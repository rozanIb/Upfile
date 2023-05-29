package com.example.addfiles

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class MainActivity : AppCompatActivity() {
lateinit var uri : Uri
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val pdfBtn = findViewById<Button>(R.id.pdfBtn)
    val docxBtn = findViewById<Button>(R.id.docxBtn)
    val audioBtn = findViewById<Button>(R.id.audioBtn)
    val videoBtn = findViewById<Button>(R.id.videoBtn)
    val imageBtn = findViewById<Button>(R.id.imageBtn)
val infoBtn=  findViewById<Button>(R.id.infoBtn)

    infoBtn.setOnClickListener() {
        val intent = Intent (this,Charts::class.java)
        startActivity(intent)
    }
    pdfBtn.setOnClickListener() {
        val intent = Intent()
        intent.setType("pdf/*")
        Intent.ACTION_GET_CONTENT
        MediaStore.Files.FileColumns.MEDIA_TYPE_DOCUMENT
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), 100)
    }
    docxBtn.setOnClickListener() {
        val intent = Intent()
        intent.setType("docx/*")
        Intent.ACTION_GET_CONTENT
        MediaStore.Files.FileColumns.MEDIA_TYPE_DOCUMENT
        startActivityForResult(Intent.createChooser(intent, "Select Docx"), 100)
    }
    audioBtn.setOnClickListener() {
        val intent = Intent()
        intent.setType("audio/*")
            Intent.ACTION_GET_CONTENT
            MediaStore.Files.FileColumns.MEDIA_TYPE_AUDIO
        startActivityForResult(Intent.createChooser(intent, "Select AUDIO"), 100)
    }
    videoBtn.setOnClickListener() {
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        intent.setType("video/*")
        startActivityForResult(Intent.createChooser(intent, "Select VIDEO"), 100)
    }
    imageBtn.setOnClickListener() {
        val intent = Intent(
        Intent.ACTION_PICK,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/*")
        startActivityForResult(Intent.createChooser(intent, "Select IMAGE"), 100)
    }
}
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    val uriTxt = findViewById<TextView>(R.id.uriTxt)
    if (requestCode == 100 && resultCode == RESULT_OK) {
        uri = data!!.data!!
        uriTxt.setText(uri.toString())
        upload()
    }
}
private fun upload() {
   var mStorage = Firebase.storage.getReference("Uploads")
    val pdfref = mStorage.child(uri.lastPathSegment.toString())
    pdfref.putFile(uri).addOnSuccessListener {
        Toast.makeText(this, "Upload", Toast.LENGTH_LONG)
    }.addOnFailureListener {
        Toast.makeText(this,"Failed",Toast.LENGTH_LONG)
    }
}}