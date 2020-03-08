package com.example.myapplication.util;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;

import java.util.ArrayList;

import com.example.myapplication.R;

public class CustomAdapter extends BaseAdapter {

    Context context;
    String[] criteriaList;
    LayoutInflater inflater;
    public static ArrayList<Float> selectedAnswers;
    private static float tipPercentage;

    public CustomAdapter(Context applicationContext, String[] criteriaList) {
        this.context = context;
        this.criteriaList = criteriaList;
        // initialize arraylist and add static string for all the questions
        selectedAnswers = new ArrayList<>();
        for (int i = 0; i < criteriaList.length; i++) {
            selectedAnswers.add(0.0f);
        }
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return criteriaList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.criteria_row, null);

        // get the reference of TextView and Button's
        TextView question = (TextView) view.findViewById(R.id.preset_criteria);
        RadioButton poor = (RadioButton) view.findViewById(R.id.rate_poor);
        RadioButton meh = (RadioButton) view.findViewById(R.id.rate_meh);
        RadioButton ok = (RadioButton) view.findViewById(R.id.rate_ok);
        RadioButton good = (RadioButton) view.findViewById(R.id.rate_good);
        RadioButton great = (RadioButton) view.findViewById(R.id.rate_great);

        // perform setOnCheckedChangeListener event on poor button
        poor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set Poor values in ArrayList if RadioButton is checked
                if (isChecked) {
                    selectedAnswers.set(i,-0.02f);
                    poor.setTextColor(Color.WHITE);
                } else {
                    poor.setTextColor(Color.parseColor("#9999ff"));
                }

            }
        });

        // perform setOnCheckedChangeListener event on meh button
        meh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set Poor values in ArrayList if RadioButton is checked
                if (isChecked) {
                    selectedAnswers.set(i,-0.01f);
                    meh.setTextColor(Color.WHITE);
                } else {
                    meh.setTextColor(Color.parseColor("#9999ff"));
                }
            }
        });

        // perform setOnCheckedChangeListener event on ok button
        ok.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set Poor values in ArrayList if RadioButton is checked
                if (isChecked) {
                    selectedAnswers.set(i,0.00f);
                    ok.setTextColor(Color.WHITE);
                } else {
                    ok.setTextColor(Color.parseColor("#9999ff"));
                }
            }
        });

        // perform setOnCheckedChangeListener event on good button
        good.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set Poor values in ArrayList if RadioButton is checked
                if (isChecked) {
                    selectedAnswers.set(i,+0.01f);
                    good.setTextColor(Color.WHITE);
                } else {
                    good.setTextColor(Color.parseColor("#9999ff"));
                }
            }
        });

        // perform setOnCheckedChangeListener event on great button
        great.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set Poor values in ArrayList if RadioButton is checked
                if (isChecked) {
                    selectedAnswers.set(i,+0.02f);
                    great.setTextColor(Color.WHITE);
                } else {
                    great.setTextColor(Color.parseColor("#9999ff"));
                }
            }
        });

        // set the value in TextView
        question.setText(criteriaList[i]);
//        calculateTip();
        return view;
    }

//    private void calculateTip() {
//        tipPercentage = 0.15f;
//        for (int i = 0; i < selectedAnswers.size(); i++) {
//            tipPercentage += selectedAnswers.get(i);
//        }
//    }
//
//    public static float getTipPercentage() {
//        return tipPercentage;
//    }
}