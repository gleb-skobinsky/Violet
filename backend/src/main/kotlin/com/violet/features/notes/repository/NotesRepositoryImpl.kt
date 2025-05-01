package com.violet.features.notes.repository

import com.violet.features.notes.models.NoteResponse
import com.violet.features.users.repository.Users
import com.violet.shared.BaseRepository
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

private object Notes : Table() {
    val id = integer("id").autoIncrement()
    val userId = integer("user_id") references Users.id
    val title = varchar("title", 255)
    val content = text("content")
    override val primaryKey = PrimaryKey(id)
}

class NotesRepositoryImpl(
    private val database: Database
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
                        title = row[Notes.title],
                        body = row[Notes.content]
                    )
                }
        }
    }

    override suspend fun saveNote(title: String, body: String): Boolean {
        TODO("Not yet implemented")
    }
}