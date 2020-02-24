package com.mushegh.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mushegh.myapplication.R
import com.mushegh.myapplication.viewmodel.UserViewModel
import com.mushegh.myapplication.adapters.UsersAdapter

class UsersFragment : Fragment() {

    var usersAdapter : UsersAdapter?=null
    var mLayoutManager : RecyclerView.LayoutManager?=null
    lateinit var userViewModel : UserViewModel



    companion object {
        fun newInstance() = UsersFragment()
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_users,container,false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





//        userslist.add(User(1,"Mushegh","mymail@c"))
//        userslist.add(User(1,"Mushegh","mymail@c"))
//        userslist.add(User(1,"Mushegh","mymail@c"))
//        userslist.add(User(1,"Mushegh","mymail@c"))

        val recyclerView : RecyclerView = view.findViewById(R.id.recycler_view)
        mLayoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        usersAdapter = UsersAdapter()
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = usersAdapter

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getUsers().observe(this, Observer {
//            var i=0
//                while (i < it.size) {
//                    userslist.add(User(it[i].id, it[i].username, it[i].email))
//                    i++
//                }
            usersAdapter!!.setUsers(it)
        })
















    }


}