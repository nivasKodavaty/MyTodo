import com.example.mytodo.data.Todo
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class TodoRepository(private val realm: Realm) {
    fun add(todo: Todo) {
        realm.writeBlocking {
            copyToRealm(todo)
        }
    }

    fun getAll(): Flow<List<Todo>> = realm.query<Todo>().asFlow()
        .map { results ->
            results.list.toList()
        }
    fun delete(todo: Todo) {
        realm.writeBlocking {
            val item = query<Todo>("id == $0", todo.id).first().find()
            if (item != null) {
                delete(item)
            }
        }
    }
    fun update(todo: Todo) {
        realm.writeBlocking {
            val item = query<Todo>("id == $0", todo.id).first().find()
            if (item != null) {
                item.title = todo.title
                item.description=todo.description
            }
    }
    }

}