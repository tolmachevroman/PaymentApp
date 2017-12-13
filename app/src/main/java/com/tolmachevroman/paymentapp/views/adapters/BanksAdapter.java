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
import com.tolmachevroman.paymentapp.models.banks.Bank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by romantolmachev on 13/12/2017.
 */

public class BanksAdapter extends RecyclerView.Adapter<BanksAdapter.ViewHolder> {

    private List<Bank> banks;
    private RecyclerViewClickListener recyclerViewClickListener;

    public BanksAdapter(List<Bank> banks, RecyclerViewClickListener recyclerViewClickListener) {
        this.banks = banks;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_item, parent, false);

        return new BanksAdapter.ViewHolder(itemView, recyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(banks.get(position));
    }

    @Override
    public int getItemCount() {
        return banks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.bankImg)
        ImageView bankImg;

        @BindView(R.id.bankTxt)
        TextView bankTxt;

        private RecyclerViewClickListener recyclerViewClickListener;
        private Context context;

        public ViewHolder(View itemView, RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.recyclerViewClickListener = recyclerViewClickListener;
            this.context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        void bind(Bank item) {

            if (item.getThumbnail() != null && !item.getThumbnail().isEmpty()) {
                Picasso.with(context).load(item.getThumbnail()).resize(48, 48).centerInside().into(bankImg);
            }

            if (item.getName() != null)
                bankTxt.setText(item.getName());
        }

        @Override
        public void onClick(View v) {
            recyclerViewClickListener.onClick(v, getAdapterPosition());
        }
    }
}
