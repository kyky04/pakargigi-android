package com.uinbdg.pakargigi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.uinbdg.pakargigi.R;
import com.uinbdg.pakargigi.adapters.ResultAdapter;
import com.uinbdg.pakargigi.models.DataItemGejala;
import com.uinbdg.pakargigi.models.DataItemPenyakit;
import com.uinbdg.pakargigi.models.DataItemResult;
import com.uinbdg.pakargigi.models.Gejala;
import com.uinbdg.pakargigi.models.Penyakit;
import com.uinbdg.pakargigi.models.ResultResponse;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.uinbdg.pakargigi.data.Constans.RESULT;

public class HasilCFActivity extends AppCompatActivity {
    private static final String TAG = "GejalaActivity";
    @BindView(R.id.recyler)
    RecyclerView recyler;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    ItemAdapter itemAdapter;


    ResultAdapter adapter;

    List<DataItemGejala> listGejala = new ArrayList<>();
    List<DataItemResult> listItemPenyakit = new ArrayList<>();

    int id, count = 0;

    HashMap<String, String> hashMapPenyakit = new HashMap<>();
    HashMap<String, String> hashMapKeterangan = new HashMap<>();

    List<DataItemPenyakit> listPenyakit = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasilcf_penyakit);
        ButterKnife.bind(this);

        adapter = new ResultAdapter(this);
        recyler.setLayoutManager(new LinearLayoutManager(this));
        recyler.setAdapter(adapter);

        listGejala = (List<DataItemGejala>) getIntent().getSerializableExtra("gejala");
        for (int i = 0; i < listGejala.size(); i++) {
            Log.d(TAG, "onCreate: " + listGejala.get(i).getId());
        }
        loadData();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listItemPenyakit.clear();
                loadData();
            }
        });

        adapter.setOnItemClickListener(new ResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(HasilCFActivity.this, PenyakitDetailActivity.class);
                intent.putExtra("penyakit",adapter.getItem(position));
                startActivity(intent);
            }
        });


    }

    private void hitungCf() {

        for (int i = 0; i < listItemPenyakit.size(); i++) {
            hashMapPenyakit.put(listItemPenyakit.get(i).getPenyakit().getPenyakit(), listItemPenyakit.get(i).getPenyakit().getPenyakit());
            hashMapKeterangan.put(listItemPenyakit.get(i).getPenyakit().getPenyakit(), listItemPenyakit.get(i).getPenyakit().getKeterangan());
        }

        for (String k : hashMapPenyakit.keySet()) {
            DataItemPenyakit tempPenyakit = new DataItemPenyakit();
            tempPenyakit.setPenyakit(k);

            for (String keterangan : hashMapKeterangan.keySet()) {
                if (k.equals(keterangan)) {
                tempPenyakit.setKeterangan(hashMapKeterangan.get(keterangan));
                }
            }


            List<DataItemGejala> gejalaList = new ArrayList<>();
            for (int i = 0; i < listItemPenyakit.size(); i++) {
                if (k.equals(listItemPenyakit.get(i).getPenyakit().getPenyakit())) {
                    DataItemGejala gejala = new DataItemGejala(listItemPenyakit.get(i).getGejala().getGejala(), listItemPenyakit.get(i).getCf());
                    gejalaList.add(gejala);
                }
            }
            tempPenyakit.setGejala(gejalaList);

            listPenyakit.add(tempPenyakit);
        }


        for (int i = 0; i < listPenyakit.size(); i++) {
            Log.d(TAG, "NAMA: PENYAKIT : " + listPenyakit.get(i).getPenyakit());
            for (int j = 0; j < listPenyakit.get(i).getGejala().size(); j++) {
                Log.d(TAG, "NAMA: GEJALA : " + listPenyakit.get(i).getGejala().get(j).getNama_gejala());
            }
        }

        DecimalFormat df2 = new DecimalFormat("#.##");
        df2.setRoundingMode(RoundingMode.UP);
        for (int i = 0; i < listPenyakit.size(); i++) {
            double hitung = 0;
            for (int j = 0; j < listPenyakit.get(i).getGejala().size(); j++) {
                double cf = listPenyakit.get(i).getGejala().get(j).getNilaiCf();
                if (j == 0) {
                    hitung = cf;
                    Log.d(TAG, "HITUNG: POSISI " + i);
                } else if (j > 0) {
                    if (hitung < 0 && cf < 0) {
                        double proses1 = 1 - Math.min(Math.abs(hitung), Math.abs(cf));
                        hitung = proses1;
                        Log.d(TAG, "HITUNG: PROSES NEGATIF SEMUA: " + hitung);
                    } else if (cf > 0) {
                        double proses1 = 1 - hitung;
                        double proses2 = hitung + cf * proses1;
                        double cfGabungan = proses2;

                        Log.d(TAG, "HITUNG: PROSES POSITIF : " + proses1 + ", CFGABUNGAN : " + cfGabungan);
                        hitung = cfGabungan;

                    } else if (cf < 0) {
                        double proses1 = 1 + hitung;
                        double proses2 = hitung + cf * proses1;
                        double cfGabungan = proses2;

                        Log.d(TAG, "HITUNG: PROSES NEGATIF : " + proses1 + ", CFGABUNGAN : " + cfGabungan);
                        hitung = cfGabungan;
                    }
                }

                Log.d(TAG, "HASIL CF: " + df2.format(hitung));
                listPenyakit.get(i).setHasil_cf(hitung);
            }
        }
        Collections.sort(listPenyakit, new Comparator<DataItemPenyakit>() {
            @Override
            public int compare(DataItemPenyakit o1, DataItemPenyakit o2) {
                return Double.compare(o2.getHasil_cf(), o1.getHasil_cf());
            }

        });
        for (int i = 0; i < listPenyakit.size(); i++) {
            Log.d(TAG, "PRESENTASE: " + listPenyakit.get(i).getPenyakit() + " " + df2.format(listPenyakit.get(i).getHasil_cf()));
        }

        adapter.addAll(listPenyakit);

    }


    void loadData() {
        refresh.setRefreshing(true);
        ANRequest.PostRequestBuilder builder = new ANRequest.PostRequestBuilder(RESULT);

        for (int i = 0; i < listGejala.size(); i++) {
            int id_gejala = listGejala.get(i).getId();
            Log.d(TAG, "loadData: " + id_gejala);
            builder.addBodyParameter("id_gejala[" + i + "]", String.valueOf(id_gejala));
        }
        builder.build()
                .getAsObject(ResultResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        refresh.setRefreshing(false);
                        if (response instanceof ResultResponse) {
                            for (int i = 0; i < ((ResultResponse) response).getData().size(); i++) {
                                Log.d(TAG, "onResponse: " + ((ResultResponse) response).getData().get(i).getPenyakit().getPenyakit());
                            }
                            listItemPenyakit.addAll(((ResultResponse) response).getData());
                            hitungCf();
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
