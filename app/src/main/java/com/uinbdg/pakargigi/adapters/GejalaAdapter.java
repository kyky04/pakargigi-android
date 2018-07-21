package com.uinbdg.pakargigi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.uinbdg.pakargigi.R;
import com.uinbdg.pakargigi.models.DataItemGejala;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Comp on 2/11/2018.
 */

public class GejalaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DataItemGejala> listItem;
    boolean pilihan = false;

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public GejalaAdapter(Context ctx,boolean pilihan) {
        this.ctx = ctx;
        this.pilihan = pilihan;
        listItem = new ArrayList<>();
    }


    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_bahasa)
        TextView tvGejala;
        @BindView(R.id.cb_bahasa)
        CheckBox cbGejala;

        public OriginalViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gejala, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            final OriginalViewHolder view = (OriginalViewHolder) holder;
            final DataItemGejala item = listItem.get(position);
            view.tvGejala.setText(item.getGejala());
            if(pilihan){
                view.cbGejala.setVisibility(View.VISIBLE);
            }
            ((OriginalViewHolder) holder).cbGejala.setOnCheckedChangeListener(null);
            view.cbGejala.setChecked(item.isSelected());
            view.cbGejala.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //set your object's last status
                    item.setSelected(isChecked);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void add(DataItemGejala item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }

    public void addAll(List<DataItemGejala> listItem) {
        for (DataItemGejala item : listItem) {
            add(item);
        }
    }

    public void removeAll() {
        listItem.clear();
        notifyDataSetChanged();
    }

    public void swap(List<DataItemGejala> datas) {
        if (datas == null || datas.size() == 0)
            return;
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();

    }

    public DataItemGejala getItem(int pos) {
        return listItem.get(pos);
    }

    public String showHourMinute(String hourMinute) {
        String time = "";
        time = hourMinute.substring(0, 5);
        return time;
    }

    public void setFilter(List<DataItemGejala> list) {
        listItem = new ArrayList<>();
        listItem.addAll(list);
        notifyDataSetChanged();
    }

    public List<DataItemGejala> getListItem() {
        return listItem;
    }

    public List <DataItemGejala> getSelected() {
        List<DataItemGejala> gejalaSelected = new ArrayList<>();
        for (int i = 0; i < listItem.size(); i++) {
            if(listItem.get(i).isSelected()){
                gejalaSelected.add(listItem.get(i));
            }
        }
        return gejalaSelected;
    }


}
