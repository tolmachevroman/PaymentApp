package com.tolmachevroman.paymentapp.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolmachevroman.paymentapp.R;
import com.tolmachevroman.paymentapp.models.installments.PayerCost;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by romantolmachev on 13/12/2017.
 */

public class InstallmentsAdapter extends RecyclerView.Adapter<InstallmentsAdapter.ViewHolder> {

    private List<PayerCost> payerCosts;
    private RecyclerViewClickListener recyclerViewClickListener;

    public InstallmentsAdapter(List<PayerCost> payerCosts, RecyclerViewClickListener recyclerViewClickListener) {
        this.payerCosts = payerCosts;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payer_item, parent, false);

        return new InstallmentsAdapter.ViewHolder(itemView, recyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(payerCosts.get(position));
    }

    @Override
    public int getItemCount() {
        return payerCosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.messageTxt)
        TextView messageTxt;

        private RecyclerViewClickListener recyclerViewClickListener;

        ViewHolder(View itemView, RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.recyclerViewClickListener = recyclerViewClickListener;
            itemView.setOnClickListener(this);
        }

        void bind(PayerCost item) {

            if (item.getRecommendedMessage() != null)
                messageTxt.setText(item.getRecommendedMessage());
        }

        @Override
        public void onClick(View v) {
            recyclerViewClickListener.onClick(v, getAdapterPosition());
        }
    }
}
