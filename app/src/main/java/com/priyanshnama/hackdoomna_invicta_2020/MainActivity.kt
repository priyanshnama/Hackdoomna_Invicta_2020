package com.priyanshnama.hackdoomna_invicta_2020

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.*


class MainActivity : AppCompatActivity() {

    var txt_pathShow: TextView? = null
    var encrypt: Button? = null
    var decrypt: Button? = null
    var myFileIntent: Intent? = null
    var ofp: String = ""
    var efp : String = ""
    var dfp : String = ""
    var a : Int = 0
    var b : Int = 0
    var file: File? = null
    private var PICKFILE_REQUEST_CODE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_pathShow = findViewById<View>(R.id.txt_show) as TextView
        encrypt = findViewById<View>(R.id.encrypt) as Button
        decrypt = findViewById<View>(R.id.decrypt) as Button

        encrypt!!.setOnClickListener {

            a = Key1.text.toString().toInt()
            b = Key2.text.toString().toInt()
            encrypt(file, a, b)
        }
        decrypt!!.setOnClickListener {

            a = Key1.text.toString().toInt()
            b = Key2.text.toString().toInt()
            decrypt(file, a, b)
        }

    }



    fun encrypt(file: File?, a: Int, b : Int):Int {
        var bytearray: ByteArray = file!!.readBytes()

        var bytearray2 = bytearray

        var arr = IntArray(bytearray.size)
        arr[a - 1] = 1;arr[b - 1] = -1;
        for (i in 0..bytearray.size - 1) {
            if ((arr[i] > 0) or (arr[i] < 0)) {
                if (i + a < bytearray.size) arr[i + a] = arr[i + a] + 1
                if (i + b < bytearray.size) arr[i + b] = arr[i + b] - 1
            }
        }
        for (i in 0..bytearray2.size - 1) {
            if (arr[i] > 0) {
                for (o in 0..arr[i]) bytearray2[i] = bytearray2[i].plus(b).toByte()
            }
            if (arr[i] < 0) {
                for (o in arr[i]..0) bytearray2[i] = bytearray2[i].minus(a).toByte()
            }
        }
        println(bytearray2.size)
        file.writeBytes(bytearray2)
        return 1
    }

    fun decrypt(file: File?, a: Int, b : Int): Int {
        var bytearray: ByteArray = file!!.readBytes()

        var bytearray2 = bytearray

        var arr = IntArray(bytearray.size)
        arr[a - 1] = 1;arr[b - 1] = -1;
        for (i in 0..bytearray.size - 1) {
            if ((arr[i] > 0) or (arr[i] < 0)) {
                if (i + a < bytearray.size) arr[i + a] = arr[i + a] + 1
                if (i + b < bytearray.size) arr[i + b] = arr[i + b] - 1
            }
        }
        for(i in 0..bytearray2.size-1)
        {
            if(arr[i] > 0){
                for(o in 0..arr[i])bytearray2[i]=bytearray2[i].minus(b).toByte()
            }
            if(arr[i] < 0){
                for(o in arr[i]..0)bytearray2[i]=bytearray2[i].plus(a).toByte()
            }
        }
        println(bytearray2.size)
        file.writeBytes(bytearray2)
        return 1

    }
}