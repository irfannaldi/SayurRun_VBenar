package com.example.sayurrun_vbenar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.sayurrun_vbenar.fragments.BerandaFragment
import com.example.sayurrun_vbenar.fragments.PesananFragment
import com.example.sayurrun_vbenar.fragments.ProfilFragment
import com.example.sayurrun_vbenar.fragments.SayuranFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val berandaFragment = BerandaFragment()
    private val sayuranFragment = SayuranFragment()
    private val pesananFragment = PesananFragment()
    private val profilFragment = ProfilFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(berandaFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.mn_beranda -> replaceFragment(berandaFragment)
                R.id.mn_sayuran -> replaceFragment(sayuranFragment)
                R.id.mn_pesanan -> replaceFragment(pesananFragment)
                R.id.mn_profil -> replaceFragment(profilFragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        if(fragment!=null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}