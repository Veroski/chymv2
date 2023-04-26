package com.example.chymv2.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chymv2.R;
import com.example.chymv2.model.ListExercice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExerciceListAdapter extends RecyclerView.Adapter<ExerciceListAdapter.ViewHolder> {
    private List<ListExercice> data;
    private LayoutInflater mInflater;
    private Context context;
    private List<ListExercice> originalExercices;


    public ExerciceListAdapter(List<ListExercice> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.data = itemList;
        this.originalExercices = new ArrayList<>();
        originalExercices.addAll(itemList);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_list_exercices, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(data.get(position));
    }

    public void setItems(List<ListExercice> items) {
        data = items;
    }

    public void filter(String strSearch) {
        if (strSearch.length() == 0) {
            data.clear();
            data.addAll(originalExercices);
        } else {
            data.clear();
            List<ListExercice> collect = originalExercices.stream().filter(i -> i.getEjercicio().toLowerCase().contains(strSearch.toLowerCase())).collect(Collectors.toList());
            data.addAll(collect);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView ejercicio, grupoMuscular, tipoEjercicio;

        public ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            ejercicio = itemView.findViewById(R.id.muscleTextView);
            grupoMuscular = itemView.findViewById(R.id.bodyPartTextView);
            tipoEjercicio = itemView.findViewById(R.id.typeTextView);
        }

        public void bindData(final ListExercice item) {
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            ejercicio.setText(item.getEjercicio());
            grupoMuscular.setText(item.getGrupoMuscular());
            tipoEjercicio.setText(item.getTipoEjercicio());

        }

    }
}

