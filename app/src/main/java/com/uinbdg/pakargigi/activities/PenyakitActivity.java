package com.uinbdg.pakargigi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.uinbdg.pakargigi.R;
import com.uinbdg.pakargigi.adapters.PenyakitAdapter;
import com.uinbdg.pakargigi.models.DataItemDestinasi;
import com.uinbdg.pakargigi.models.PenyakitResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.uinbdg.pakargigi.data.Constans.PENYAKIT;

public class PenyakitActivity extends AppCompatActivity {
    private static final String TAG = "GejalaActivity";
    @BindView(R.id.recyler)
    RecyclerView recyler;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    ItemAdapter itemAdapter;


    List<DataItemDestinasi> itemDestinasis = new ArrayList<>();

    PenyakitAdapter adapter;
    @BindView(R.id.searchview)
    SearchView searchview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyakit);
        ButterKnife.bind(this);

        adapter = new PenyakitAdapter(this);
        recyler.setLayoutManager(new LinearLayoutManager(this));
        recyler.setAdapter(adapter);

//        loadData();
        searchview.onActionViewExpanded();
        searchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchview.onActionViewExpanded();
            }
        });


//        searchview.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: "+query);
//                adapter.setFilter(query,itemDestinasis);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        loadData();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        adapter.setOnItemClickListener(new PenyakitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(PenyakitActivity.this, PenyakitDetailActivity.class);
                intent.putExtra("penyakit",adapter.getItem(position));
                startActivity(intent);
            }
        });
    }


    void loadData() {
        refresh.setRefreshing(true);
        AndroidNetworking.get(PENYAKIT)
                .build()
                .getAsObject(PenyakitResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        refresh.setRefreshing(false);
                        if (response instanceof PenyakitResponse) {
                            adapter.swap(((PenyakitResponse) response).getData());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        refresh.setRefreshing(false);
                        Log.d(TAG, "onError: " + anError.getErrorDetail());
                    }
                });
    }

}
