package com.violet.features.notes.repository

import com.violet.features.notes.models.NoteResponse
import com.violet.features.users.repository.Users
import com.violet.shared.BaseRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

private object Notes : Table() {
    val id = integer("id").autoIncrement()
    val userId = integer("user_id") references Users.id
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
            val userId = Users.select {
                Users.email eq email
            }.map { it[Users.id] }
                .singleOrNull() ?: return@dbQuery emptyList()
            Notes.select { Notes.userId eq userId }
                .map { row ->
                    NoteResponse(
                        id = row[Notes.id],
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

    override suspend fun deleteNote(noteId: Int): Boolean {
        return dbQuery {
            Notes.deleteWhere {
                id.eq(noteId)
            } > 0
        }
    }

    private fun getUserOrNull(email: String): Int? {
        return Users.select {
            Users.email eq email
        }.map { it[Users.id] }
            .singleOrNull()
    }
}