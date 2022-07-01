package com.icesi.umarket.model.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.icesi.umarket.consumer.ConsumerMainOverviewFragment
import com.icesi.umarket.R
import com.icesi.umarket.model.Product
import com.icesi.umarket.util.Util

class ProductViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    //STATE
    lateinit var product: Product
    lateinit var onProductObserver: ConsumerMainOverviewFragment.SellerObserver

    //UI controllers
    var producImageRow: ImageView = itemView.findViewById(R.id.marketRowImage)
    var productNameRow: TextView = itemView.findViewById(R.id.marketNameRowTextView)
    var productPriceRow: TextView = itemView.findViewById(R.id.descriptMarket)

    init {
        producImageRow.setOnClickListener {
            onProductObserver.sendProduct(
                Product(
                    product.id,
                    productNameRow.text.toString(),
                    product.price,
                    product.description,
                    product.imageID,
                    product.amount)
            )
        }
    }

    fun bindProduct(product: Product){
        this.product = product
        productNameRow.text = product.name
        productPriceRow.text = Util.refactMoneyAmount(product.price)
        Util.loadImage(product.imageID.toString(),producImageRow, "product-images" )
    }
}