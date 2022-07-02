package com.icesi.umarket.model.holders

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.icesi.umarket.R
import com.icesi.umarket.model.Order
import com.icesi.umarket.util.Constants
import com.icesi.umarket.util.Util

class OrderViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    //STATE
    var order: Order? = null

    //UI controllers
    var productImgOrderRow: ImageView = itemView.findViewById(R.id.productImgOrderRow)
    var productNameOrder: TextView = itemView.findViewById(R.id.productNameOrder)
    var amountProductOrder: TextView = itemView.findViewById(R.id.amountProductOrder)
    var priceProductOrder: TextView = itemView.findViewById(R.id.priceOrderRow)
    var statusProductOrder: TextView = itemView.findViewById(R.id.statusOrderConsumerText)


    fun bindOrder(order: Order) {
        this.order = order
        productNameOrder.text = order.name
        amountProductOrder.text = "  " + order.amount.toString()
        priceProductOrder.text = Util.refactMoneyAmount(order.totalPrice)
        orderFlagColor(order.orderFlag)
        Util.loadImage(order.imageID,productImgOrderRow, Constants.productImg)
    }

    private fun orderFlagColor(orderFlag: String) {
        when (orderFlag) {
            Constants.successFlag -> statusProductOrder.setTextColor(Color.rgb(139, 195, 74))
            Constants.cancelFlag -> statusProductOrder.setTextColor(Color.rgb(255, 51, 51))
            Constants.editFlag -> statusProductOrder.setTextColor(Color.rgb(103, 58, 183))
        }
        statusProductOrder.text = orderFlag
    }
}