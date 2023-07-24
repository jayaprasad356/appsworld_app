package com.telugucalendar.appsworld.Fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.telugucalendar.appsworld.Adapters.FestivalAdapter
import com.telugucalendar.appsworld.databinding.FragmentPandugaluBinding
import com.telugucalendar.appsworld.helper.DatabaseHelper
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PandugaluFrag : Fragment() {
    private var binding : FragmentPandugaluBinding? = null;
    var imgLeft: ImageView? = null
    var imgRight: ImageView? = null
    var tvMonthYear: TextView? = null
    lateinit var month_year : String
    var monthcount = 0
    var cal = Calendar.getInstance()
    var df = SimpleDateFormat("MMMM yyyy")
    var c = Calendar.getInstance()
    var databaseHelper: DatabaseHelper? = null
    var activity: Activity? = null
    var festivalAdapter: FestivalAdapter? = null
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


    @SuppressLint("SimpleDateFormat", "MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {


        // Inflate the layout for this fragment
        binding = FragmentPandugaluBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment





        activity = getActivity()

        databaseHelper = DatabaseHelper(activity)




        binding!!.recyclerView.setLayoutManager(
            LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
        )


        cal.add(Calendar.MONTH, monthcount)
        val dateFormat = SimpleDateFormat("MMMM yyyy")
        month_year = dateFormat.format(cal.time)
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


//                festivalList(getMonthNum()!!, getYearNum()!!)

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

                festivalList(getMonthNum()!!, getYearNum()!!)

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



                festivalList(getMonthNum()!!, getYearNum()!!)

            }

        }


     festivalList(getMonthNum()!!, getYearNum()!!)



        return binding!!.root
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


    private fun festivalList(monthNum: String, yearNum: String) {
        if (databaseHelper!!.getmodelFestivalList(monthNum, yearNum).size !== 0) {
          //  Log.d("festival", databaseHelper!!.getmodelFestivalList(monthNum, yearNum).toString())
            festivalAdapter = FestivalAdapter(requireActivity(),
                databaseHelper!!.getmodelFestivalList(monthNum, yearNum)
            )
            binding!!.recyclerView!!.adapter = festivalAdapter
        } else {
            Toast.makeText(activity,"No Data",Toast.LENGTH_SHORT).show()
            binding!!.recyclerView!!.visibility = View.GONE
        }
    }




}