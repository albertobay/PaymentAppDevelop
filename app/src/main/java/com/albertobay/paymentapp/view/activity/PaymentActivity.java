package com.albertobay.paymentapp.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.albertobay.paymentapp.R;
import com.albertobay.paymentapp.presenter.AmountPresenter;
import com.albertobay.paymentapp.view.adapter.AmountAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Albert Bay on 25/10/18.
 */

public class PaymentActivity extends AppCompatActivity implements AmountPresenter.View {


    @BindView(R.id.app_bar_id)
    Toolbar toolbar;
    @BindView(R.id.amountRecyclerId)
    RecyclerView rv_amounts;


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

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showArtistNotFoundMessage() {

    }

    @Override
    public void showConnectionErrorMessage() {

    }

    @Override
    public void showServerError() {

    }

    @Override
    public void renderAmount() {

    }

    @Override
    public void launchAmountDetail(String artist) {

    }

    @Override
    public Context context() {
        return null;
    }

    private void setupToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_amounts.setLayoutManager(linearLayoutManager);
        AmountAdapter adapter = new AmountAdapter();
        String[] amountVector = getResources().getStringArray(R.array.amounts);
        List<String> amountList = new ArrayList<String>(Arrays.asList(amountVector));

        adapter.setAmounts(amountList);

        /*adapter.setItemClickListener(
                (tracks, track, position) -> tracksPresenter.launchArtistDetail(tracks, track, position));*/
        rv_amounts.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
