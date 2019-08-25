package com.stdio.oneticket;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ru.aviasales.core.AviasalesSDK;
import ru.aviasales.core.identification.SdkConfig;
import ru.aviasales.template.ui.fragment.AviasalesFragment;


public class MainActivity extends AppCompatActivity {

    String TRAVEL_PAYOUTS_MARKER = "184228";
    String TRAVEL_PAYOUTS_TOKEN = "0fd58b7cf9d61525de4c82ea525449b5";
    String SDK_HOST = "www.travel-api.pw";

    private AviasalesFragment aviasalesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);


        AviasalesSDK.getInstance().init(this, new SdkConfig(TRAVEL_PAYOUTS_MARKER, TRAVEL_PAYOUTS_TOKEN, SDK_HOST));
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        initFragment();
    }

    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        aviasalesFragment = (AviasalesFragment) fm.findFragmentByTag(AviasalesFragment.TAG);

        if (aviasalesFragment == null) {
            aviasalesFragment = (AviasalesFragment) AviasalesFragment.newInstance();
        }

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, aviasalesFragment, AviasalesFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (!aviasalesFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
