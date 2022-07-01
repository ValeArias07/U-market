package com.icesi.umarket.util

import android.app.Activity
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.text.NumberFormat
import java.util.*

object Util {

    fun loadImage(id: String, imgView: ImageView, path: String) {
        if (id != "") {
            Firebase.storage.reference.child(path)
                .child(id).downloadUrl
                .addOnSuccessListener {
                    Glide.with(imgView).load(it).into(imgView)
                }
        }
    }

    fun initRecycler(recycler: RecyclerView, activity: Activity, orientation: Int) : RecyclerView{
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(activity, orientation, false)
        return recycler
    }

    fun sendImg(id: String, path: String , uri: Uri): Boolean{
        var sended: Boolean = true;
        Firebase.storage.reference.child(path).child(id)
            .putFile(uri)
            .addOnSuccessListener {
                sended = true
            }
        return sended
    }

    fun refactMoneyAmount(amount: Int): String{
        val number: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        number.maximumFractionDigits = 0
        return number.format(amount).replace(",", ".")
    }

    fun checkTextLength(textLength: Int, max: Int){
        //// Cada vez que el usuario ingrese un caracter, calcular el tamaño del texto y
        ///  restarlo con el limite para mostrar cuantos caracteres quedan disponibles
    }
}