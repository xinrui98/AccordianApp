package com.example.accordianapp

class ToDoModel {
    companion object Factory {
        fun createList(): ToDoModel = ToDoModel()
    }

    var UID : String? = null
    var itemDataText : String? = null
    var done : Boolean? = null
}