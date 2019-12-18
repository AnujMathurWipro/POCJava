package com.anuj.pocjava.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.anuj.pocjava.R;
import com.anuj.pocjava.databinding.ItemListBinding;
import com.anuj.pocjava.models.RowsItem;

import java.util.List;

public class MainScreenListAdapter extends RecyclerView.Adapter<MainScreenListAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<RowsItem> itemList;

    public MainScreenListAdapter(LayoutInflater inf, List<RowsItem> list) {
        inflater = inf;
        itemList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_list, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemListBinding binding = DataBindingUtil.getBinding(holder.itemView);
        if (binding != null) {
            RowsItem item = itemList.get(position);
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }

    public void setListItems(List<RowsItem> items) {
        itemList = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View view) {
            super(view);
        }
    }
}