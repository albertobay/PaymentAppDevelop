package com.albertobay.paymentapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.albertobay.paymentapp.R;
import com.albertobay.paymentapp.data.model.PaymentMethod;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Bay on 31,October,2018
 */
public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.CardsViewHolder> {

private List<PaymentMethod> payments;
private ItemClickListener itemClickListener;

public CreditCardAdapter() {
        payments = Collections.emptyList();
        }

@Override public CardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
final View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_payment_layout, parent, false);
        return new CardsViewHolder(itemView);
        }

@Override public void onBindViewHolder(CardsViewHolder holder, int position) {
        PaymentMethod payment = payments.get(position);

        holder.paymentTextView.setText(payment.getName());
        holder.paymentImageView.setScaleType(ImageView.ScaleType.CENTER);
        Picasso.with(holder.paymentImageView.getContext())
                .load(payment.getThumbnail())
                .into(holder.paymentImageView);

        holder.itemView.setOnClickListener((View view) -> {
            if (itemClickListener != null) {
            itemClickListener.onItemClick(payments, payment, position); }
            });
        }

@Override public int getItemCount() {
        return payments.size();
        }

public void setPayments(List<PaymentMethod> payments) {
        this.payments = payments;
        }

public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        }

public interface ItemClickListener {
    void onItemClick(List<PaymentMethod> paymentMethods, PaymentMethod payment, int position);
}

    /**
     * Se crea el vieholder
     */
public static class CardsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.paymentImageView)
    ImageView paymentImageView;
    @BindView(R.id.paymentTextView)
    TextView paymentTextView;

    View itemView;

    public CardsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
    }
}
}