package com.example.addfiles

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.net.URI

class MainActivity : AppCompatActivity() {

    val PDF : Int = 0
    lateinit var uri : Uri
    lateinit var mStorage : StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chooseBtn = findViewById<Button>(R.id.pdfBtn)
        val upBtn = findViewById<Button>(R.id.upBtn)
        mStorage = FirebaseStorage.getInstance().getReference("Uploads")
        chooseBtn.setOnClickListener {
            val intent = Intent()
            intent.setType("pdf/*")
            Intent.ACTION_GET_CONTENT
         MediaStore.Files.FileColumns.MEDIA_TYPE_DOCUMENT
            startActivityForResult(Intent.createChooser(intent, "Select PDF"), PDF)
        }
        upBtn.setOnClickListener  {
        val fileref = mStorage.child(uri.lastPathSegment.toString())
        val uploadTask=fileref.putFile(uri)
        uploadTask.addOnSuccessListener {taskSnanshot->
            val dow = taskSnanshot.metadata?.reference?.downloadUrl
            val doeTxt=findViewById<TextView>(R.id.dwnTxt)
            doeTxt.text=dow.toString()
            Toast.makeText(this, "Upload", Toast.LENGTH_LONG)
        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_LONG)
        }
    }}
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val uriTxt=findViewById<TextView>(R.id.uriTxt)
        if(requestCode == PDF && resultCode == RESULT_OK){
            uri = data!!.data!!
            uriTxt.text=uri.toString()



}}}


