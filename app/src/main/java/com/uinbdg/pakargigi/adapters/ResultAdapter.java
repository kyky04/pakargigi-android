package com.uinbdg.pakargigi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uinbdg.pakargigi.R;
import com.uinbdg.pakargigi.models.DataItemPenyakit;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Comp on 2/11/2018.
 */

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DataItemPenyakit> listItem;

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public ResultAdapter(Context ctx) {
        this.ctx = ctx;
        listItem = new ArrayList<>();
    }


    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_penyakit)
        TextView tvPenyakit;
        @BindView(R.id.lay)
        LinearLayout lay;
        @BindView(R.id.tv_percent)
        TextView tvPercent;

        public OriginalViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            final DataItemPenyakit item = listItem.get(position);
            view.tvPenyakit.setText(item.getPenyakit());
            DecimalFormat df2 = new DecimalFormat("#.##");
            double percent = item.getHasil_cf() * 100;

            view.tvPercent.setText(String.valueOf(df2.format(percent))+"%");

            view.lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void add(DataItemPenyakit item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }

    public void addAll(List<DataItemPenyakit> listItem) {
        for (DataItemPenyakit item : listItem) {
            add(item);
        }
    }

    public void removeAll() {
        listItem.clear();
        notifyDataSetChanged();
    }

    public void swap(List<DataItemPenyakit> datas) {
        if (datas == null || datas.size() == 0)
            return;
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();

    }

    public DataItemPenyakit getItem(int pos) {
        return listItem.get(pos);
    }

    public String showHourMinute(String hourMinute) {
        String time = "";
        time = hourMinute.substring(0, 5);
        return time;
    }

//    public void setFilter(String query, List<DataItemPenyakit> list) {
//        listItem = new ArrayList<>();
//        JaroWinkler jw = new JaroWinkler();
//        List<DataItemPenyakit> destinasiList = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            String input = list.get(i).getNama().trim().toString();
//            input = input.replace(" ", "");
//            Log.d("JARO", input.toLowerCase() + " " + query.trim().toString().toLowerCase() + " " + jw.similarity(list.get(i).getNama().trim().toString(), query.trim().toString()));
//            if (jw.similarity(input.toLowerCase(), query.trim().toString().toLowerCase()) > 0.7) {
//                destinasiList.add(list.get(i));
//            }
//        }
//        listItem.addAll(destinasiList);
//        notifyDataSetChanged();
//    }

    public List<DataItemPenyakit> getListItem() {
        return listItem;
    }

//    public List<DataItemPenyakit> getSelected() {
//        List<DataItemPenyakit> bahasaSelected = new ArrayList<>();
//        for (int i = 0; i < listItem.size(); i++) {
//            if (listItem.get(i).isSelected()) {
//                bahasaSelected.add(listItem.get(i));
//            }
//        }
//        return bahasaSelected;
//    }


}
