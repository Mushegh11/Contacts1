package com.mushegh.myapplication.data

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mushegh.myapplication.adapters.ContactsAdapter
import com.mushegh.myapplication.data.models.*
import com.mushegh.myapplication.network.ApiClient
import com.mushegh.myapplication.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {

    // private var allusers : LiveData<List<User>>?= null


    var apiInterface: ApiInterface = ApiClient.getClient().create(
        ApiInterface::class.java
    )


    var usernamelist: MutableList<String> = mutableListOf()

    fun getUsers(): MutableLiveData<List<User>> {

        val liveData = MutableLiveData<List<User>>()
        var call: Call<List<User>> = apiInterface.getUser()
        call.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("TAG", "OnFailure" + t.localizedMessage)
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {


                var list: List<User>? = response.body()
//                    for(i in 0 until list!!.size)
//                    {
//                        usernamelist.add(list[i].username)
//                    }
                liveData.value = list
                // Log.d("TAG", "OnResponse" + usernamelist)


//                    var gson : Gson = Gson()
//                    var json : String = response.body() as String
//                    var  list : MutableList<String> = mutableListOf()
//                    list.add(gson.fromJson(json,User::class.java).username)
//                  //  var user : User = gson.fromJson(json,User::class.java)
//                  //  Log.d("TAG" , "onResponse" + response.body())
//                    Log.d("TAG", "OnResponse $list")
            }

        })

        return liveData

    }

    fun getAlbums(): MutableLiveData<List<Album>> {
        val liveData = MutableLiveData<List<Album>>()
        var call: Call<List<Album>> = apiInterface.getAlbums()
        call.enqueue(object : Callback<List<Album>> {
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Log.d("TAG", "OnFailure" + t.localizedMessage)
            }

            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                var list: List<Album>? = response.body()
                liveData.value = list
            }


        })

        return liveData
    }


    fun getPhotos(id: Int): MutableLiveData<List<Photo>> {
        val liveData = MutableLiveData<List<Photo>>()

        var call: Call<List<Photo>> = apiInterface.getPhotos(id)
        call.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.d("TAG", "OnFailure" + t.localizedMessage)
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                var list: List<Photo>? = response.body()
                liveData.value = list
//                for(i in 0 until list!!.size)                 {
//                       usernamelist.add(list[i].url)
//                   }
//                Log.d("TAG", usernamelist.toString())
            }


        })

        return liveData
    }

    fun getContacts(context: Context): MutableLiveData<List<Contact>> {
        return ContactsManager.getContacts(context)

    }
}
