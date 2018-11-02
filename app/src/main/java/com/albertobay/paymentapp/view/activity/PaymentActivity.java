package com.albertobay.paymentapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.albertobay.paymentapp.R;
import com.albertobay.paymentapp.view.adapter.AmountAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Albert Bay on 25/10/18.
 */

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.app_bar_id)
    Toolbar toolbar;
    @BindView(R.id.amountRecyclerId)
    RecyclerView rv_amounts;
    @BindView(R.id.sdkTitle)
    TextView sdkTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        setupToolbar();
        setupRecyclerView();

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private void setupToolbar() {
        sdkTitle.setText(getString(R.string.title_main_page));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_amounts.setLayoutManager(linearLayoutManager);
        rv_amounts.setOnClickListener(this);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv_amounts.addItemDecoration(itemDecor);
        String[] amountVector = getResources().getStringArray(R.array.amounts);
        List<String> amountList = new ArrayList<String>(Arrays.asList(amountVector));
        AmountAdapter adapter = new AmountAdapter(this,  amountList, new AmountAdapter.OnContinueOperationListener() {
            @Override
            public void onContinue(String mProductOfferItem ) {
                //Log.d("log","");//startActivityWithParams(CoverageDetailInsuranceActivity.class, null);
                Intent intent = new Intent(PaymentActivity.this, CreditCardActivity.class);
                startActivity(intent);
            }
        });


        rv_amounts.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {


    }
}