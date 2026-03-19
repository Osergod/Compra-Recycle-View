package com.example.comprarecycleview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StoreActivity : AppCompatActivity() {

    lateinit var totalText: TextView
    lateinit var adapter: ProductAdapter
    val products = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        totalText = findViewById(R.id.total)

        // Productos de prueba
        products.add(Product("Producto 1", 10.0))
        products.add(Product("Producto 2", 20.0))

        adapter = ProductAdapter(products) {
            updateTotal()
        }

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        findViewById<Button>(R.id.btnCarrito).setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        updateTotal()
    }

    fun updateTotal() {
        val total = products.sumOf { it.price * it.quantity }
        totalText.text = "Total: $total €"
    }
}