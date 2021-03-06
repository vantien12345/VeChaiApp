package com.example.buyandselltickss.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.buyandselltickss.Adapter.BangPheLieuAdapter;
import com.example.buyandselltickss.Adapter.PhotoAdapter;
import com.example.buyandselltickss.R;
import com.example.buyandselltickss.classOject.BangGia;
import com.example.buyandselltickss.classOject.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment  extends Fragment {
    private View mview;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private List<Photo> listPhoto;
    private Timer mtimer;

    private RecyclerView recyclerView;
    private BangPheLieuAdapter adapter;
    private List<BangGia> bangGiaList;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_home,container, false);


        recyclerView = mview.findViewById(R.id.RecyclerView);

        viewPager = mview.findViewById(R.id.view_pager);
        circleIndicator = mview.findViewById(R.id.circle_center);

        photoAdapter = new PhotoAdapter(this, listPhoto());
         viewPager.setAdapter(photoAdapter);
         circleIndicator.setViewPager(viewPager);
         adapter = new BangPheLieuAdapter(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.setDate(getList());
        recyclerView.setAdapter(adapter);

// =================================================================

         // ========================================================
         photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
         autoSlideImg();


        return mview;
    }
    private List<BangGia> getList(){
        bangGiaList = new ArrayList<>();
        bangGiaList.add(new BangGia(R.drawable.giaybao , "GI???Y B??O" ,"6,200"));
        bangGiaList.add(new BangGia(R.drawable.giayhoso , "GI???Y H??? S??" ,"3,700"));
        bangGiaList.add(new BangGia(R.drawable.giaythung1 , "GU???Y TH??NG" ,"3,500"));
        bangGiaList.add(new BangGia(R.drawable.satdac , "S???T ?????C" ,"6,700"));
        bangGiaList.add(new BangGia(R.drawable.satvun , "S???T V???N" ,"3,000"));
        bangGiaList.add(new BangGia(R.drawable.satton , "S???T T??N" ,"5.500"));
        bangGiaList.add(new BangGia(R.drawable.mubinh , "M?? B??NH" ,"1000"));
        bangGiaList.add(new BangGia(R.drawable.munhua , "M?? NH??A" ,"3,700"));
        bangGiaList.add(new BangGia(R.drawable.noinhom , "NH??M" ,"7,000"));
        bangGiaList.add(new BangGia(R.drawable.lonnhom , "LON NH??M" ,"3,500"));



        return  bangGiaList;
    }


    private List<Photo> listPhoto() {
        listPhoto = new ArrayList<>();
        listPhoto.add(new Photo(R.drawable.anh1));
        listPhoto.add(new Photo(R.drawable.anh2));
        listPhoto.add(new Photo(R.drawable.anh3));
        listPhoto.add(new Photo(R.drawable.anh5));
        listPhoto.add(new Photo(R.drawable.anh6));
        return listPhoto;
    }
    private void autoSlideImg(){
        if(listPhoto == null || listPhoto.isEmpty() || viewPager == null){

        }

        if(mtimer == null){
            mtimer = new Timer();
        }
        mtimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                         int currentItem = viewPager.getCurrentItem();
                         int totalItem = listPhoto.size() - 1;
                         if(currentItem < totalItem){
                             currentItem++;
                             viewPager.setCurrentItem(currentItem);
                         }else {
                             viewPager.setCurrentItem(0);
                         }
                    }
                });
            }
        }, 500, 3000 );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mtimer != null){
            mtimer.cancel();
            mtimer = null;
        }
    }
}
