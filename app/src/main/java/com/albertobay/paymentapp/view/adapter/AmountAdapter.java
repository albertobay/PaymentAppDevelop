package com.albertobay.paymentapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.albertobay.paymentapp.Constants;
import com.albertobay.paymentapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Bay on 30,October,2018
 */
public class AmountAdapter extends RecyclerView.Adapter<AmountAdapter.AmountViewHolder> {

    protected Context mContext;
    private List<String> amounts;
    private OnContinueOperationListener mContinueOperationListener;

    public AmountAdapter(Context context, List<String> items, OnContinueOperationListener mContinueOperationListener) {
        this.mContinueOperationListener = mContinueOperationListener;
        amounts = Collections.emptyList();
        amounts = items;
        this.mContext = context;
    }

    @Override
    public AmountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_amount_layout, parent, false);

        return new AmountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AmountViewHolder holder, int position) {
        holder.setItem(amounts.get(position));
    }

    @Override
    public int getItemCount() {
        return amounts.size();
    }


    public OnContinueOperationListener getContinueOperationListener() {
        return mContinueOperationListener;
    }

    public void setContinueOperationListener(OnContinueOperationListener mContinueOperationListener) {
        this.mContinueOperationListener = mContinueOperationListener;
    }

    public class AmountViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.amountTextView)
        TextView amountTextView;

        public final View mView;
        private String mItem;

        public AmountViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }

        public void setItem(String item) {
            mItem = item;

            if(amounts != null && amounts.size()>0){

            }
            amountTextView.setText(Constants.CURRENCY +mItem);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {mContinueOperationListener.onContinue(mItem);}
            });

        }
    }

    public interface OnContinueOperationListener {
        void onContinue(String mProductPreview);
    }
}