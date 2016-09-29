package com.asbozh.fifthhomework;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {

    private final Context mContext;

    private ArrayList<Item> itemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvPrice;
        ImageView ivIcon;
        CardView cardView;


        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cv);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
        }
    }


    public RVAdapter(ArrayList<Item> itemList, Context context) {
        this.itemList = itemList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_row, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvDescription.setText(item.getDescription());
        holder.tvPrice.setText(String.valueOf(item.getPrice()));
        holder.ivIcon.setImageBitmap(loadImageFromAssets(item.getIcon()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private Bitmap loadImageFromAssets(String pic) {
        Bitmap  mBitmap = null;
        AssetManager assetManager = mContext.getAssets();
        InputStream is = null;
        try {
            is = assetManager.open(pic);
            mBitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mBitmap;
    }
}
