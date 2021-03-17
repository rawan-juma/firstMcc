package com.example.firstmccassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UsersAdapter(var con: Context?, var data: MutableList<Users>): RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username=itemView.findViewById<TextView>(R.id.username1)
        val phone=itemView.findViewById<TextView>(R.id.phone1)
        val addess=itemView.findViewById<TextView>(R.id.address1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(con).inflate(R.layout.user_item, parent, false)
        return MyViewHolder(
                itemView
        )
    }

    override fun getItemCount(): Int {
        return data.size    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.username.text=data[position].Username
        holder.phone.text=data[position].Phone
        holder.addess.text=data[position].Address

    }





}