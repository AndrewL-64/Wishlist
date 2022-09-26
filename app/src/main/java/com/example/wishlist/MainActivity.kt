package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var wishlist: List<Wishlist>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        //Setting up RecyclerView
        val wishlistRV = findViewById<View>(R.id.rvlist) as RecyclerView
        wishlist = ArrayList()
        val adapter = WishlistAdapter(wishlist)
        //Attaching the adapter to RecyclerView to populate items
        //Set layout manager to position the items
        wishlistRV.layoutManager = LinearLayoutManager(this)
        wishlistRV.adapter = adapter
        //Once button is pressed, add to list of items
        val name = findViewById<EditText>(R.id.itemNameEditText)
        val count = findViewById<EditText>(R.id.CountEditText)
        val desc = findViewById<EditText>(R.id.DescriptionEditText)
        findViewById<Button>(R.id.submit).setOnClickListener {
            Toast.makeText(it.context, "Here", Toast.LENGTH_SHORT).show()
            val mName: String = name.text.toString()
            val mCount: String = count.text.toString()
            val mDescription: String = desc.text.toString()
            //Clear the input fields
            name.text.clear(); count.text.clear(); desc.text.clear()
            val newItem = Wishlist(mName, mCount, mDescription)
            (wishlist as MutableList<Wishlist>).add(newItem)
            //Notify the adapter of new information
            adapter.notifyItemInserted(wishlist.size - 1)
        }
    }
}