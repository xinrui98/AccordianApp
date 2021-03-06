package com.example.accordianapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() , UpdateAndDelete{
    lateinit var database: DatabaseReference

    var toDoList: MutableList<ToDoModel>? = null
    lateinit var adapter: ToDoAdapter
    private var listViewItem: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fab = findViewById(R.id.fab) as FloatingActionButton
        val button1 = findViewById<Button>(R.id.button1)
        listViewItem = findViewById(R.id.item_listview) as ListView


        database = FirebaseDatabase.getInstance().reference
        fab.setOnClickListener(){
            Toast.makeText(this,"button 1 clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }
        //add item to TODO list
        button1.setOnClickListener { view ->
            val alertDialog = AlertDialog.Builder(this)
            val textEditText = EditText(this)
//            alertDialog.setMessage("Add TODO item")
            alertDialog.setTitle("Enter last 4 digits of parcel ID")
            alertDialog.setView(textEditText)
            alertDialog.setPositiveButton("Add") { dialog, i ->
                val todoItemData = ToDoModel.createList()
                todoItemData.itemDataText = textEditText.text.toString()
                todoItemData.done = false

                val newItemData = database.child("todo").push()
                todoItemData.UID = newItemData.key

                newItemData.setValue(todoItemData)

                dialog.dismiss()
                Toast.makeText(this, "item saved", Toast.LENGTH_LONG).show()
            }
            alertDialog.show()
        }

        toDoList = mutableListOf<ToDoModel>()
        adapter = ToDoAdapter(this, toDoList!!)
        listViewItem!!.adapter = adapter
        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "No Item Added", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                toDoList!!.clear()
                addItemToList(snapshot)
            }

        })

    }

    private fun addItemToList(snapshot: DataSnapshot) {
        val items = snapshot.children.iterator()

        if (items.hasNext()) {
            val toDoIndexValue = items.next()
            val itemsIterator = toDoIndexValue.children.iterator()

            while (itemsIterator.hasNext()) {
                val currentItem = itemsIterator.next()
                val toDoItemData = ToDoModel.createList()
                val map = currentItem.getValue() as HashMap<String, Any>

                toDoItemData.UID = currentItem.key
                toDoItemData.done = map.get("done") as Boolean?
                toDoItemData.itemDataText = map.get("itemDataText") as String?
                toDoList!!.add(toDoItemData)

            }
        }

        adapter.notifyDataSetChanged()
    }

    override fun modifyItem(itemUID: String, isDone: Boolean) {
        val itemReference = database.child("todo").child(itemUID)
        itemReference.child("done").setValue(isDone)
    }

    override fun onItemDelete(itemUID: String) {
        val itemReference = database.child("todo").child(itemUID)
        itemReference.removeValue()
        adapter.notifyDataSetChanged()
    }

}