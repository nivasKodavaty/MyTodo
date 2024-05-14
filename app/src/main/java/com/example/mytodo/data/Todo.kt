package com.example.mytodo.data

import io.realm.kotlin.types.RealmObject
import org.mongodb.kbson.ObjectId
import java.util.UUID


open class Todo : RealmObject {
    var id: String?=UUID.randomUUID().toString()
    var title: String = ""
    var description: String = ""
}