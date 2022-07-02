package com.icesi.umarket.model.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icesi.umarket.R
import com.icesi.umarket.model.Product
import com.icesi.umarket.model.holders.ProductSellerViewHolder
import com.icesi.umarket.seller.SellerMainOverviewFragment
import java.io.File

class ProductSellerAdapter: RecyclerView.Adapter<ProductSellerViewHolder>() {

    private val products = ArrayList<Product>()
    lateinit var onProductSellerObserver: SellerMainOverviewFragment.OnProductsOnSellerObserver

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSellerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.product_seller_row,parent,false)
        val productViewHolder = ProductSellerViewHolder(view)
        productViewHolder.onProductSellerObserver = onProductSellerObserver
        return productViewHolder
    }

    override fun onBindViewHolder(holder: ProductSellerViewHolder, position: Int) {
        val product = products[position]

        holder.bindProduct(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun clear() {
        val size = products.size
        products.clear()
        notifyItemRangeRemoved(0,size)
    }

    fun addProduct(product: Product){
        products.add(product)
        notifyItemInserted(products.size-1)
    }

    private fun setImageBitmap(image: String?): Bitmap? {
        val file = image?.let { File(it) }
        val bitmap = BitmapFactory.decodeFile(file?.path)
        val thumpnail = Bitmap.createScaledBitmap(bitmap, bitmap.width/4, bitmap.height/4, true)

        return thumpnail
    }

}