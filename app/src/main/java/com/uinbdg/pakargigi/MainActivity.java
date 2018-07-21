package com.uinbdg.pakargigi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uinbdg.pakargigi.activities.GejalaActivity;
import com.uinbdg.pakargigi.activities.PanduanActivity;
import com.uinbdg.pakargigi.activities.PenyakitActivity;
import com.uinbdg.pakargigi.activities.TentangActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_reservasi)
    TextView tvReservasi;
    @BindView(R.id.btn_panduan)
    LinearLayout btnPanduan;
    @BindView(R.id.tv_hasil_test)
    TextView tvHasilTest;
    @BindView(R.id.btn_identifikasi)
    LinearLayout btnIdentifikasi;
    @BindView(R.id.btn_gangguan)
    LinearLayout btnGangguan;
    @BindView(R.id.btn_penyakit)
    LinearLayout btnPenyakit;
    @BindView(R.id.btn_about)
    LinearLayout btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_panduan, R.id.btn_identifikasi, R.id.btn_penyakit, R.id.btn_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_panduan:
                startActivity(new Intent(this, PanduanActivity.class));
                break;
            case R.id.btn_identifikasi:
                startActivity(new Intent(this, GejalaActivity.class));
                break;
            case R.id.btn_penyakit:
                startActivity(new Intent(this, PenyakitActivity.class));
                break;
            case R.id.btn_about:
                startActivity(new Intent(this, TentangActivity.class));
                break;
        }
    }
}
