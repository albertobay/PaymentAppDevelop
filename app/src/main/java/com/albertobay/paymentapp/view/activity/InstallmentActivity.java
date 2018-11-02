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
import com.albertobay.paymentapp.data.model.Installment;
import com.albertobay.paymentapp.data.model.PayerCost;
import com.albertobay.paymentapp.interactor.InstallmentInteractor;
import com.albertobay.paymentapp.presenter.InstallmentPresenter;
import com.albertobay.paymentapp.view.adapter.InstallmentAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstallmentActivity extends AppCompatActivity implements InstallmentPresenter.View {

    @BindView(R.id.app_bar_id)
    Toolbar toolbar;
    @BindView(R.id.sdkTitle)
    TextView sdkTitle;
    @BindView(R.id.errorTextView)
    TextView errorTextView;
    @BindView(R.id.installRecyclerId)
    RecyclerView rv_install;
    @BindView(R.id.pv_meli)
    ProgressBar pv_meli;
    @BindView(R.id.iv_error)
    ImageView iv_error;
    private InstallmentPresenter mInstallmentPresenter;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installment);

        ButterKnife.bind(this);
        setupSharedPreferences();
        setupToolbar();
        setupRecyclerView();

        mInstallmentPresenter = new InstallmentPresenter(new InstallmentInteractor(new MeliClient()));
        mInstallmentPresenter.setView(this);

        mInstallmentPresenter.onSearchInstallments(BuildConfig.ApiKey,
                mSharedPreferences.getString(Constants.SharedPreferences.AMOUNT_SELECTED,null),
                mSharedPreferences.getString(Constants.SharedPreferences.CREDIT_CARD_ID_SELECTED,null),
                mSharedPreferences.getString(Constants.SharedPreferences.BANK_ID_SELECTED,null));
            }

@Override
public void showLoading() {
        pv_meli.setVisibility(View.VISIBLE);
        rv_install.setVisibility(View.GONE);
    }

@Override
public void hideLoading() {
        pv_meli.setVisibility(View.GONE);
    rv_install.setVisibility(View.VISIBLE);
        }

@Override
public void showInstallmentsNotFoundMessage() {
        pv_meli.setVisibility(View.GONE);
        iv_error.setVisibility(View.VISIBLE);
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(getString(R.string.error_installment_not_found));
        iv_error.setImageDrawable(ContextCompat.getDrawable(context(), R.mipmap.ic_not_internet));
        }


@Override
public void renderInstallments(List<Installment> installments) {
        InstallmentAdapter adapter = (InstallmentAdapter) rv_install.getAdapter();
        adapter.setInstallment(installments.get(0).getPayerCosts());
        adapter.notifyDataSetChanged();
        }

@Override
public void launchSelectedInstallmentDetail(PayerCost payerCost, int position) {
        //TODO a la primer apantalla
    session.putString(Constants.SharedPreferences.RECOMENDED_MESSAGE, payerCost.getRecommendedMessage());
    session.putBoolean(Constants.SharedPreferences.COMPLETE_FLOW, true);
    session.commit();
    Intent intent = new Intent(InstallmentActivity.this, PaymentActivity.class);
    startActivity(intent);

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
        rv_install.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv_install.addItemDecoration(itemDecor);
        InstallmentAdapter adapter = new InstallmentAdapter();
        adapter.setItemClickListener(
                (payer, position) -> mInstallmentPresenter.launchInstallment(payer, position));
        rv_install.setAdapter(adapter);

}

private void setupToolbar() {
        sdkTitle.setText(getString(R.string.title_select_installment));
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
        return InstallmentActivity.this;
        }



@Override
public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
        }
}
