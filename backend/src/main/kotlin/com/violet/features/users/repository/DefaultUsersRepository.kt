package com.violet.features.users.repository

import com.violet.features.users.models.ExposedUser
import com.violet.shared.BaseRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table() {
    val id = integer("id").autoIncrement()
    val email = varchar("email", length = 50)
    val password = varchar("password", length = 50)
    val verified = bool("verified")

    override val primaryKey = PrimaryKey(id)
}

class DefaultUsersRepository(database: Database) : UsersRepository, BaseRepository() {

    init {
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

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
