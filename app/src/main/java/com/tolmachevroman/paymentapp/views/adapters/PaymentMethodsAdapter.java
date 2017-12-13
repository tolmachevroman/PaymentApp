package com.tolmachevroman.paymentapp.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tolmachevroman.paymentapp.R;
import com.tolmachevroman.paymentapp.models.paymentmethods.PaymentMethod;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by romantolmachev on 12/12/2017.
 */

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.ViewHolder> {

    private List<PaymentMethod> paymentMethods;
    private RecyclerViewClickListener recyclerViewClickListener;

    public PaymentMethodsAdapter(List<PaymentMethod> paymentMethods, RecyclerViewClickListener recyclerViewClickListener) {
        this.paymentMethods = paymentMethods;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_method_item, parent, false);

        return new ViewHolder(itemView, recyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(paymentMethods.get(position));
    }

    @Override
    public int getItemCount() {
        return paymentMethods.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.cardImg)
        ImageView cardImg;

        @BindView(R.id.cardTxt)
        TextView cardTxt;

        private RecyclerViewClickListener recyclerViewClickListener;
        private Context context;

        ViewHolder(View itemView, RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.recyclerViewClickListener = recyclerViewClickListener;
            this.context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        void bind(PaymentMethod item) {

            if (item.getThumbnail() != null && !item.getThumbnail().isEmpty()) {
                Picasso.with(context).load(item.getThumbnail()).resize(48, 48).centerInside().into(cardImg);
            }

            if (item.getName() != null)
                cardTxt.setText(item.getName());
        }

        @Override
        public void onClick(View v) {
            recyclerViewClickListener.onClick(v, getAdapterPosition());
        }
    }
}
