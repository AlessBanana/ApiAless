package com.example.apialess

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apialess.model.Products

class ProductAdapter (val products: List<Products>): RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val LayoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(LayoutInflater.inflate(R.layout.itemproduct, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item : Products = products[position]
        holder.bind(item)
    }


}