package com.albertobay.paymentapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.albertobay.paymentapp.BuildConfig;
import com.albertobay.paymentapp.Constants;
import com.albertobay.paymentapp.data.api.client.MeliClient;
import com.albertobay.paymentapp.data.model.PaymentMethod;
import com.albertobay.paymentapp.interactor.CreditCardInteractor;
import com.albertobay.paymentapp.presenter.CreditCardPresenter;
import com.albertobay.paymentapp.view.adapter.CreditCardAdapter;
import com.albertobay.paymentapp.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreditCardActivity extends AppCompatActivity implements CreditCardPresenter.View {

    @BindView(R.id.app_bar_id) Toolbar toolbar;
    @BindView(R.id.sdkTitle) TextView sdkTitle;
    @BindView(R.id.errorTextView) TextView errorTextView;
    @BindView(R.id.cardRecyclerId) RecyclerView rv_cards;
    @BindView(R.id.pv_meli) ProgressBar pv_meli;
    @BindView(R.id.iv_error) ImageView iv_error;
    private CreditCardPresenter cardPresenter;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor session;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        ButterKnife.bind(this);
        setupSharedPreferences();
        setupToolbar();
        setupRecyclerView();

        cardPresenter = new CreditCardPresenter(new CreditCardInteractor(new MeliClient()));
        cardPresenter.setView(this);

        cardPresenter.onSearchPayments(BuildConfig.ApiKey);
    }

    @Override public void showLoading() {
        pv_meli.setVisibility(View.VISIBLE);
        rv_cards.setVisibility(View.GONE);
    }

    @Override public void hideLoading() {
        pv_meli.setVisibility(View.GONE);
        rv_cards.setVisibility(View.VISIBLE);
    }

    @Override public void showPaymentsNotFoundMessage() {
        pv_meli.setVisibility(View.GONE);
        iv_error.setVisibility(View.VISIBLE);
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(getString(R.string.error_payment_not_found));
        iv_error.setImageDrawable(ContextCompat.getDrawable(context(), R.mipmap.ic_not_internet));
    }


    @Override public void renderPayments(List<PaymentMethod> payment) {
        CreditCardAdapter adapter = (CreditCardAdapter) rv_cards.getAdapter();
        adapter.setPayments(payment);
        adapter.notifyDataSetChanged();
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupSharedPreferences(){
        mSharedPreferences = getSharedPreferences(Constants.SharedPreferences.STORE_NAME, Context.MODE_PRIVATE);
        session = mSharedPreferences.edit();

    }


    private void setupRecyclerView() {
        /*int numberOfColumns = 1;
        rv_cards.setLayoutManager(new GridLayoutManager(this, numberOfColumns));*/
       LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_cards.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv_cards.addItemDecoration(itemDecor);
        CreditCardAdapter adapter = new CreditCardAdapter();
        adapter.setItemClickListener(

                (payments, payment, position) -> cardPresenter.launchBankDetail(payment, position));
        rv_cards.setAdapter(adapter);

    }

    private void setupToolbar() {
        sdkTitle.setText(getString(R.string.title_select_payment));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }


    @Override public Context context() {
        return CreditCardActivity.this;
    }

    @Override public void launchSelectBankDetail( PaymentMethod payment, int position) {
        session.putString(Constants.SharedPreferences.CREDIT_CARD_SELECTED, payment.getName());
        session.putString(Constants.SharedPreferences.CREDIT_CARD_ID_SELECTED, payment.getId());
        session.putString(Constants.SharedPreferences.URL_IMAGE_CD_SELECTED, payment.getSecureThumbnail());
        session.commit();
        Intent intent = new Intent(this, BankActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
