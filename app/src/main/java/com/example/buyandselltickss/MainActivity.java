package com.example.buyandselltickss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buyandselltickss.classOject.DataBase;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_NAME = "saveData";
    EditText name, password, nameDK, passwordk;
    TextView dangKy;
    Button login, huy, dangkylogin;
    String tenTKDK, matKhauDK, tk,mk;
    SharedPreferences sharedPreferences;
    public static DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =  findViewById(R.id.edtName);
        password =  findViewById(R.id.edtPasswword);
        dangKy =  findViewById(R.id.txtDangKy);
        login =  findViewById(R.id.btnLogin);
        sharedPreferences = getSharedPreferences(KEY_NAME, MODE_PRIVATE);
        String name1 = sharedPreferences.getString("taikhoan",null);
        String password1 = sharedPreferences.getString("matkhau",null);
        if(name1 != null && password1 != null){
            Intent intent = new Intent(MainActivity.this, HomeMainActivity.class);
            startActivity(intent);
        }
        // tạo database
        dataBase = new DataBase(this,"DuLieuDangNhap.SQLite",null,1);
        // tạo bảng
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS ThongTinDangNhapB(taikhoan VARCHAR(100) , matkhau VARCHAR(100))");

        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog(Gravity.CENTER);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String layTaiKhoan = name.getText().toString();
                String layMatKhau = password.getText().toString();

                Cursor thongtin = dataBase.GetData("SELECT * FROM ThongTinDangNhapB");
                while (thongtin.moveToNext()) {
                    tk = thongtin.getString(0);
                    mk = thongtin.getString(1);
                    //Toast.makeText(MainActivity.this, tk, Toast.LENGTH_LONG).show();
                    if(layTaiKhoan != null && layMatKhau != null || layTaiKhoan.isEmpty() && layMatKhau.isEmpty()){
                        if(layTaiKhoan.equals(tk) && layMatKhau.equals(mk)){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", layTaiKhoan);
                        editor.putString("matkhau",layMatKhau);
                        editor.apply();
                            Intent intent = new Intent(MainActivity.this, HomeMainActivity.class);
                            intent.putExtra("tentaikhoan",layTaiKhoan);
                            startActivity(intent);
                        }
                    }


                }

            }
        });
    }

//    // xu ly dialog
    private void Dialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_customdiglog);

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
        huy =  dialog.findViewById(R.id.btnHuy);
        dangkylogin =  dialog.findViewById(R.id.btnDangKyLogin);
        nameDK =  dialog.findViewById(R.id.editNameDK);
        passwordk =  dialog.findViewById(R.id.edtPassworđK);

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dangkylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenTKDK = nameDK.getText().toString();
                matKhauDK = passwordk.getText().toString();
                if(tenTKDK.equals("") && matKhauDK.equals("")){
                    Toast.makeText(MainActivity.this, "vui long nhap thong tin", Toast.LENGTH_LONG).show();
                }else {
                    dataBase.QueryData("INSERT INTO ThongTinDangNhapB VALUES('"+ tenTKDK +"','"+ matKhauDK +"')");
                    Toast.makeText(MainActivity.this, "đăng ký thành công", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

       dialog.show();
   }
}