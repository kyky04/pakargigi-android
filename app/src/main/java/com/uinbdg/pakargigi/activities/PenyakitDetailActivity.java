package com.uinbdg.pakargigi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.uinbdg.pakargigi.R;
import com.uinbdg.pakargigi.adapters.GejalaAdapter;
import com.uinbdg.pakargigi.models.DataItemPenyakit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PenyakitDetailActivity extends AppCompatActivity {
    private static final String TAG = "PenyakitDetailActivity";
    @BindView(R.id.tv_nama_penyakit)
    TextView tvNamaPenyakit;
    @BindView(R.id.tv_keterangan)
    TextView tvKeterangan;
    @BindView(R.id.recyler)
    RecyclerView recyler;

    GejalaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penyakit);
        ButterKnife.bind(this);


        DataItemPenyakit penyakit = (DataItemPenyakit) getIntent().getSerializableExtra("penyakit");

        tvNamaPenyakit.setText(penyakit.getPenyakit());
        tvKeterangan.setText(penyakit.getKeterangan());

        recyler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GejalaAdapter(this,false);
        adapter.swap(penyakit.getGejala());

        recyler.setAdapter(adapter);
    }
}
