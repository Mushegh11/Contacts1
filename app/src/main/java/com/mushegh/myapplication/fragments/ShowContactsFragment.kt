package com.mushegh.myapplication.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mushegh.myapplication.R
import kotlinx.android.synthetic.main.showcontacts_layout.*

class ShowContactsFragment : Fragment() {


    companion object {
        fun newInstance() = ShowContactsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view : View = inflater.inflate(R.layout.showcontacts_layout,container,false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        intent.putExtra("Name",currentUser.firstName)
//        intent.putExtra("Phone",currentUser.number)
//        intent.putExtra("Email",currentUser.email)
//        intent.putExtra("image",currentUser.image)
//
        var bundle : Bundle = arguments!!
        var name = bundle.getString("Name")
        var phone = bundle.getString("Phone")
        var email = bundle.getString("Email")

        var firstName : String = ""
        var lastName : String = ""

        var i=0

        while(i<name!!.length)
        {
            if(name[i]!=' ')
            {
                firstName+=name[i]
            }
            else
            {

                lastName+=name[i]
                var j =i+1
                while(j<name.length)
                {

                    lastName+=name[j]
                    j++
                }
                break
            }
            i++
        }
        contactsFirstName.text = firstName
        contactsLastName.text = lastName
        contactsPhone.text= phone
        if(email!=null) {
            contactsEmail.text = email
        }
        var image = bundle.getParcelable<Bitmap>("image")
        if(image != null) {
            image_contact_show.setImageBitmap(image)
        }
        else
        {
            image_contact_show.setImageResource(R.drawable.ic_account_circle_black_24dp)
        }


    }
}