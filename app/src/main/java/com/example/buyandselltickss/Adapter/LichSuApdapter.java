package com.example.buyandselltickss.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.buyandselltickss.Fragment.HoanThanh;
import com.example.buyandselltickss.Fragment.XacNhanDonHang;
public class LichSuApdapter extends FragmentStateAdapter {


    public LichSuApdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new XacNhanDonHang();
            case 1:
                return new HoanThanh();
            default:
                return new XacNhanDonHang();
        }
    }
    @Override
    public int getItemCount() {
        return 2;
    }
}
