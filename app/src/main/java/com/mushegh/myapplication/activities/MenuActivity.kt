package com.mushegh.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.mushegh.myapplication.R
import com.mushegh.myapplication.data.models.Contact
import com.mushegh.myapplication.fragments.AlbumsPhotosFragment
import com.mushegh.myapplication.fragments.ContactsFragment
import com.mushegh.myapplication.fragments.UsersFragment
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {




    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.nav_users)
        {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,UsersFragment.newInstance()).commit()
        }
        else if(item.itemId == R.id.Page1)
        {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,AlbumsPhotosFragment.newInstance()).commit()

        }
        else if(item.itemId == R.id.Page2)
        {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,ContactsFragment.newInstance()).commit()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START))
        {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else
        {
            super.onBackPressed()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        setSupportActionBar(toolbar)

       var toggle : ActionBarDrawerToggle = ActionBarDrawerToggle(this,drawer_layout,toolbar,
           R.string.navigation_drawer_open,
           R.string.navigation_drawer_close
       )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()



        nav_view.setNavigationItemSelectedListener(this)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UsersFragment()).commit()

            nav_view.setCheckedItem(R.id.nav_users)
        }


    }

}
