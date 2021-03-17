package com.example.firstmccassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Home : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        db= Firebase.firestore
        getAllProducts()
    }

    private fun getAllProducts(){
        var recyclerView=findViewById<RecyclerView>(R.id.rec1)
        val Users= mutableListOf<Users>()
        db.collection("Users")
            .get()
            .addOnCompleteListener {task ->
                if(task.isSuccessful){
                    for(document in task.result!!){

                        val data=document.data
                        val name=data["name"] as String
                        val phone=data["phone"]as String
                        val address=data["address"] as String

                        Users.add(Users(name,phone,address))
                        Toast.makeText(this,"successful********", Toast.LENGTH_SHORT).show()
                    }

                    recyclerView.layoutManager= LinearLayoutManager(this)
                    recyclerView.setHasFixedSize(true)
                    val UsersAdapter = UsersAdapter(this, Users)
                    recyclerView.adapter=UsersAdapter
                }
            }
    }
}