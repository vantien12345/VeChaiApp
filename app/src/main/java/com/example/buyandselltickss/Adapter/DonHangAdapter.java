package com.example.buyandselltickss.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyandselltickss.R;
import com.example.buyandselltickss.ScrapBoardScreen.DonHang;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangViewHolder> {

    private List<DonHang>  mlist;
    private IClickListener miClickListener;
    public interface IClickListener {
        void onClickListener(DonHang donHang);
    }

    public DonHangAdapter(List<DonHang> mlist , IClickListener iClickListener) {
        this.mlist = mlist;
        this.miClickListener = iClickListener;
    }

    @NonNull
    @Override
    public DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_don_hang, parent, false);
        return new DonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangViewHolder holder, int position) {

        DonHang donHang = mlist.get(position);
        if(donHang == null){
            return;
        }
        holder.dchi.setText(donHang.getAddress());
        holder.thoigiantiem.setText(donHang.getTime());
        holder.btnHuyDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miClickListener.onClickListener(donHang);
            }
        });

    }

    @Override
    public int getItemCount() {
//        if(mlist != null){
//            return mlist.size();
//        }
        return mlist.size();
    }

    public class DonHangViewHolder extends  RecyclerView.ViewHolder {

        private TextView dchi, thoigiantiem;
        private Button btnHuyDatHang, btnHoanThanh;
        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);

            dchi = itemView.findViewById(R.id.tvhienthidiachi);
            thoigiantiem = itemView.findViewById(R.id.tvhienthithoigian);
            btnHuyDatHang = itemView.findViewById(R.id.btnHUYDatHang);
            btnHoanThanh = itemView.findViewById(R.id.btnHoanThanh);

        }
    }
}
