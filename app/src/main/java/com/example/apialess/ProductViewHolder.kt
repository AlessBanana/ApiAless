package com.example.apialess

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apialess.databinding.ItemproductBinding
import com.example.apialess.model.Products
import com.squareup.picasso.Picasso

class ProductViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemproductBinding.bind(view)

    fun bind(product: Products) {
        Picasso.get().load(product.image).into(binding.ivProduct)
        binding.prTitle.setText("Título: " + product.title)
        binding.prDescription.setText("Descripción: " + product.description)
        binding.prPrice.setText("Precio: " + product.price)
    }
}