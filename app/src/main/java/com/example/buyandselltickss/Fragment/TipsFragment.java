package com.example.buyandselltickss.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.buyandselltickss.Adapter.DonHangAdapter;
import com.example.buyandselltickss.Adapter.LichSuApdapter;
import com.example.buyandselltickss.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TipsFragment  extends Fragment {
    private  View view;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2xacnhan;
    private FragmentActivity context;
    private LichSuApdapter lichSuApdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tips, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2xacnhan = view.findViewById(R.id.Vỉewpager2history);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        lichSuApdapter = new LichSuApdapter(fm,getLifecycle());
        viewPager2xacnhan.setAdapter(lichSuApdapter);


        new TabLayoutMediator(tabLayout, viewPager2xacnhan, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Xác Nhận Đơn Hàng");
                        break;
                    case 1:
                        tab.setText("Hoàn Thành Đơn Hàng");
                        break;
                }
            }
        }).attach();

        return view;
    }
}
