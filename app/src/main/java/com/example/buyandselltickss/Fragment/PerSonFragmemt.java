package com.example.buyandselltickss.Fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.buyandselltickss.ChangePassword;
import com.example.buyandselltickss.HomeMainActivity;
import com.example.buyandselltickss.MainActivity;
import com.example.buyandselltickss.R;
import com.example.buyandselltickss.classOject.DataBase;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.IOException;
import java.security.Permission;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import gun0912.tedbottompicker.TedBottomPicker;

import static android.content.Context.MODE_PRIVATE;
import static com.example.buyandselltickss.HomeMainActivity.KEY_NAME;
import static com.example.buyandselltickss.HomeMainActivity.MY_REQUEST_CODE;


public class PerSonFragmemt extends Fragment {
    SharedPreferences sharedPreferences;
    private View view;
    String  name2, matKhau2;
    TextView nameHienThi, doimatkhau;
    DataBase dataBase;
    public  static  CircleImageView profile_image;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person, container,false);
        doimatkhau = view.findViewById(R.id.doimatkhau);
        profile_image = view.findViewById(R.id.profile_image);

        sharedPreferences = getActivity().getSharedPreferences(KEY_NAME, MODE_PRIVATE);
        name2 = sharedPreferences.getString("taikhoan",null);
        matKhau2 = sharedPreferences.getString("matkhau", null);
        nameHienThi = view.findViewById(R.id.tenhienthi);
        dataBase = new DataBase(getActivity(),"DuLieuDangNhap.SQLite",null,1);

        doimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequesPermission();
            }
        });

        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        nameHienThi.setText(name2);
    }

    public void RequesPermission() {
        HomeMainActivity mainActivity = (HomeMainActivity) getActivity();
        if (mainActivity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mainActivity.openGallery();
            return;
        }

        if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            mainActivity.openGallery();
        } else {
            String [] permisstion = {Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permisstion, MY_REQUEST_CODE);
        }

    }
    public void setBitmapImageView (Bitmap bitmapImageView) {
        if (profile_image != null) {
            profile_image.setImageBitmap(bitmapImageView);
        }

    }
}

