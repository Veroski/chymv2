package com.example.chymv2.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chymv2.R;
import com.example.chymv2.model.ListExercice;
import com.example.chymv2.model.Rutina;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoutineListAdapter extends RecyclerView.Adapter<RoutineListAdapter.ViewHolder>{
    private List<Rutina> data;
    private LayoutInflater mInflater;
    private Context context;
    private List<Rutina> originalRoutines;


    public RoutineListAdapter(List<Rutina> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.data = itemList;
        this.originalRoutines = new ArrayList<>();
        originalRoutines.addAll(itemList);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_routines, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(data.get(position));
    }

    public void setItems(List<Rutina> items) {
        data = items;
    }

    public void filter(String strSearch) {
        if (strSearch.length() == 0) {
            data.clear();
            data.addAll(originalRoutines);
        } else {
            data.clear();
            List<Rutina> collect = originalRoutines.stream().filter(i -> i.getNombre().toLowerCase().contains(strSearch.toLowerCase())).collect(Collectors.toList());
            data.addAll(collect);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView nombreRutina;

        public ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView_r);
            nombreRutina = itemView.findViewById(R.id.routineTextView);

        }

        public void bindData(final Rutina item) {
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            nombreRutina.setText(item.getNombre());

        }

    }
}