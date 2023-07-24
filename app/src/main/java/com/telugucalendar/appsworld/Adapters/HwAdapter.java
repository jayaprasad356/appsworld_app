package com.telugucalendar.appsworld.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.telugucalendar.appsworld.Fragments.panchangamFrag;
import com.telugucalendar.appsworld.Model.HomeCollection;
import com.telugucalendar.appsworld.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class HwAdapter extends BaseAdapter {
    private Activity context;

    private java.util.Calendar month;
    private java.util.Calendar year;
    public GregorianCalendar pmonth;
    /**
     * calendar instance for previous month for getting complete view
     */
    public GregorianCalendar pmonthmaxset;
    private GregorianCalendar selectedDate;
    public static int firstDay;
    int maxWeeknumber;
    int maxP;
    int calMaxP;
    int mnthlength;
    String itemvalue, curentDateString,curentMonth;
    DateFormat df;

    private ArrayList<String> items;
    public static List<String> day_string;
    public ArrayList<HomeCollection> date_collection_arr;
    private String gridvalue;
    public static TextView dayView;
    public static ImageView ampu;

    Typeface typefaceText;
    Typeface typeface;
    Dialog myDialog;

    public HwAdapter(Activity context, GregorianCalendar monthCalendar, ArrayList<HomeCollection> date_collection_arr) {
        this.date_collection_arr=date_collection_arr;
        HwAdapter.day_string = new ArrayList<String>();
        Locale.setDefault(Locale.US);
        month = monthCalendar;
        selectedDate = (GregorianCalendar) monthCalendar.clone();
        this.context = context;
        month.set(GregorianCalendar.DAY_OF_MONTH, 1);
        typefaceText = Typeface.createFromAsset(context.getAssets(),"fonts/arlrb.TTF");
        this.items = new ArrayList<String>();
        df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        curentDateString = df.format(selectedDate.getTime());
        refreshDays();

    }

    public int getCount() {
        return day_string.size();
    }

    public Object getItem(int position) {
        return day_string.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new view for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) { // if it's not recycled, initialize some
            // attributes
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.cal_item, null);
        }


        dayView = (TextView) v.findViewById(R.id.date);
        ampu = (ImageView) v.findViewById(R.id.ampuImage);
        ampu.setVisibility(View.GONE);
        String[] separatedTime = day_string.get(position).split("-");


        gridvalue = separatedTime[2].replaceFirst("^0*", "");
        //Log.d(gridvalue, "getView:gridvalue ");
        if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
            Log.d("DATE_CLICK","1");
            dayView.setTextColor(Color.TRANSPARENT);
            v.setOnClickListener(null);
            dayView.setOnClickListener(null);

        } else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
            Log.d("DATE_CLICK","2");
            dayView.setTextColor(Color.TRANSPARENT);
            v.setOnClickListener(null);
            dayView.setOnClickListener(null);

        } else {
            // setting curent month's days in blue color.
            dayView.setTextColor(Color.parseColor("#000000"));

        }

        if(position == 0 || position == 7 || position == 14|| position == 21|| position == 28 || position == 35)
        {
            if(position < firstDay)
            {
                if(Integer.parseInt(gridvalue) == 1)
                {
                    dayView.setTextColor(Color.parseColor("#ff005d"));
                }
            }else
            {
                dayView.setTextColor(Color.parseColor("#ff005d"));
            }
        }

        if (day_string.get(position).equals(curentDateString) || day_string.get(position).equals(panchangamFrag.selectedGridDate))
        {



            if(day_string.get(position).equals(curentDateString))
            {

               // Toast.makeText(context, ""+curentDateString, Toast.LENGTH_SHORT).show();

               //Toast.makeText(context, ""+day_string.get(position).equals(curentDateString), Toast.LENGTH_SHORT).show();
            // Toast.makeText(context, ""+firstDay, Toast.LENGTH_SHORT).show();




                if(month.get(GregorianCalendar.MONTH) == panchangamFrag.cutmonth)
                {
                    dayView.setTextColor(Color.parseColor("#0E2830"));
                    if(curentDateString.equals(panchangamFrag.selectedGridDate))
                    {
                        //clicked
                        v.setBackgroundResource(R.drawable.current_clicked);

                    }else
                    {
                        v.setBackgroundResource(R.drawable.current_day);

                    }

                }

            }else
            {


                if(((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) || ((Integer.parseInt(gridvalue) < 7) && (position > 28)))
                {


                }
                else
                {
                    dayView.setTextColor(Color.parseColor("#000000"));
                    v.setBackgroundResource(R.drawable.rounded_calender);

                }
            }

        }


        else {

            v.setBackgroundColor(Color.TRANSPARENT);
        }




        dayView.setText(gridvalue);
        dayView.setTypeface(typefaceText);

        // create date string for comparison
        String date = day_string.get(position);

        if (date.length() == 1) {
            date = "0" + date;
        }
        String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }

        setEventView(v, position,dayView);

        return v;
    }

    public void refreshDays() {
        // clear items
        items.clear();
        day_string.clear();
        Locale.setDefault(Locale.US);
        pmonth = (GregorianCalendar) month.clone();
        // month start day. ie; sun, mon, etc
        firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
        // finding number of weeks in current month.
        //maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
        // allocating maximum row number for the gridview.

        if(month.get(GregorianCalendar.YEAR) == 2022)
        {
            if(month.get(GregorianCalendar.MONTH) == 0 || month.get(GregorianCalendar.MONTH) == 6|| month.get(GregorianCalendar.MONTH) == 9)
            {
                mnthlength = 6 * 7;

            }else
            {
                mnthlength = 5 * 7;
            }

        }else if(month.get(GregorianCalendar.YEAR) == 2023)
        {
            if(month.get(GregorianCalendar.MONTH) == 3 || month.get(GregorianCalendar.MONTH) == 6|| month.get(GregorianCalendar.MONTH) == 11)
            {
                mnthlength = 6 * 7;

            }else
            {
                mnthlength = 5 * 7;
            }

        }else if(month.get(GregorianCalendar.YEAR) == 2024)
        {
            if(month.get(GregorianCalendar.MONTH) == 5)
            {
                mnthlength = 6 * 7;

            }else
            {
                mnthlength = 5 * 7;
            }
        }else
        {
            if(month.get(GregorianCalendar.MONTH) == 7)
            {
                mnthlength = 5 * 7;

            }else
            {
                mnthlength = 5 * 7;
            }
        }


        maxP = getMaxP(); // previous month maximum day 31,30....
        calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
        pmonthmaxset = (GregorianCalendar) pmonth.clone();
        pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

        for (int n = 0; n < mnthlength; n++) {

            itemvalue = df.format(pmonthmaxset.getTime());
            pmonthmaxset.add(GregorianCalendar.DATE, 1);
            day_string.add(itemvalue);

        }
    }

    private int getMaxP() {
        int maxP;
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            pmonth.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }
        maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        return maxP;
    }

    public void setEventView(View v, int pos, TextView txt){

        int len=HomeCollection.date_collection_arr.size();
        for (int i = 0; i < len; i++)
        {
            HomeCollection cal_obj=HomeCollection.date_collection_arr.get(i);
            String date=cal_obj.date;
            String name=cal_obj.description;
            int len1=day_string.size();
            if (len1>pos)
            {
                if (day_string.get(pos).equals(date))
                {

                    if ((Integer.parseInt(gridvalue) > 1) && (pos < firstDay)) {

                    } else if ((Integer.parseInt(gridvalue) < 7) && (pos > 28)) {

                    } else if(day_string.get(pos).equals(panchangamFrag.selectedGridDate))
                    {
                        v.setBackgroundResource(R.drawable.festival_clicked);

                    }else if(day_string.get(pos).equals(curentDateString))
                    {
                        v.setBackgroundResource(R.drawable.festival_currentday);

                    }else if(name.equals("Amayasya"))
                    {
                        ampu.setVisibility(View.VISIBLE);
                        //ampu.setBackgroundResource(R.drawable.amaasya);

                    }else if(name.equals("Purnami"))
                    {
                        ampu.setVisibility(View.VISIBLE);
                        ampu.setBackgroundResource(R.drawable.punnami);
                        //v.setBackgroundResource(R.drawable.paurnami);

                    }else
                    {
                        v.setBackgroundResource(R.drawable.festival_circle);
                        if(pos == 0 || pos == 7 || pos == 14|| pos == 21|| pos == 28 || pos == 35)
                        {
                            txt.setTextColor(Color.parseColor("#ff005d"));
                        }else
                        {
                            txt.setTextColor(Color.parseColor("#000000"));
                        }
                    }
                }
            }
        }
    }

    public void getPositionList(String date, final Activity act)
    {

        int len= HomeCollection.date_collection_arr.size();
        int resID;

        for (int j=0; j<len; j++)
        {
            if (HomeCollection.date_collection_arr.get(j).date.equals(date))
            {
                if(HomeCollection.date_collection_arr.get(j).description.equals("Amayasya") || HomeCollection.date_collection_arr.get(j).description.equals("Purnami"))
                {

                }else
                {

                }

            }
        }
    }
}
