package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val wishlistItems: List<Wishlist>) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView
        val count: TextView
        val description: TextView
        init {
            itemName = itemView.findViewById<View>(R.id.itemName) as TextView
            count = itemView.findViewById<View>(R.id.count) as TextView
            description = itemView.findViewById<View>(R.id.description) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val listView = inflater.inflate(R.layout.wishlist_item, parent, false)
        return ViewHolder(listView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.isLongClickable = true
        val newItem = wishlistItems[position]
        holder.itemName.text = newItem.itemName
        holder.count.text = newItem.count
        holder.description.text = newItem.description
        //Open URL
        holder.description.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(newItem.description))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for ${newItem.description}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return wishlistItems.size
    }

    /*
    fun setClickListener(itemClickListener : AdapterView.OnItemClickListener) {
        mClickListener = itemClickListener
    }


    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
     */
}