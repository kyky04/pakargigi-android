package com.uinbdg.pakargigi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.uinbdg.pakargigi.R;
import com.uinbdg.pakargigi.adapters.GejalaAdapter;
import com.uinbdg.pakargigi.models.DataItemBahasa;
import com.uinbdg.pakargigi.models.GejalaResponse;
import com.uinbdg.pakargigi.utils.CommonUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.uinbdg.pakargigi.data.Constans.GEJALA;

public class GejalaActivity extends AppCompatActivity {
    private static final String TAG = "GejalaActivity";
    @BindView(R.id.recyler)
    RecyclerView recyler;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    ItemAdapter itemAdapter;
    @BindView(R.id.btn_ok)
    Button btnOk;


    GejalaAdapter gejalaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala);
        ButterKnife.bind(this);

        gejalaAdapter = new GejalaAdapter(this,true);
        recyler.setLayoutManager(new LinearLayoutManager(this));
        recyler.setAdapter(gejalaAdapter);

        loadData();

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                itemAdapter.clear();
                loadData();
            }
        });
    }


    void loadData() {
        refresh.setRefreshing(true);
        AndroidNetworking.get(GEJALA)
                .build()
                .getAsObject(GejalaResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        refresh.setRefreshing(false);
                        if (response instanceof GejalaResponse) {
                            gejalaAdapter.swap(((GejalaResponse) response).getData());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        refresh.setRefreshing(false);
                        Log.d(TAG, "onError: " + anError.getErrorDetail());
                    }
                });
    }

    public List<DataItemBahasa> getAllData(List<DataItemBahasa> dataItemBahasaList) {
        List<DataItemBahasa> list = new ArrayList<>();
        for (int i = 0; i < dataItemBahasaList.size(); i++) {
            list.add(dataItemBahasaList.get(i));
        }
        return list;
    }

    @OnClick(R.id.btn_ok)
    public void onViewClicked() {
        Log.d(TAG, "onViewClicked: "+gejalaAdapter.getItemCount());
        if(gejalaAdapter.getSelected().size() != 0){
            Intent intent = new Intent(this, HasilCFActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("gejala", (Serializable) gejalaAdapter.getSelected());
            intent.putExtras(bundle);
            startActivity(intent);
        }else {
            CommonUtil.showSnack(this,"Pilih salah satu gejala yang anda alami");
        }

    }
}
