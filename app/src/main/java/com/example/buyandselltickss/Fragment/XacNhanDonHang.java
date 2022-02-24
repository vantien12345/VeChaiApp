package com.example.buyandselltickss.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyandselltickss.Adapter.DonHangAdapter;
import com.example.buyandselltickss.R;
import com.example.buyandselltickss.ScrapBoardScreen.DonHang;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class XacNhanDonHang extends Fragment {
    private  View view;
    private RecyclerView recyclerView;
    private DonHangAdapter donHangAdapter;
    private List<DonHang> listDonHang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_xac_nhan_don_hang, container, false);
        recyclerView = view.findViewById(R.id.rcv_DonHang);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        listDonHang = new ArrayList<>();
        donHangAdapter = new DonHangAdapter(listDonHang, new DonHangAdapter.IClickListener() {
            @Override
            public void onClickListener(DonHang donHang) {
                dialog(donHang);
            }
        });
        recyclerView.setAdapter(donHangAdapter);

        getListDataBase();

        return view;
    }

    private void getListDataBase (){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myrefence = database.getReference("Danh Sach Don Hang");
//        myrefence.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    DonHang donHang = dataSnapshot.getValue(DonHang.class);
//                    listDonHang.add(donHang);
//                }
//                donHangAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getActivity(),"đã xảy ra lỗi",Toast.LENGTH_LONG).show();
//
//            }
//        });

        myrefence.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DonHang donHang = snapshot.getValue(DonHang.class);
                if(donHang != null) {
                    listDonHang.add(donHang);
                    donHangAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DonHang donHang = snapshot.getValue(DonHang.class);
                if(donHang == null || listDonHang == null || listDonHang.isEmpty()) {
                    return;
                }
                for (int i = 0; i < listDonHang.size(); i++ ) {
                    if (donHang.getAddress().equals(listDonHang.get(i).getAddress())) {
                        listDonHang.remove(listDonHang.get(i));
                        break;
                    }
                }
                donHangAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void dialog (DonHang donHang) {
        final Dialog dialog2 = new Dialog(getActivity());
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.layout_custom_dialog_xoadonhang);

        Window window = dialog2.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windownAttributes = window.getAttributes();
        window.setAttributes(windownAttributes);
        dialog2.setCancelable(false);
        Button btnHuyXoa, btnXacNhanXoa;
        btnHuyXoa = dialog2.findViewById(R.id.btn_huyXoa);
        btnXacNhanXoa = dialog2.findViewById(R.id.btnXacNhanXoa);
        btnHuyXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        btnXacNhanXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myrefence = database.getReference("Danh Sach Don Hang");

                myrefence.child(String.valueOf(donHang.getAddress())).removeValue();
                dialog2.dismiss();
            }
        });

        dialog2.show();

    }
}
