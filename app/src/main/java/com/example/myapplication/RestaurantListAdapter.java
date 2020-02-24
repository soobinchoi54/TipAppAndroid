package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder> {
    private List<Restaurant> restaurantData;

    RestaurantListAdapter(List<Restaurant> data) {
        this.restaurantData = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //must take into account all views within movie_row.xml

        //ImageView iv;
        TextView tvName;
        TextView tvAddress;
        TextView tvPrice;


        ViewHolder(View itemView) {
            super(itemView);
            //iv = itemView.findViewById(R.id.ivMovie);
            tvName = itemView.findViewById(R.id.restaurantName);
            tvAddress = itemView.findViewById(R.id.restaurantAddress);
            tvPrice = itemView.findViewById(R.id.restaurantPrice);

        }
    }


    @Override
    public RestaurantListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_row, parent, false);
        return new RestaurantListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantListAdapter.ViewHolder holder, int position) {
        Restaurant restaurant = restaurantData.get(position);

        /*//get current poster path to add to poster base path
        poster_path = poster_base + program.getPosterPath();
        //set image view to full poster path image using picasso
        Picasso.get().load(poster_path).into(holder.iv);*/

        //Set all text views with movie data
        holder.tvName.setText(restaurant.getName());
        holder.tvAddress.setText(restaurant.getAddress());
        holder.tvPrice.setText(restaurant.getPrice());

    }

    @Override
    public int getItemCount() { return 15; }
}
