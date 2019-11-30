package com.example.mymail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        val direcciones = arrayOf("jesusruiperez@gmail.com", "jerual@hotmail.com", "jesus@yahoo.es", "jejabato@gmail.com", "jejeje@hotmail.com")

        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, direcciones)

        val navView = findViewById(R.id.nav_view) as NavigationView

        //Accedemos al layout que hace de cabecera sobre el NavigationView
        val header =  navView.getHeaderView(0) as View

        //Buscamos el spinner
        val list_direcciones = header.findViewById(R.id.id_spinner) as Spinner

        list_direcciones.adapter = adaptador
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)




        navView.setNavigationItemSelectedListener(object :  NavigationView.OnNavigationItemSelectedListener {

            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                var fragmentTransaction = false
                var fragment: Fragment? = null

                when (menuItem.getItemId()) {
                    R.id.menu_Inbox -> {
                        fragment = Fragment1()
                        fragmentTransaction = true
                    }
                    R.id.menu_Outbox -> {
                        fragment = Fragment2()
                        fragmentTransaction = true
                    }
                    R.id.menu_Trash -> {
                        fragment = Fragment3()
                        fragmentTransaction = true
                    }
                    R.id.menu_Spam -> {
                        fragment = Fragment4()
                        fragmentTransaction = true
                    }
                }
                if (fragmentTransaction) {
                    supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment!!).commit()
                    menuItem.setChecked(true)
                    supportActionBar!!.title = menuItem.title
                }
                drawerLayout.closeDrawers()
                return true
            }


        })
        fun onOptionsItemSelected(item: MenuItem): Boolean {
            val drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
            when (item.itemId) {
                android.R.id.home -> {
                    drawerLayout.openDrawer(GravityCompat.START)
                    return true
                }
            }
            return super.onOptionsItemSelected(item)
        }


    }
}
