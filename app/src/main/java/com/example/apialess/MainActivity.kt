package com.example.apialess

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apialess.databinding.ActivityMainBinding
import com.example.apialess.model.Products
import com.example.apialess.model.ProductsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ProductAdapter
    private val productsList = mutableListOf<Products>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        getProducts()
    }

    private fun initRecyclerView() {
        adapter = ProductAdapter(productsList)
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<List<Products>> = getRetrofit().create(ProductApiService::class.java).getAllProducts()
            val products : List<Products>? = call.body()

            if (call.isSuccessful) {
                val products = call.body() ?: emptyList()
                runOnUiThread {
                    productsList.clear()
                    productsList.addAll(products)
                    adapter.notifyDataSetChanged()
                }
            } else {
                runOnUiThread { showError() }
            }
        }
    }


    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }



}
