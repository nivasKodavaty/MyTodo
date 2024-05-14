package com.example.mytodo.data

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object RealmClient {

    val realm: Realm by lazy {
        val configuration = RealmConfiguration.create(schema = setOf(Todo::class))
        Realm.open(configuration)
    }
    fun getTodoById(id: String): Todo? {
        return realm.query<Todo>("id = $0", id).first().find()
    }

    fun getAllTodoAsFlow(): Flow<List<Todo>> {
        return realm.query<Todo>().asFlow().map { it.list }
    }

    fun addTodo(todo: Todo) {
        realm.writeBlocking {
            copyToRealm(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        realm.writeBlocking {
            query<Todo>("id = $0", todo.id)
                .first()
                .find()
                ?.let { delete(it) }
                ?: throw IllegalStateException("Todo not found.")
        }
    }

    fun updateTodo(todoItem: Todo) {
        realm.query<Todo>("id = $0", todoItem.id)
            .first()
            .find()
            ?.also { todo ->
                // Add a dog in a transaction
                realm.writeBlocking {
                    findLatest(todo)?.apply {
                        title = todoItem.title
                        description = todoItem.description
                    }
                }
            }
    }

}