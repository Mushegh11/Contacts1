package com.mushegh.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mushegh.myapplication.R
import com.mushegh.myapplication.data.models.User


class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private var users : List<User> = listOf()
    private var mListener : AdapterView.OnItemClickListener? =null


       class UsersViewHolder(item : View) : RecyclerView.ViewHolder(item)
       {

               var textViewName : TextView = item.findViewById(R.id.user_text)


       }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.users_item,parent,false)
        return UsersViewHolder(v)
    }

    override fun getItemCount() = users.size



    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        var currentUser = users[position]

     //   val user : User = users[position]

        holder.textViewName.text =  currentUser.username
            //listUsers[position].username

    }
     fun setUsers(users : List<User>)
    {
        this.users=users
        notifyDataSetChanged()
    }

}