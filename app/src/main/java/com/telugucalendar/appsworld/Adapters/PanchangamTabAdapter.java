package com.telugucalendar.appsworld.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.telugucalendar.appsworld.Model.PanchangamTab;
import com.telugucalendar.appsworld.R;


import java.util.ArrayList;


public class PanchangamTabAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final Activity activity;
    ArrayList<PanchangamTab> panchangamTabs;

    private static final int VIEW_TYPE_AD = 1;
    private static final int VIEW_TYPE_ITEM = 0;

    public PanchangamTabAdapter(Activity activity, ArrayList<PanchangamTab> panchangamTabs) {
        this.activity = activity;
        this.panchangamTabs = panchangamTabs;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.panchangam_tab_layout, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {



        final ExploreItemHolder itemHolder = (ExploreItemHolder) holder;
        final PanchangamTab panchangamTab = panchangamTabs.get(position);

        itemHolder.tvTitle.setText(panchangamTab.getTitle());
          itemHolder.tvContent.setText(panchangamTab.getDescription());

//        if ( position == 3) {
//
//
//            itemHolder.layout.setVisibility(View.GONE);
//
//            AdView adView = new AdView(activity);
//            ViewGroup parent = (ViewGroup) holder.itemView;
//            parent.addView(adView);
//            adView.setAdSize(AdSize.SMART_BANNER);
//            String adUnitId = activity.getResources().getString(R.string.panchangambanner2);
//            adView.setAdUnitId(adUnitId);
//            AdRequest adRequest = new AdRequest.Builder().build();
//            adView.loadAd(adRequest);
//
//        }



//
//        else {
//
//            itemHolder.tvTitle.setText(panchangamTab.getTitle());
//            itemHolder.tvContent.setText(panchangamTab.getDescription());
//        }
    }

    @Override
    public int getItemCount()
    {

        return panchangamTabs.size() ;
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle,tvContent;
        final LinearLayout layout;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
