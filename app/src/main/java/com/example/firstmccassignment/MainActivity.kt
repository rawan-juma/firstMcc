package com.example.firstmccassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.ProgressDialog
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var progressDialog: ProgressDialog
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db= Firebase.firestore

        progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Please wait a few seconds..")
        progressDialog.setCancelable(false)


        var AddBtn=findViewById<Button>(R.id.Add)
        AddBtn.setOnClickListener {
            var username=findViewById<EditText>(R.id.name).text.toString()
            var phone=findViewById<EditText>(R.id.phone).text.toString()
            var address=findViewById<EditText>(R.id.address).text.toString()
            if(username.isNotEmpty() && phone.isNotEmpty()&& address.isNotEmpty() ){
            progressDialog.show()
            SaveToFierbase()
            Handler().postDelayed({ progressDialog.dismiss() }, 5000)

            findViewById<EditText>(R.id.name).setText("").toString()
            findViewById<EditText>(R.id.phone).setText("").toString()
            findViewById<EditText>(R.id.address).setText("").toString()
            var i= Intent(this,Home()::class.java)
            startActivity(i)}else{
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
            }


        }
        var homebtn=findViewById<ImageView>(R.id.homebtn)
        homebtn.setOnClickListener {
            var i= Intent(this,Home()::class.java)
            startActivity(i)
        }
    }

    fun SaveToFierbase(){

        var username=findViewById<EditText>(R.id.name).text.toString()
        var phone=findViewById<EditText>(R.id.phone).text.toString()
        var address=findViewById<EditText>(R.id.address).text.toString()


            val User= hashMapOf("name" to username,"phone" to phone, "address" to address)
            db.collection("Users")
                    .add(User)
                    .addOnSuccessListener { documentReference ->

                        Log.e("TAG","User added successfully with product id ${documentReference.id}")
                    }
                    .addOnFailureListener{exception ->
                        Log.e("TAG",exception.message.toString())
                    }


    }
}