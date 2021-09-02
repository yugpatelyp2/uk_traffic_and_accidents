package com.app.spotaccident.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.spotaccident.R;
import com.app.spotaccident.SubLevelActivity;
import com.app.spotaccident.model.DataPojo;
import com.app.spotaccident.utils.SquareImageView;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MyViewHolder> {
    private List<DataPojo> mListData;
    private Activity con;

    public MainListAdapter(Activity con, List<DataPojo> mListData) {
        this.con = con;
        this.mListData = mListData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_main_item,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DataPojo dataPojo = mListData.get(position);

        holder.txtTitle.setText(dataPojo.getTitle());
        if (position == 0) {
            holder.imageView.setImageDrawable(con.getResources().getDrawable(R.drawable.light_condition));
        } else if (position == 1) {
            holder.imageView.setImageDrawable(con.getResources().getDrawable(R.drawable.road_surface));
        } else if (position == 2) {
            holder.imageView.setImageDrawable(con.getResources().getDrawable(R.drawable.road_type));
        } else if (position == 3) {
            holder.imageView.setImageDrawable(con.getResources().getDrawable(R.drawable.area_type));
        } else if (position == 4) {
            holder.imageView.setImageDrawable(con.getResources().getDrawable(R.drawable.severity));
        } else if (position == 5) {
            holder.imageView.setImageDrawable(con.getResources().getDrawable(R.drawable.speed));
        } else {
            holder.imageView.setImageDrawable(con.getResources().getDrawable(R.drawable.weather_condition));
        }
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTitle;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(con, SubLevelActivity.class);
            intent.putExtra("data", mListData.get(getPosition()));
            con.startActivity(intent);
        }
    }
}
