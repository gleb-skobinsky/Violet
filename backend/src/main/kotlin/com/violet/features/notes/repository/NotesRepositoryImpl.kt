package com.violet.features.notes.repository

import com.violet.features.notes.models.NoteResponse
import com.violet.features.users.repository.Users
import com.violet.shared.BaseRepository
import com.violet.shared.uuid
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

private object Notes : IdTable<UUID>("notes") {
    override val id = uuid("id").entityId().clientDefault {
        EntityID(UUID.randomUUID(), Notes)
    }
    val userId = uuid("user_id").references(
        ref = Users.id,
        onDelete = ReferenceOption.CASCADE
    )
    val title = varchar("title", 255)
    val content = text("content")
    override val primaryKey = PrimaryKey(id)
}

class NotesRepositoryImpl(
    database: Database
) : BaseRepository(), NotesRepository {

    init {
        transaction(database) {
            SchemaUtils.create(Notes)
        }
    }

    override suspend fun getNotesForUser(email: String): List<NoteResponse> {
        return dbQuery {
            val userId = getUserOrNull(
                email = email
            ) ?: return@dbQuery emptyList()
            Notes.select { Notes.userId eq userId }
                .map { row ->
                    NoteResponse(
                        id = row[Notes.id].value.toString(),
                        title = row[Notes.title],
                        body = row[Notes.content]
                    )
                }
        }
    }

    override suspend fun saveNote(
        userEmail: String,
        title: String,
        body: String
    ): Boolean {
        return dbQuery {
            val userId = getUserOrNull(
                email = userEmail
            ) ?: return@dbQuery false
            Notes.insert {
                it[this.userId] = userId
                it[this.title] = title
                it[this.content] = body
            }
            true
        }
    }

    override suspend fun deleteNote(noteId: String): Boolean {
        return dbQuery {
            Notes.deleteWhere {
                id.eq(noteId.uuid())
            } > 0
        }
    }

    private fun getUserOrNull(email: String): UUID? {
        return Users.select {
            Users.email eq email
        }.map { it[Users.id] }
            .singleOrNull()?.value
    }
}