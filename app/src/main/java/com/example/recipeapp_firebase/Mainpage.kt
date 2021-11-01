package com.example.recipeapp_firebase

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Mainpage : AppCompatActivity() {

    lateinit var recycle: RecyclerView
    lateinit var list:ArrayList<String>
    var list2= arrayListOf<ArrayList<String>>()
    val db = Firebase.firestore

//    var id=intent.extras!!.getString("id").toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)
        recycle=findViewById(R.id.rv)
        list= arrayListOf()

        updatedrecycle()
    }

    fun addrecipe(view: View) {
       startActivity( Intent(this,MainActivity::class.java))

    }


    fun updatedrecycle(){
        db.collection("Recipe")
            .get()
            .addOnSuccessListener { result ->
                var details = "\n"

                for (d in result) {
                    d.data.map { (key, value ) ->
                        details = "$key: $value \n\n"
                        details.toSortedSet()
                        list.add(details)
                        //list2=list
                        Log.d("REsult",details)
                        //list.sortByDescending { it}
                    }
                }


                recycle.adapter = RVAdapter(this, list)
                recycle.layoutManager = LinearLayoutManager(this)

            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show() }

       // db.collection("Recipe").document(id).update("Title","Hello")
        }



}