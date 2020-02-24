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
import com.mushegh.myapplication.*
import com.mushegh.myapplication.adapters.AlbumsRecyclerAdapter
import com.mushegh.myapplication.data.models.Album
import com.mushegh.myapplication.viewmodel.AlbumViewModel
import kotlinx.android.synthetic.main.albumphoto_layout.*

class AlbumsPhotosFragment : Fragment() {


    //var photosAdapter : PhotoRecyclerAdapter?=null
    var albumsAdapter : AlbumsRecyclerAdapter?=null
    var mLayoutManager : RecyclerView.LayoutManager?=null
    lateinit var albumViewModel : AlbumViewModel
     var list : ArrayList<Album> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = inflater.inflate(R.layout.albumphoto_layout,container,false)


        return view
    }



    companion object {
        fun newInstance() = AlbumsPhotosFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        //val recyclerView2 : RecyclerView = view.findViewById(R.id.childRecView)
        mLayoutManager = LinearLayoutManager(context)
        ParentRecView.setHasFixedSize(true)
        albumsAdapter = AlbumsRecyclerAdapter(list, context!!)
        ParentRecView.layoutManager = mLayoutManager
        ParentRecView.adapter = albumsAdapter


       albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        albumViewModel.getAlbums().observe(this, Observer {

            albumsAdapter!!.setAlbums(it)
        })


    }
}
