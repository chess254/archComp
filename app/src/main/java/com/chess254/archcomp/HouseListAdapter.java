package com.chess254.archcomp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chess254.archcomp.Models.House;

import java.util.List;

/**
 * Created by chess on 10/24/2018.
 */

public class HouseListAdapter extends RecyclerView.Adapter <HouseListAdapter.HouseViewHolder>{

    private final LayoutInflater mInflater;
    private List<House> mHouses;

    HouseListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }


    class HouseViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewArea;
        private TextView textViewAddreaa;
        private ImageView imageViewHouse;

        public HouseViewHolder(View itemView) {
            super(itemView);

            textViewArea = itemView.findViewById(R.id.area_house);
            textViewAddreaa = itemView.findViewById(R.id.location_house);
            imageViewHouse = itemView.findViewById(R.id.image_house);
        }
    }
    @Override
    public HouseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.recyclerview_house, parent, false);
        return new HouseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HouseListAdapter.HouseViewHolder holder, int position) {

        if(mHouses != null) {
            House current = mHouses.get(position);
            holder.textViewArea.setText(current.getAreaHouse());
            holder.textViewAddreaa.setText(current.getLocationHouse());
            holder.imageViewHouse.setImageResource(R.mipmap._house);
        } else {
            holder.textViewArea.setText("no houses");
        }
    }

    void setmHouses(List<House> houses){
        mHouses = houses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mHouses != null)
            return mHouses.size();
        else
            return 0;
    }
}
