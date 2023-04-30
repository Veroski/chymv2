package com.example.chymv2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chymv2.R;
import com.example.chymv2.model.CardMaterial;
import android.content.Context;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ViewHolder> {
    private List<CardMaterial> mData;
    private LayoutInflater mInflater;
    private Context context;

    public MaterialAdapter(List<CardMaterial> itemMaterial, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemMaterial;
    }


    @Override
    public int getItemCount(){ return mData.size();}

    @Override
    public MaterialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.card_material, null);
        return new MaterialAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MaterialAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<CardMaterial> items){ mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        CheckBox status;

        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.materialCardTxt);
            status = itemView.findViewById(R.id.materialCheckBox);
        }

        void bindData(final CardMaterial item){
            name.setText(item.getName());
            status.setEnabled(item.isStatus());
        }
    }
}