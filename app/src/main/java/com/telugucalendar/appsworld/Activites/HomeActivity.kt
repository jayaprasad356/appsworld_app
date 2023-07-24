package com.telugucalendar.appsworld.Activites


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.telugucalendar.appsworld.Fragments.MoreOptionsFrag
import com.telugucalendar.appsworld.Fragments.MuhurthaluFrag
import com.telugucalendar.appsworld.Fragments.PandugaluFrag
import com.telugucalendar.appsworld.Fragments.RashiPahlaluFrag
import com.telugucalendar.appsworld.R
import com.telugucalendar.appsworld.databinding.ActivityHomeBinding
import com.telugucalendar.appsworld.helper.Session

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class HomeActivity : AppCompatActivity() {

    private var activityHomeBinding: ActivityHomeBinding? = null
    private var adView: AdView? = null
    private var interstitial: InterstitialAd? = null
    private var handler: Handler? = null
    private var session: Session? = null
    private var i = 1

    companion object {
        var fm: FragmentManager? = null
        var navbar: BottomNavigationView? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding?.root)

        fm = supportFragmentManager
        navbar = activityHomeBinding?.BottomNavigation
        session = Session(applicationContext)
        fm?.beginTransaction()?.replace(
            R.id.Container,
            com.telugucalendar.appsworld.Fragments.panchangamFrag()
        )?.commit()

        MobileAds.initialize(this, getString(R.string.admob_app_id))
        adView = findViewById(R.id.AdView)
        val adRequest = AdRequest.Builder().build()
        adView?.loadAd(adRequest)

        interstitial = InterstitialAd(this)
        // interstitial?.adUnitId = "ca-app-pub-3940256099942544/1033173712" // Test Interstitial Ad Unit ID
        interstitial?.adUnitId = "ca-app-pub-5044124034987819/9441049479" // Live Interstitial Ad Unit ID
        interstitial?.loadAd(AdRequest.Builder().build())

        handler = Handler()
        handler?.postDelayed({
            displayInterstitial()
            loadInterstitial()
        }, 20000) // Load the interstitial after 30 seconds

        activityHomeBinding?.BottomNavigation?.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.Panchangam -> {
                    fm?.beginTransaction()?.replace(
                        R.id.Container,
                        com.telugucalendar.appsworld.Fragments.panchangamFrag()
                    )?.commit()
                    true
                }
                R.id.Pandugalu -> {
                    fm?.beginTransaction()?.replace(R.id.Container, PandugaluFrag())?.commit()
                    true
                }
                R.id.Muhurthalu -> {
                    fm?.beginTransaction()?.replace(R.id.Container, MuhurthaluFrag())?.commit()
                    true
                }
                R.id.RashiPhalalu -> {
                    fm?.beginTransaction()?.replace(R.id.Container, RashiPahlaluFrag())?.commit()
                    true
                }
                R.id.More -> {
                    fm?.beginTransaction()?.replace(R.id.Container, MoreOptionsFrag())?.commit()
                    true
                }
                else -> false
            }
        }
    }

    private fun loadInterstitial() {
        interstitial?.loadAd(AdRequest.Builder().build())
    }

    private fun displayInterstitial() {
        if (interstitial?.isLoaded == true) {
            interstitial?.show()
        }
    }

    override fun onDestroy() {
        handler?.removeCallbacksAndMessages(null) // Remove any pending tasks
        super.onDestroy()
    }
}
