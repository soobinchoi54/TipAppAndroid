package com.example.myapplication.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Experience;
import com.squareup.picasso.Picasso;
import com.example.myapplication.R;

import java.util.List;
/**************************************************************************************************
 *              Adapter class for ViewHistoryActivity.java
 ***************************************************************************************************/
public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder> {
    private List<Experience> experiences;
    public HistoryListAdapter(List<Experience> experiences) { this.experiences = experiences; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRestaurant;
        TextView tvName;
        TextView tvLocation;
        TextView tvCategory;
        TextView tvPrice;
        TextView tvTotalBill;
        TextView tvTipPercentage;
        TextView tvTime;
        TextView tvService;
        TextView tvTimeliness;
        TextView tvFood;
        TextView tvCustom;



        ViewHolder(View itemView) {
            super(itemView);
            ivRestaurant = itemView.findViewById(R.id.ivRestaurant);
            tvName = itemView.findViewById(R.id.tvName);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTotalBill = itemView.findViewById(R.id.tvTotalBill);
            tvTipPercentage = itemView.findViewById(R.id.tvTipPercentage);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvService = itemView.findViewById(R.id.tvService);
            tvTimeliness = itemView.findViewById(R.id.tvTimeliness);
            tvFood = itemView.findViewById(R.id.tvFood);
            tvCustom = itemView.findViewById(R.id.tvCustom);

        }
    }

    public void setData(List<Experience> experiences){
        this.experiences = experiences;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HistoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListAdapter.ViewHolder holder, int position) {
        Experience experience = experiences.get(position);
        holder.tvName.setText(experience.getName());
        holder.tvLocation.setText("Location: " + experience.getLocation());
        holder.tvCategory.setText("Category: " + experience.getCategory());
        holder.tvPrice.setText("Price: " + experience.getPrice());
        holder.tvTotalBill.setText("Total Bill: " + experience.getTotalBill());
        holder.tvTipPercentage.setText("Tip Percentage: " + experience.getTipPercentage());
        holder.tvTime.setText("Time spent: " + experience.getTime());
        holder.tvService.setText("Service: " + experience.getService());
        holder.tvTimeliness.setText("Timeliness: " + experience.getTimeliness());
        holder.tvFood.setText("Food: " + experience.getFood());

        // getImage() path must not be empty -- Add alternate image when custom restaurant is entered when it is empty
        if (!(experience.getImage().isEmpty())) {
            Picasso.get()
                    .load(experience.getImage())
                    .fit()
                    .centerCrop()
                    .into(holder.ivRestaurant);
        } else {
            Picasso.get()
                    .load("https://i.imgur.com/DvpvklR.png")
                    .into(holder.ivRestaurant);
        }

        // add customized criteria and criteria rate
        String[] customs = experience.getCustom().split("#");
        String[] custom_rates = experience.getCUSTOM_RATE().split("#");
        if(customs[0].isEmpty()) return;
        StringBuilder customBuilder = new StringBuilder();
        for(int i = 0; i < customs.length; i++){
            customBuilder.append(customs[i].trim() + ": " + custom_rates[i].trim() + "\n");
        }
        String custom = customBuilder.toString();
        holder.tvCustom.setText(custom);

    }


    @Override
    public int getItemCount() {
        return experiences.size();
    }

}
