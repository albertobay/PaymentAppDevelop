package com.albertobay.paymentapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import com.albertobay.paymentapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Bay on 30,October,2018
 */
public class AmountAdapter extends RecyclerView.Adapter<AmountAdapter.AmountViewHolder> {

    private List<String> amounts;
    private ItemClickListener itemClickListener;

    public AmountAdapter() {
        amounts = Collections.emptyList();
    }

    @Override public AmountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_amount_layout, parent, false);
        return new AmountViewHolder(itemView);
    }

    @Override public void onBindViewHolder(AmountViewHolder holder, final int position) {
        final String amount = amounts.get(position);
        holder.amount = amount;
        holder.textView.setText(amount);

        if (amounts.size() > 0) {

            for (int i = 0; i < amounts.size(); i++) {
                    holder.amount = amounts.get(i);

            }
        } else {

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(amount, position);
            }
            }
        });
    }

    @Override public int getItemCount() {
        return amounts.size();
    }

    public void setAmounts(List<String> amounts) {
        this.amounts = amounts;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(String amount, int position);
    }

    public static class AmountViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.amountTextView)
        TextView textView;

        String amount;
        View itemView;

        public AmountViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
