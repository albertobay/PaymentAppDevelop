package com.albertobay.paymentapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.albertobay.paymentapp.R;
import com.albertobay.paymentapp.data.model.Installment;
import com.albertobay.paymentapp.data.model.PayerCost;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Bay on 02,November,2018
 */
public class InstallmentAdapter extends  RecyclerView.Adapter<InstallmentAdapter.InstallmentViewHolder> {

    private List<PayerCost> payerCost;
    private InstallmentAdapter.ItemClickListener itemClickListener;

    public InstallmentAdapter() {
        payerCost = Collections.emptyList();
    }

    @Override
    public InstallmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_installment_layout, parent, false);
        return new InstallmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InstallmentViewHolder holder, int position) {
        PayerCost payer = payerCost.get(position);

        holder.recomendedTextView.setText(payer.getRecommendedMessage());


        holder.itemView.setOnClickListener((View view) -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(payer, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return payerCost.size();
    }

    public void setInstallment(List<PayerCost> payerCost) {
        this.payerCost = payerCost;
    }

    public void setItemClickListener(InstallmentAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(PayerCost payer, int position);
    }

    /**
     * Se crea el vieholder
     */
    public static class InstallmentViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.recomendedTextView)
        TextView recomendedTextView;

        View itemView;

        public InstallmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}