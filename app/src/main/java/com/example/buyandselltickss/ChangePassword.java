package com.example.buyandselltickss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buyandselltickss.classOject.DataBase;

import static com.example.buyandselltickss.HomeMainActivity.KEY_NAME;
import static com.example.buyandselltickss.MainActivity.dataBase;

public class ChangePassword extends AppCompatActivity {

    EditText matKhauCu, matKhaumoi, nhapLaiMatKhau;
    Button bntHuy, btnDoiMatKhau,btnXacNhan,btnXacNhan1,btnXacNhan2;
    String name1,matkhau2,edtMatKhauCu, edtMatKhauMoi, edtNhapLaiMatKhau;
    SharedPreferences sharedPreferences;
   // DataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        matKhauCu = findViewById(R.id.matKhauCu);
        matKhaumoi = findViewById(R.id.matKhauMoi);
        nhapLaiMatKhau = findViewById(R.id.nhapLaiMatKhau);
        bntHuy = findViewById(R.id.btnHuyDoiMatKhau);
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau);
        sharedPreferences =getSharedPreferences(KEY_NAME,MODE_PRIVATE);
        name1 = sharedPreferences.getString("taikhoan",null);
        matkhau2 = sharedPreferences.getString("matkhau",null);
        //dataBase = new DataBase(this,"DuLieuDangNhap.SQLite",null,1);

        bntHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this, HomeMainActivity.class);
                startActivity(intent);
            }
        });

        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMatKhauCu = matKhauCu.getText().toString().trim();
                edtMatKhauMoi = matKhaumoi.getText().toString().trim();
                edtNhapLaiMatKhau = nhapLaiMatKhau.getText().toString().trim();
                if(edtMatKhauCu.equals(matkhau2) && edtMatKhauCu != null && edtMatKhauMoi.equals(edtNhapLaiMatKhau)){
                    dataBase.QueryData("UPDATE ThongTinDangNhapB SET matkhau = '"+ edtMatKhauMoi +"' WHERE taikhoan = '"+ name1 +"' ");
                    Dialog(Gravity.CENTER);
                }else if(edtMatKhauCu.equals(matkhau2) && edtMatKhauMoi != edtNhapLaiMatKhau){
                    Dialog2(Gravity.CENTER);
                }
                else{
                    Dialog1(Gravity.CENTER);
                }
            }
        });

    }
    private void Dialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_custom_dialog_doimatkhauthanhcong);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windownAttributes = window.getAttributes();
        windownAttributes.gravity = gravity;
        window.setAttributes(windownAttributes);
        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        btnXacNhan = dialog.findViewById(R.id.btnXacNhanDMK);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeMainActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }
    private void Dialog1(int gravity) {
        final Dialog dialog1 = new Dialog(this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.layout_custom_dialog_doimatkhauthatbai1);

        Window window = dialog1.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windownAttributes = window.getAttributes();
        windownAttributes.gravity = gravity;
        window.setAttributes(windownAttributes);
        if (Gravity.BOTTOM == gravity) {
            dialog1.setCancelable(true);
        } else {
            dialog1.setCancelable(false);
        }

        btnXacNhan1 = dialog1.findViewById(R.id.btnXacNhan1);

        btnXacNhan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        dialog1.show();
    }
    private void Dialog2(int gravity) {
        final Dialog dialog2 = new Dialog(this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.layout_custom_dialog_doimatkhauthatbai2);

        Window window = dialog2.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windownAttributes = window.getAttributes();
        windownAttributes.gravity = gravity;
        window.setAttributes(windownAttributes);
        if (Gravity.BOTTOM == gravity) {
            dialog2.setCancelable(true);
        } else {
            dialog2.setCancelable(false);
        }

        btnXacNhan2 = dialog2.findViewById(R.id.btnXacNhan2);

        btnXacNhan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        dialog2.show();
    }
}