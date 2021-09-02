package com.app.spotaccident.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.spotaccident.MapActivity;
import com.app.spotaccident.R;
import com.app.spotaccident.model.TypePojo;

import java.util.List;

public class SubListAdapter extends RecyclerView.Adapter<SubListAdapter.MyViewHolder> {
    private List<TypePojo> mListData;
    private Activity con;

    public SubListAdapter(Activity con, List<TypePojo> mListData) {
        this.con = con;
        this.mListData = mListData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_sub_item,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final TypePojo typePojo = mListData.get(position);

        holder.txtTitle.setText(typePojo.getTitle());
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(con, MapActivity.class);
            intent.putExtra("data", mListData.get(getPosition()));
            con.startActivity(intent);
        }
    }
}
