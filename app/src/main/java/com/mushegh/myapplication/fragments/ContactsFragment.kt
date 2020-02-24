package com.mushegh.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mushegh.myapplication.R
import com.mushegh.myapplication.adapters.ContactsAdapter
import com.mushegh.myapplication.data.models.Contact
import com.mushegh.myapplication.viewmodel.ContactsViewModel


class ContactsFragment : Fragment() , ContactsAdapter.OnAdapterItemClickListener {

    override fun inItemLongClick(current: Contact, position: Int) {
        Toast.makeText(context,"You clicked long $position",Toast.LENGTH_SHORT).show()
    }


    override fun onItemClick(current: Contact, position: Int) {
        Toast.makeText(context,"You clicked $position",Toast.LENGTH_SHORT).show()

        var bundle : Bundle = Bundle()
        bundle.putString("Name",current.firstName)
        bundle.putString("LastName",current.lastName)
        bundle.putString("Phone",current.number)
        bundle.putString("Email",current.email)
        bundle.putParcelable("image",current.image)

        var fragmentManager : FragmentManager = activity!!.supportFragmentManager
        var fragmentTrasaction : FragmentTransaction = fragmentManager.beginTransaction()

        var showfrag : ShowContactsFragment = ShowContactsFragment()
        showfrag.arguments = bundle
        fragmentTrasaction.replace(R.id.fragment_container,showfrag,null).addToBackStack(null)
        fragmentTrasaction.commit()

//        var intent : Intent = Intent(context, ShowContactsFragment::class.java)
//            intent.putExtra("Name",current.firstName)
//            intent.putExtra("Phone",current.number)
//            intent.putExtra("Email",current.email)
//          intent.putExtra("image",current.image)
//            context!!.startActivity(intent)

    }




    lateinit var contactsViewModel :  ContactsViewModel
    var contactsAdapter : ContactsAdapter?=null
    var mLayoutManager : RecyclerView.LayoutManager?=null




    override fun onContextItemSelected(item: MenuItem): Boolean {
        if(item.itemId ==121)
        {
            Toast.makeText(context,"Edit",Toast.LENGTH_SHORT).show()
            return true
        }
        else if (item.itemId ==122)
        {
            Toast.makeText(context,"Delete",Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onContextItemSelected(item)
    }




    companion object {
        fun newInstance() = ContactsFragment()
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.contacts_recyclerview,container,false)


    }











    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.listcontacts)
        mLayoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        var listcontats : MutableList<Contact> = mutableListOf()



        contactsAdapter = ContactsAdapter(listcontats,context!!,this)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = contactsAdapter


        contactsViewModel = ViewModelProvider(this).get(ContactsViewModel::class.java)
        contactsViewModel.getContacts(context!!).observe(this, Observer {
            //            var i=0
//                while (i < it.size) {
//                    userslist.add(User(it[i].id, it[i].username, it[i].email))
//                    i++
//                }
            contactsAdapter!!.setContacts(it)
        })



    }

}