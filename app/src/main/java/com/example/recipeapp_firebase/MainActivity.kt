package com.example.recipeapp_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var title: EditText
    lateinit var name: EditText
    lateinit var ingre: EditText
    lateinit var instr: EditText
    lateinit var save : Button
    lateinit var id1:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title=findViewById(R.id.edtitle)
        name=findViewById(R.id.edname)
        ingre=findViewById(R.id.edinge)
        instr=findViewById(R.id.edins)
        save=findViewById(R.id.btnsave)


        val db = Firebase.firestore

        save.setOnClickListener {
            var s1=title.text.toString()
            var s2=name.text.toString()
            var s3=ingre.text.toString()
            var s4=instr.text.toString()
            if(s1.isNotEmpty()&&s2.isNotEmpty()&&s3.isNotEmpty()&&s4.isNotEmpty())
            {
                val recipe= hashMapOf(
                    "Title" to s1,
                    "author" to s2,
                    "ingredents" to s3,
                    "instruction" to s4
                )
                db.collection("Recipe")
                    .add(recipe)
                    .addOnSuccessListener {  id1=it.id
                        Toast.makeText(applicationContext, "data successfully added", Toast.LENGTH_SHORT)
                        .show() }
                    .addOnFailureListener { Toast.makeText(applicationContext, "Somethingwent wrong", Toast.LENGTH_SHORT)
                        .show() }

                title.text.clear()
                name.text.clear()
                ingre.text.clear()
                instr.text.clear()

            }
            else{
                Toast.makeText(applicationContext,"one or more field empty", Toast.LENGTH_SHORT).show()
            }


        }

    }

    fun viewreceipe(view: View) {
         val info=Intent(this,Mainpage::class.java)
        info.putExtra("id",id1)
        startActivity(info)
    }
}