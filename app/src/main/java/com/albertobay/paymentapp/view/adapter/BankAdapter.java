package com.albertobay.paymentapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.albertobay.paymentapp.R;
import com.albertobay.paymentapp.data.model.Issuer;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Bay on 02,November,2018
 */
public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BanksViewHolder> {

    private List<Issuer> banks;
    private BankAdapter.ItemClickListener itemClickListener;

    public BankAdapter() {
        banks = Collections.emptyList();
    }

    @Override
    public BanksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_banks_layout, parent, false);
        return new BanksViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BanksViewHolder holder, int position) {
        Issuer bank = banks.get(position);

        holder.banksTextView.setText(bank.getName());
        holder.bankImageView.setScaleType(ImageView.ScaleType.CENTER);
        Picasso.with(holder.bankImageView.getContext())
                .load(bank.getThumbnail())
                .into(holder.bankImageView);

        holder.itemView.setOnClickListener((View view) -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(banks, bank, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return banks.size();
    }

    public void setPayments(List<Issuer> banks) {
        this.banks = banks;
    }

    public void setItemClickListener(BankAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(List<Issuer> banks, Issuer bank, int position);
    }

    /**
     * Se crea el vieholder
     */
    public static class BanksViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.bankImageView)
        ImageView bankImageView;
        @BindView(R.id.banksTextView)
        TextView banksTextView;

        View itemView;

        public BanksViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}