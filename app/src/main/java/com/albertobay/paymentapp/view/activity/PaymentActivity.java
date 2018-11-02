package com.albertobay.paymentapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.albertobay.paymentapp.Constants;
import com.albertobay.paymentapp.R;
import com.albertobay.paymentapp.view.adapter.AmountAdapter;
import com.squareup.picasso.Picasso;

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
    @BindView(R.id.mainLayout)
    LinearLayout mainLayout;
    @BindView(R.id.reviewLayout)
    LinearLayout reviewLayout;
    @BindView(R.id.montoTextView)
    TextView montoTextView;
    @BindView(R.id.paymentTextView)
    TextView paymentTextView;
    @BindView(R.id.bancoNombreTextView)
    TextView bancoNombreTextView;
    @BindView(R.id.recomendedMessageTextView)
    TextView recomendedMessageTextView;
    @BindView(R.id.paymentImageView)
    ImageView paymentImageView;
    @BindView(R.id.bancoImageView)
    ImageView bancoImageView;
    @BindView(R.id.closeButton)
    Button closeButton;





    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        validateSharedPreferences();

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private void validateSharedPreferences(){
        mSharedPreferences = getSharedPreferences(Constants.SharedPreferences.STORE_NAME, Context.MODE_PRIVATE);
        session = mSharedPreferences.edit();
        if(!mSharedPreferences.getBoolean(Constants.SharedPreferences.COMPLETE_FLOW, false)){
            setupToolbar();
            setupRecyclerView();
        }else{
            setupFinalScreen();
        }
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
            public void onContinue(String amount ) {
                session.putString(Constants.SharedPreferences.AMOUNT_SELECTED, amount);
                session.commit();
                Intent intent = new Intent(PaymentActivity.this, CreditCardActivity.class);
                startActivity(intent);
            }
        });


        rv_amounts.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {
        if(view.equals(closeButton)){
            session.putBoolean(Constants.SharedPreferences.COMPLETE_FLOW, false);
            session.commit();
            session.clear();
            finish();
            startActivity(getIntent());
        }
    }

    private void setupFinalScreen() {
        mainLayout.setVisibility(View.GONE);
        reviewLayout.setVisibility(View.VISIBLE);
        String amount =  mSharedPreferences.getString(Constants.SharedPreferences.AMOUNT_SELECTED,null);
        String payment =  mSharedPreferences.getString(Constants.SharedPreferences.CREDIT_CARD_SELECTED,null);
        String paymentUrl =  mSharedPreferences.getString(Constants.SharedPreferences.URL_IMAGE_CD_SELECTED,null);
        String bank = mSharedPreferences.getString(Constants.SharedPreferences.BANK_SELECTED,null);
        String bankUrl = mSharedPreferences.getString(Constants.SharedPreferences.URL_IMAGE_BANK_SELECTED,null);
        String installment = mSharedPreferences.getString(Constants.SharedPreferences.RECOMENDED_MESSAGE,null);

        bancoImageView.setScaleType(ImageView.ScaleType.CENTER);
        Picasso.with(bancoImageView.getContext())
                .load(bankUrl)
                .into(bancoImageView);

        paymentImageView.setScaleType(ImageView.ScaleType.CENTER);
        Picasso.with(paymentImageView.getContext())
                .load(paymentUrl)
                .into(paymentImageView);

        montoTextView.setText(amount);
        paymentTextView.setText(payment);
        bancoNombreTextView.setText(bank);
        recomendedMessageTextView.setText(installment);

        closeButton.setOnClickListener(this);

    }
}