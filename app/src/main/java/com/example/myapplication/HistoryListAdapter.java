package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
/**************************************************************************************************
 *              Adapter class for ViewHistoryActivity.java
 ***************************************************************************************************/
public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder> {
    private List<Experience> experiences;
    public HistoryListAdapter(List<Experience> experiences) { this.experiences = experiences; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvLocation;
        TextView tvCategory;
        TextView tvPrice;
        TextView tvTotalBill;
        TextView tvTipPercentage;
        TextView tvTime;
        TextView tvCritera1;
        TextView tvCritera2;
        TextView tvCritera3;
        TextView tvCustom;



        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTotalBill = itemView.findViewById(R.id.tvTotalBill);
            tvTipPercentage = itemView.findViewById(R.id.tvTipPercentage);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvCritera1 = itemView.findViewById(R.id.tvCritera1);
            tvCritera2 = itemView.findViewById(R.id.tvCritera2);
            tvCritera3 = itemView.findViewById(R.id.tvCritera3);
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
        holder.tvCritera1.setText("Critera1: " + experience.getCriteria1());
        holder.tvCritera2.setText("Critera2: " + experience.getCriteria2());
        holder.tvCritera3.setText("Critera3: " + experience.getCriteria3());
        String[] customs = experience.getCustom().split("#");
        String[] custom_rates = experience.getCUSTOM_RATE().split("#");
        StringBuilder customBuilder = new StringBuilder();
        for(int i = 0; i < customs.length; i++){
            customBuilder.append(customs[i].trim() + ": " + custom_rates[i].trim() + "\n");
        }
        String custom = customBuilder.toString();
        holder.tvCustom.setText(custom);
        // Picasso.get().load(IMAGE_URL+ experience.getPosterPath()).into(holder.ivMovie);
    }


    @Override
    public int getItemCount() {
        return experiences.size();
    }

}
