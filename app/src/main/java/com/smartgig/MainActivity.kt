package com.smartgig

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.ui.*
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.smartgig.databinding.ActivityMainBinding
import com.smartgig.utils.SessionManager
import com.smartgig.ui.super_admin.home_page.HomeFragment
import com.smartgig.ui.super_admin.leave_history.LeaveHistoryFragment
import com.smartgig.ui.loginPage.LoginPage
import com.smartgig.ui.super_admin.paysleep.PaySleepFragment
import com.smartgig.ui.super_admin.request_leave.RequestLeaveFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //  ,NavigationView.OnNavigationItemSelectedListener

    private lateinit var binding: ActivityMainBinding

    //    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var toggle: ActionBarDrawerToggle

    //    private lateinit var navController: NavController
    private lateinit var navView: NavigationView
    private lateinit var drawerLayouts: DrawerLayout
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var toolbar: MaterialToolbar
//    private lateinit var navHostFragment: NavHostFragment


//    private val navController by lazy {
//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//
//        navHostFragment.navController
//    }
//    private val navController by lazy {
//        Navigation.findNavController(this, R.id.nav_host_fragment)
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        // set Toolbar
        toolbar = binding.topAppBar

        toolbar.apply{
            navigationIcon = (R.drawable.hamburger).toDrawable()


        }


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawerLayouts = binding.mainDrawer

        toggle =
            ActionBarDrawerToggle(
                this,
                drawerLayouts,
                toolbar,
                R.string.drawer_Open,
                R.string.drawer_Close
            )
        toggle.apply {
            isDrawerIndicatorEnabled = true
//            setHomeAsUpIndicator(R.drawable.hamburger)
            isDrawerSlideAnimationEnabled = true
        }
        drawerLayouts.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener(this)

//        val fragment = supportFragmentManager.beginTransaction()
//        fragment.replace(R.id.nav_host_fragment, HomeFragment()).commit()

        changeFragment(HomeFragment())
        drawerLayoutViewSetUp()



    }

    private fun drawerLayoutViewSetUp() {
        val headerView: View = binding.navigationView.getHeaderView(0)
        val header_Image = headerView.findViewById<ImageView>(R.id.header_imageView)
        val header_title = headerView.findViewById<TextView>(R.id.header_title)
        val header_role = headerView.findViewById<TextView>(R.id.header_role)

        val userName = SessionManager.getName(this)
        val userRole = SessionManager.getRole(this)
        val userImage = SessionManager.getImage(this)


        if (userName != null) {
            header_title.text = userName
        }


        if (userRole != null) {
            header_role.text =  userRole
        }

        Glide.with(this)
            .load(userImage)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)

            .into(header_Image)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayouts.closeDrawer(GravityCompat.START)
        if (item.itemId == R.id.nav_home) {
            supportActionBar?.setDisplayShowTitleEnabled(false)
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
            changeFragment(HomeFragment())
        }else if (item.itemId == R.id.nav_reqLeave) {
            supportActionBar?.title=""
            Toast.makeText(this, "Request Leave", Toast.LENGTH_SHORT).show()
            changeFragment(RequestLeaveFragment())
        } else if (item.itemId == R.id.nav_leaveHistory) {

            Toast.makeText(this, "Request Leave", Toast.LENGTH_SHORT).show()
            changeFragment(LeaveHistoryFragment())

        } else if (item.itemId == R.id.nav_paySleep) {

            Toast.makeText(this, "Pay Sleep", Toast.LENGTH_SHORT).show()
            changeFragment(PaySleepFragment())

        } else if (item.itemId == R.id.nav_notification) {

            Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            SessionManager.clearData(this)
//            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
//            this.findNavController().navigate(action)
            val intent = Intent(this, LoginPage::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }
        return true
    }

    private fun changeFragment(frag: Fragment) {
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.nav_host_fragment, frag).commit()
    }



}


