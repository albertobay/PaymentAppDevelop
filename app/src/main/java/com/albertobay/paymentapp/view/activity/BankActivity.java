package com.albertobay.paymentapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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
import com.albertobay.paymentapp.R;
import com.albertobay.paymentapp.data.api.client.MeliClient;
import com.albertobay.paymentapp.data.model.Issuer;
import com.albertobay.paymentapp.interactor.BankInteractor;
import com.albertobay.paymentapp.presenter.BankPresenter;
import com.albertobay.paymentapp.view.adapter.BankAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankActivity extends AppCompatActivity implements BankPresenter.View {

        @BindView(R.id.app_bar_id)
        Toolbar toolbar;
        @BindView(R.id.sdkTitle)
        TextView sdkTitle;
        @BindView(R.id.errorTextView)
        TextView errorTextView;
        @BindView(R.id.bankRecyclerId)
        RecyclerView rv_banks;
        @BindView(R.id.pv_meli)
        ProgressBar pv_meli;
        @BindView(R.id.iv_error)
        ImageView iv_error;
        private BankPresenter bankPresenter;
        SharedPreferences mSharedPreferences;
        SharedPreferences.Editor session;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_bank);

                ButterKnife.bind(this);
                setupSharedPreferences();
                setupToolbar();
                setupRecyclerView();

                bankPresenter = new BankPresenter(new BankInteractor(new MeliClient()));
                bankPresenter.setView(this);

                bankPresenter.onSearchBanks(BuildConfig.ApiKey,
                        mSharedPreferences.getString(Constants.SharedPreferences.CREDIT_CARD_ID_SELECTED,null));
        }

        @Override
        public void showLoading() {
                pv_meli.setVisibility(View.VISIBLE);
                rv_banks.setVisibility(View.GONE);
        }

        @Override
        public void hideLoading() {
                pv_meli.setVisibility(View.GONE);
                rv_banks.setVisibility(View.VISIBLE);
        }

        @Override
        public void showBanksNotFoundMessage() {
                pv_meli.setVisibility(View.GONE);
                iv_error.setVisibility(View.VISIBLE);
                errorTextView.setVisibility(View.VISIBLE);
                errorTextView.setText(getString(R.string.error_bank_not_found));
                iv_error.setImageDrawable(ContextCompat.getDrawable(context(), R.mipmap.ic_not_internet));
        }


        @Override
        public void renderBanks(List<Issuer> banks) {
                BankAdapter adapter = (BankAdapter) rv_banks.getAdapter();
                adapter.setPayments(banks);
                adapter.notifyDataSetChanged();
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
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
                rv_banks.setLayoutManager(linearLayoutManager);
                DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                rv_banks.addItemDecoration(itemDecor);
                BankAdapter adapter = new BankAdapter();
                adapter.setItemClickListener(
                        (banks, bank, position) -> bankPresenter.launchBankDetail(bank, position));
                rv_banks.setAdapter(adapter);

        }

        private void setupToolbar() {
                sdkTitle.setText(getString(R.string.title_select_bank));
                setSupportActionBar(toolbar);
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                        actionBar.setDisplayUseLogoEnabled(false);
                        actionBar.setDisplayHomeAsUpEnabled(true);
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                }
        }


        @Override
        public Context context() {
                return BankActivity.this;
        }

        @Override
        public void launchSelectBankDetail(Issuer bank, int position) {
                session.putString(Constants.SharedPreferences.BANK_SELECTED, bank.getName());
                session.putString(Constants.SharedPreferences.BANK_SELECTED, bank.getName());
                session.putString(Constants.SharedPreferences.URL_IMAGE_BANK_SELECTED, bank.getSecureThumbnail());
                session.commit();
                Intent intent = new Intent(this, InstallmentActivity.class);
                startActivity(intent);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
        }
}