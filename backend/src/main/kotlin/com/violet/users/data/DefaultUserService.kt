package com.violet.users.data

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class DefaultUserService(database: Database) : UserService {
    private object Users : Table() {
        val id = integer("id").autoIncrement()
        val email = varchar("email", length = 50)
        val password = varchar("password", length = 50)
        val verified = bool("verified")

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    override suspend fun create(user: ExposedUser): Int = dbQuery {
        Users.insert {
            it[email] = user.email
            it[password] = user.password
            it[verified] = user.verified
        }[Users.id]
    }

    override suspend fun readById(id: Int): ExposedUser? {
        return dbQuery {
            Users.select { Users.id eq id }
                .map { ExposedUser(it[Users.email], it[Users.password], it[Users.verified]) }
                .singleOrNull()
        }
    }

    override suspend fun readByEmail(email: String): ExposedUser? {
        return dbQuery {
            Users.select { Users.email eq email }
                .map { ExposedUser(it[Users.email], it[Users.password], it[Users.verified]) }
                .singleOrNull()
        }
    }


    override suspend fun update(id: Int, user: ExposedUser) {
        dbQuery {
            Users.update({ Users.id eq id }) {
                it[email] = user.email
                it[password] = user.password
            }
        }
    }

    override suspend fun delete(id: Int) {
        dbQuery {
            Users.deleteWhere { Users.id.eq(id) }
        }
    }
}
