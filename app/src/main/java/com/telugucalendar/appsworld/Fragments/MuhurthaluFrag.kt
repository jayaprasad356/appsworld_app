package com.telugucalendar.appsworld.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.telugucalendar.appsworld.Adapters.MuhurthaluAdapter
import com.telugucalendar.appsworld.R
import com.telugucalendar.appsworld.databinding.FragmentMuhurthaluBinding
import com.telugucalendar.appsworld.helper.DatabaseHelper
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MuhurthaluFrag : Fragment() {


    var month_year =""
    var select_option ="1"
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
    var databaseHelper: DatabaseHelper? = null
    private var mAdView: AdView? = null
    private  var mAdView2:AdView? = null



    private lateinit var binding : FragmentMuhurthaluBinding
    private lateinit var MuhurthaluAdapter : MuhurthaluAdapter


    // This is the part of BottomNavigation
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMuhurthaluBinding.inflate(layoutInflater,container,false)




//        mAdView = binding.root.findViewById<AdView>(R.id.adView1)
//        mAdView2 = binding.root.findViewById<AdView>(R.id.adView2)
//        val adRequest = AdRequest.Builder().build()
//        mAdView!!.loadAd(adRequest)
//        mAdView2!!.loadAd(adRequest)

        //Date logic goes here
        cal.add(Calendar.MONTH, monthcount)
        val dateFormat = SimpleDateFormat("MMMM yyyy")
        month_year = dateFormat.format(cal.getTime())
        year = cal[Calendar.YEAR].toString()
        databaseHelper = DatabaseHelper(activity)


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


                muhurthamTabList(select_option,getMonthNum()!!,getYearNum()!!)

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

                muhurthamTabList(select_option,getMonthNum()!!,getYearNum()!!)





            }


        }



        binding.MuhurthaluRecycler.layoutManager = LinearLayoutManager(requireActivity())
        binding.OptionOne.setOnClickListener {
            unselectall();
            binding.OptionOne.setBackgroundResource(R.drawable.select_bg)
            select_option ="1"
            muhurthamTabList(select_option,getMonthNum()!!,getYearNum()!!)

        }
        binding.OptionTwo.setOnClickListener {
            unselectall();
            binding.OptionTwo.setBackgroundResource(R.drawable.select_bg)
            select_option ="2"
            muhurthamTabList(select_option,getMonthNum()!!,getYearNum()!!)
        }
        binding.OptionThree.setOnClickListener {
            unselectall();
            binding.OptionThree.setBackgroundResource(R.drawable.select_bg)
            select_option ="3"
            muhurthamTabList(select_option,getMonthNum()!!,getYearNum()!!)
        }
        binding.OptionFour.setOnClickListener {
            unselectall();
            binding.OptionFour.setBackgroundResource(R.drawable.select_bg)
            select_option ="4"
            muhurthamTabList(select_option,getMonthNum()!!,getYearNum()!!)
        }

        muhurthamTabList(select_option,getMonthNum()!!,getYearNum()!!)

        //Log.d("MURUTHAM_SIZE", databaseHelper!!.getMuhurthamTabList("1","01","2023").size.toString())

        return binding.root
    }

    private fun unselectall() {
        binding.OptionOne.setBackgroundResource(R.drawable.unselect_bg)
        binding.OptionTwo.setBackgroundResource(R.drawable.unselect_bg)
        binding.OptionThree.setBackgroundResource(R.drawable.unselect_bg)
        binding.OptionFour.setBackgroundResource(R.drawable.unselect_bg)
    }

    private fun muhurthamTabList(mid: String,monthNum: String, yearNum: String) {

        Log.d("MURUTHAM_LIST_", select_option + " - "+getMonthNum()!!+ " - "+getYearNum()!!)

        if (databaseHelper!!.getMuhurthamTabList(mid,monthNum, yearNum).size != 0) {
            binding.MuhurthaluRecycler!!.visibility = View.VISIBLE
            MuhurthaluAdapter = MuhurthaluAdapter(
                databaseHelper!!.getMuhurthamTabList(mid,monthNum, yearNum)
            )
            binding.MuhurthaluRecycler!!.adapter = MuhurthaluAdapter
        } else {
            binding.MuhurthaluRecycler!!.visibility = View.GONE
        }
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





    private fun setTeluguMonth(month_year: String): String? {
        val index = month_year.indexOf(' ')
        val month = month_year.substring(0, index)
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
}