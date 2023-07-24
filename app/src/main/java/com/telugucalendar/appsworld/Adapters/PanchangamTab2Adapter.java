package com.telugucalendar.appsworld.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.telugucalendar.appsworld.Model.PanchangamTab;
import com.telugucalendar.appsworld.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PanchangamTab2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final Activity activity;
    ArrayList<PanchangamTab> panchangamTabs;
    private List<String> data;


    public List<String> getDataAfterTwo() {
        if (data == null) {
            return Collections.emptyList();
        }
        return data.subList(2, data.size());
    }

    public PanchangamTab2Adapter(Activity activity, ArrayList<PanchangamTab> panchangamTabs) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final PanchangamTab panchangamTab = panchangamTabs.get(position);
        holder.tvTitle.setText(panchangamTab.getTitle());
        holder.tvContent.setText(panchangamTab.getDescription());


    }

    @Override
    public int getItemCount()
    {
        return getDataAfterTwo().size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle,tvContent;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }
}
