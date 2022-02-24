package com.example.buyandselltickss.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyandselltickss.Fragment.HomeFragment;
import com.example.buyandselltickss.HomeSell;
import com.example.buyandselltickss.R;
import com.example.buyandselltickss.ScrapBoardScreen.GiayBao;
import com.example.buyandselltickss.ScrapBoardScreen.GiayHoSo;
import com.example.buyandselltickss.ScrapBoardScreen.GiayThung;
import com.example.buyandselltickss.ScrapBoardScreen.LonNhom;
import com.example.buyandselltickss.ScrapBoardScreen.MuBInh;
import com.example.buyandselltickss.ScrapBoardScreen.MuNhua;
import com.example.buyandselltickss.ScrapBoardScreen.Nhom;
import com.example.buyandselltickss.ScrapBoardScreen.SatDac;
import com.example.buyandselltickss.ScrapBoardScreen.SatTon;
import com.example.buyandselltickss.ScrapBoardScreen.SatVun;
import com.example.buyandselltickss.classOject.BangGia;

import java.util.List;

import static java.security.AccessController.getContext;

public class BangPheLieuAdapter extends RecyclerView.Adapter<BangPheLieuAdapter.BangPhelieuHolder> {
    private Context context;
    private List<BangGia> mlist;

    public BangPheLieuAdapter(Context context) {
        this.context = context;
    }
    public void setDate(List<BangGia> list){
        this.mlist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BangPhelieuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_phe_lieu, parent, false);

        return new BangPhelieuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BangPhelieuHolder holder, int position) {
        BangGia bangGia = mlist.get(position);
        if(bangGia == null){
            return;
        }
        holder.hinh.setImageResource(bangGia.getHinh());
        holder.tenPheLieu.setText(bangGia.getTenPheLieu());
        holder.giaPhelieu.setText(bangGia.getGiaPheLieu());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(v.getContext(), GiayBao.class);
                        v.getContext().startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(v.getContext(), GiayHoSo.class);
                        v.getContext().startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(v.getContext(), GiayThung.class);
                        v.getContext().startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(v.getContext(), SatDac.class);
                        v.getContext().startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(v.getContext(), SatVun.class);
                        v.getContext().startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(v.getContext(), SatTon.class);
                        v.getContext().startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(v.getContext(), MuBInh.class);
                        v.getContext().startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(v.getContext(), MuNhua.class);
                        v.getContext().startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8 = new Intent(v.getContext(), Nhom.class);
                        v.getContext().startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9 = new Intent(v.getContext(), LonNhom.class);
                        v.getContext().startActivity(intent9);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mlist != null){
            return mlist.size();
        }
        return 0;
    }

    public class BangPhelieuHolder extends RecyclerView.ViewHolder{
        private ImageView hinh;
        TextView tenPheLieu, giaPhelieu;


        public BangPhelieuHolder(@NonNull View itemView) {
            super(itemView);
            hinh = itemView.findViewById(R.id.hinhPheLieu);
            tenPheLieu = itemView.findViewById(R.id.tenPheLieu);
            giaPhelieu  = itemView.findViewById(R.id.giaPheLieu);



        }
    }

}
