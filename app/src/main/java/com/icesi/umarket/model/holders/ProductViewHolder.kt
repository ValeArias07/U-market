package com.icesi.umarket.model.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.icesi.umarket.consumer.ConsumerMainOverviewFragment
import com.icesi.umarket.R
import com.icesi.umarket.model.Product
import com.icesi.umarket.util.Constants
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
                    product.name,
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
        Util.loadImage(product.imageID,producImageRow, Constants.productImg)
    }
}