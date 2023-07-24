package com.telugucalendar.appsworld.Fragments

import android.annotation.SuppressLint
import android.app.AlertDialog

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.*
import com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks
import com.google.android.gms.ads.formats.MediaView
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import com.google.gson.Gson
import com.telugucalendar.appsworld.databinding.FragmentRashiPahlaluBinding


import com.telugucalendar.appsworld.helper.ApiConfig
import com.telugucalendar.appsworld.helper.Constant
import org.json.JSONArray
import org.json.JSONObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class RashiPahlaluFrag : Fragment() {

    var month_year =""
    var month =""
    var year =""
    var montharray = arrayOf(
        "జనవరి ",
        "ఫిబ్రవరి ",
        "మార్చి ",
        "ఏప్రిల్ ",
        "మే ",
        "జూన్ ",
        "జూలై ",
        "ఆగస్టు ",
        "సెప్టెంబర్ ",
        "అక్టోబర్ ",
        "నవంబర్ ",
        "డిసెంబర్ "
    )
    var c = Calendar.getInstance()
    var df = SimpleDateFormat("MMMM yyyy")
    var monthcount = 0
    var cal = Calendar.getInstance()
    private var mAdView: AdView? = null
    private val ADMOB_AD_UNIT_ID = "ca-app-pub-1838677544163695/9583700181"

    private var refresh: Button? = null
    private var startVideoAdsMuted: CheckBox? = null
    private var videoStatus: TextView? = null
    private var nativeAd: UnifiedNativeAd? = null



    private lateinit var binding : FragmentRashiPahlaluBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRashiPahlaluBinding.inflate(layoutInflater, container, false)


        // Initialize the Mobile Ads SDK.

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(
            activity
        ) { }

        refresh = binding.root.findViewById<Button>(com.telugucalendar.appsworld.R.id.btn_refresh)
        startVideoAdsMuted = binding.root.findViewById<CheckBox>(com.telugucalendar.appsworld.R.id.cb_start_muted)
        videoStatus = binding.root.findViewById<TextView>(com.telugucalendar.appsworld.R.id.tv_video_status)

        refresh!!.setOnClickListener { refreshAd() }

        refreshAd()




        //Date logic goes here
        cal.add(Calendar.MONTH, monthcount)
        val dateFormat = SimpleDateFormat("MMMM yyyy")
        month_year = dateFormat.format(cal.getTime())
        year = cal[Calendar.YEAR].toString()


        binding!!.PresentMonthAndYear.setText(setTeluguMonth(month_year)+ year)
        binding!!.ArrowLeft.setOnClickListener {

            if (getYearNum().equals("2023")&&getMonthNum().equals("06")){

//                cal.add(Calendar.MONTH, monthcount)
//                val dateFormat = SimpleDateFormat("MMMM yyyy")
//                month_year = dateFormat.format(cal.time)
//                year = cal[Calendar.YEAR].toString()

                Toast.makeText(context, "ప్రదర్శించడానికి డేటా లేదు", Toast.LENGTH_SHORT).show()



                binding!!.PresentMonthAndYear.setText(setTeluguMonth(month_year)+ year)



            }


            else{

                var dateFormat: Date? = null
                try {
                    dateFormat = df.parse(month_year)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                c.setTime(dateFormat)
                c.add(Calendar.MONTH, -1)


                month_year = df.format(c.getTime())
                year = c[Calendar.YEAR].toString()

                binding!!.PresentMonthAndYear.setText(setTeluguMonth(month_year)+ year)



            }


        }

        binding!!.ArrowRight.setOnClickListener {

            if (getYearNum().equals("2024")&&getMonthNum().equals("12")){

                binding!!.PresentMonthAndYear.setText(setTeluguMonth(month_year)+ year)
                Toast.makeText(context, "ప్రదర్శించడానికి డేటా లేదు", Toast.LENGTH_SHORT).show()

            }
            else{
                var dateFormat: Date? = null
                try {
                    dateFormat = df.parse(month_year)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                c.time = dateFormat
                c.add(Calendar.MONTH, 1)
                month_year = df.format(c.time)
                year = c[Calendar.YEAR].toString()
                binding!!.PresentMonthAndYear.setText(setTeluguMonth(month_year)+ year)





            }

        }




        binding.llAries.setOnClickListener {

            var rashi_id = 1
            var rashi_year = year
            var rashi_month = month
            rasi(rashi_id, rashi_year, rashi_month)


        }
        binding.llTaurus.setOnClickListener {

            var rashi_id = 2
            var rashi_year = year
            var rashi_month = month
            rasi(rashi_id,rashi_year,rashi_month)


        }
        binding.llGemini.setOnClickListener {

            var rashi_id = 3
            var rashi_year = year
            var rashi_month = month
            rasi(rashi_id,rashi_year,rashi_month)


        }
        binding.llCancer.setOnClickListener {

            var rashi_id = 4
            var rashi_year = year
            var rashi_month = month
            rasi(rashi_id,rashi_year,rashi_month)


        }
        binding.llLeo.setOnClickListener {

            var rashi_id = 5
            var rashi_year = year
            var rashi_month = month
            rasi(rashi_id,rashi_year,rashi_month)


        }
        binding.llVirgo.setOnClickListener {

            var rashi_id = 6
            var rashi_year = year
            var rashi_month = month
            rasi(rashi_id,rashi_year,rashi_month)


        }
        binding.llLibra.setOnClickListener {

            var rashi_id = 7
            var rashi_year = year
            var rashi_month = month
            rasi(rashi_id,rashi_year,rashi_month)


        }
        binding.llScorpio.setOnClickListener {

            var rashi_id = 8
            var rashi_year = year
            var rashi_month = month
            rasi(rashi_id,rashi_year,rashi_month)


        }
        binding.llSagittarius.setOnClickListener {

            var rashi_id = 9
            var rashi_year = year
            var rashi_month = month
            rasi(rashi_id,rashi_year,rashi_month)


        }
        binding.llCapricorn.setOnClickListener {

            var rashi_id = 10
            var rashi_year = year
            var rashi_month = month
            rasi(rashi_id,rashi_year,rashi_month)


        }
        binding.llAquarius.setOnClickListener {

            var rashi_id = 11
            var rashi_year = year
            var rashi_month = month
            rasi(rashi_id,rashi_year,rashi_month)


        }
        binding.llPisces.setOnClickListener {

            var rashi_id = 12
            var rashi_year = year
            var rashi_month = month
            rasi(rashi_id,rashi_year,rashi_month)


        }

        return binding.root
    }

    @SuppressLint("MissingInflatedId")
    private fun showDialog(description: String) {
        val builder = AlertDialog.Builder(activity,com.telugucalendar.appsworld.R.style.CustomAlertDialog).create()
        val view = layoutInflater.inflate(com.telugucalendar.appsworld.R.layout.rashi_layout,null)
        val  button = view.findViewById<ImageButton>(com.telugucalendar.appsworld.R.id.dialogDismiss_button)
        val  tvdescription = view.findViewById<TextView>(com.telugucalendar.appsworld.R.id.tvdescription)
        val  tvdate = view.findViewById<TextView>(com.telugucalendar.appsworld.R.id.tvdate)
        val date = binding!!.PresentMonthAndYear.text.toString()
        builder.setView(view)
        button.setOnClickListener { builder.dismiss() }
        tvdescription.setText(description)
        tvdate.setText(date)
        builder.show()
        builder.setCanceledOnTouchOutside(true);
    }




    private fun setTeluguMonth(month_year: String): String? {
        val index = month_year.indexOf(' ')
        month = month_year.substring(0, index)
        val p = Arrays.asList(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        ).indexOf(month)
        return montharray[p]
    }





    private fun rasi(rashi_id: Int, rashi_year: String, rashi_month: String) {
        val params = HashMap<String, String>()
        params[Constant.RASHI_ID] = rashi_id.toString()
        params[Constant.YEAR] = rashi_year
        params[Constant.MONTH] = rashi_month
        ApiConfig.RequestToVolley({ result, response ->
            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {

                        Log.d("Rasho",response)
                        val jsonArray: JSONArray = jsonObject.getJSONArray(Constant.DATA)
                        val g = Gson()


                        for (i in 0 until jsonArray.length()) {
                            val jsonObject1 = jsonArray.getJSONObject(i)
                            if (jsonObject1 != null) {

                                val description: String = jsonArray.getJSONObject(0).getString(Constant.DESCRIPTION)
                                val rashi_id: String = jsonArray.getJSONObject(0).getString(Constant.RASHI_ID)
                                val title: String = jsonArray.getJSONObject(0).getString(Constant.RASHI_ID)

                                showDialog(description)



                            } else {
                                Toast.makeText(activity,"No Data",Toast.LENGTH_SHORT).show()
                                break
                            }
                        }
//                        val adapter = BallisastramAdapter(requireActivity(), balliData)
//                        binding!!.recyclerView.setAdapter(adapter)
                    } else {
                        Toast.makeText(
                            activity,
                            jsonObject.getString(Constant.MESSAGE),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }, activity, Constant.RASI_LIST, params, true)

    }


    private fun getMonthNum(): String? {
        var newDate: Date? = null
        try {
            newDate = df.parse("" + month_year)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val format = SimpleDateFormat("MM")
        return format.format(newDate)
    }

    private fun getYearNum(): String? {
        var newDate: Date? = null
        try {
            newDate = df.parse("" + month_year)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val format = SimpleDateFormat("yyyy")
        return format.format(newDate)
    }


    private fun refreshAd() {
        refresh!!.isEnabled = false
        val builder: AdLoader.Builder = AdLoader.Builder(requireActivity(),ADMOB_AD_UNIT_ID)
        builder.forUnifiedNativeAd(
            OnUnifiedNativeAdLoadedListener { unifiedNativeAd ->
                // OnUnifiedNativeAdLoadedListener implementation.
                // If this callback occurs after the activity is destroyed, you must call
                // destroy and return or you may get a memory leak.
                var isDestroyed: Boolean = false
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        isDestroyed = isDestroyed
                    }
                    if (isDestroyed || requireActivity().isFinishing() || requireActivity().isChangingConfigurations()) {
                        unifiedNativeAd.destroy()
                        return@OnUnifiedNativeAdLoadedListener
                    }
                    if (nativeAd != null) {
                        nativeAd!!.destroy()
                    }

                    nativeAd = unifiedNativeAd
                    val frameLayout: FrameLayout = binding.root.findViewById<FrameLayout>(com.telugucalendar.appsworld.R.id.fl_adplaceholder)
                    val adView = layoutInflater
                        .inflate(com.telugucalendar.appsworld.R.layout.ad_unified, null) as UnifiedNativeAdView
                    populateUnifiedNativeAdView(unifiedNativeAd, adView)
                    frameLayout.removeAllViews()
                    frameLayout.addView(adView)
                } catch (e: Exception) {
                    Log.e("ERROR", "An error occurred while destroying the ad: ${e.message}")
                }


            })
        val videoOptions =
            VideoOptions.Builder().setStartMuted(startVideoAdsMuted!!.isChecked).build()
        val adOptions = NativeAdOptions.Builder().setVideoOptions(videoOptions).build()
        builder.withNativeAdOptions(adOptions)
        val adLoader = builder
            .withAdListener(
                object : AdListener() {
                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        refresh!!.isEnabled = true
                        val error = String.format(
                            "domain: %s, code: %d, message: %s",
                            loadAdError.domain,
                            loadAdError.code,
                            loadAdError.message
                        )

                    }
                })
            .build()
        adLoader.loadAd(AdRequest.Builder().build())
        videoStatus!!.text = ""
    }

    override fun onDestroy() {
        if (nativeAd != null) {
            nativeAd!!.destroy()
        }
        super.onDestroy()
    }

    private fun populateUnifiedNativeAdView(
        nativeAd: UnifiedNativeAd,
        adView: UnifiedNativeAdView
    ) {
        // Set the media view.
        adView.mediaView = adView.findViewById<View>(com.telugucalendar.appsworld.R.id.ad_media) as MediaView

        // Set other ad assets.
        adView.headlineView = adView.findViewById(com.telugucalendar.appsworld.R.id.ad_headline)
        adView.bodyView = adView.findViewById(com.telugucalendar.appsworld.R.id.ad_body)
        adView.callToActionView = adView.findViewById(com.telugucalendar.appsworld.R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(com.telugucalendar.appsworld.R.id.ad_app_icon)
        adView.priceView = adView.findViewById(com.telugucalendar.appsworld.R.id.ad_price)
        adView.starRatingView = adView.findViewById(com.telugucalendar.appsworld.R.id.ad_stars)
        adView.storeView = adView.findViewById(com.telugucalendar.appsworld.R.id.ad_store)
        adView.advertiserView = adView.findViewById(com.telugucalendar.appsworld.R.id.ad_advertiser)

        // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
        (adView.headlineView as TextView).text = nativeAd.headline
        adView.mediaView.setMediaContent(nativeAd.mediaContent)

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.body == null) {
            adView.bodyView.visibility = View.INVISIBLE
        } else {
            adView.bodyView.visibility = View.VISIBLE
            (adView.bodyView as TextView).text = nativeAd.body
        }
        if (nativeAd.callToAction == null) {
            adView.callToActionView.visibility = View.INVISIBLE
        } else {
            adView.callToActionView.visibility = View.VISIBLE
            (adView.callToActionView as Button).text = nativeAd.callToAction
        }
        if (nativeAd.icon == null) {
            adView.iconView.visibility = View.GONE
        } else {
            (adView.iconView as ImageView).setImageDrawable(
                nativeAd.icon.drawable
            )
            adView.iconView.visibility = View.VISIBLE
        }
        if (nativeAd.price == null) {
            adView.priceView.visibility = View.INVISIBLE
        } else {
            adView.priceView.visibility = View.VISIBLE
            (adView.priceView as TextView).text = nativeAd.price
        }
        if (nativeAd.store == null) {
            adView.storeView.visibility = View.INVISIBLE
        } else {
            adView.storeView.visibility = View.VISIBLE
            (adView.storeView as TextView).text = nativeAd.store
        }
        if (nativeAd.starRating == null) {
            adView.starRatingView.visibility = View.INVISIBLE
        } else {
            (adView.starRatingView as RatingBar).rating = nativeAd.starRating.toFloat()
            adView.starRatingView.visibility = View.VISIBLE
        }
        if (nativeAd.advertiser == null) {
            adView.advertiserView.visibility = View.INVISIBLE
        } else {
            (adView.advertiserView as TextView).text = nativeAd.advertiser
            adView.advertiserView.visibility = View.VISIBLE
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd)

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        val vc = nativeAd.videoController

        // Updates the UI to say whether or not this ad has a video asset.
        if (vc.hasVideoContent()) {
            videoStatus!!.text = String.format(
                Locale.getDefault(),
                "Video status: Ad contains a %.2f:1 video asset.",
                vc.aspectRatio
            )

            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.videoLifecycleCallbacks = object : VideoLifecycleCallbacks() {
                override fun onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
                    refresh!!.isEnabled = true
                    videoStatus!!.text = "Video status: Video playback has ended."
                    super.onVideoEnd()
                }
            }
        } else {
            videoStatus!!.text = "Video status: Ad does not contain a video asset."
            refresh!!.isEnabled = true
        }
    }




}