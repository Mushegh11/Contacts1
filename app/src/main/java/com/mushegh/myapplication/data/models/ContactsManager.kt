package com.mushegh.myapplication.data.models

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.lifecycle.MutableLiveData
import retrofit2.Call

object ContactsManager {
    fun getContacts(context: Context): MutableLiveData<List<Contact>> {
        val liveData = MutableLiveData<List<Contact>>()
        var contactsList: MutableList<Contact> = ArrayList()
        var listEMails : MutableList<String> = ArrayList()
        var i=0
            val cr = context.contentResolver
            val cursor1 = cr?.query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                null,
                null,
                null
            )
            if (cursor1 != null && cursor1.moveToFirst()) {
                do {

                    val email =
                        cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                        if(email!=null) {
                            listEMails.add(email)
                            i++
                        }
                    else
                        {
                            listEMails.add("Email Not Found")
                        }


                } while (cursor1.moveToNext())
                cursor1.close()
            }



        val uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val projection: Array<String>? = null
        val selection: String? = null
        val selectionArgs: Array<String>? = null
        val sortOrder: String? = null
        var resolver: ContentResolver = context.contentResolver
        var cursor: Cursor? = resolver.query(uri, projection, selection, selectionArgs, sortOrder)
        while (cursor!!.moveToNext()) {
            var name: String =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            var number =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            

            var obj: Contact = Contact()
            obj.firstName = name
            obj.number = number

                if(i>=0) {
                    obj.email = listEMails[--i]

                }



            val photouri =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
            if (photouri != null) {
                obj.image =
                    MediaStore.Images.Media.getBitmap(context.contentResolver, Uri.parse(photouri))
            }

            contactsList.add(obj)



            liveData.value = contactsList
            // listcontacts.adapter = ContactsAdapter(contactsList, context)


        }
        cursor.close()
        return liveData
    }
}

