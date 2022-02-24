package com.example.buyandselltickss;

 import androidx.activity.result.ActivityResult;
 import androidx.activity.result.ActivityResultCallback;
 import androidx.activity.result.ActivityResultLauncher;
 import androidx.activity.result.ActivityResultRegistryOwner;
 import androidx.activity.result.contract.ActivityResultContracts;
 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;
 import androidx.annotation.RequiresApi;
 import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
 import androidx.core.view.GravityCompat;
 import androidx.drawerlayout.widget.DrawerLayout;
 import androidx.fragment.app.Fragment;
 import androidx.fragment.app.FragmentTransaction;

 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.content.pm.PackageManager;
 import android.graphics.Bitmap;
 import android.graphics.Color;
 import android.graphics.PorterDuff;
 import android.graphics.drawable.ColorDrawable;
 import android.net.Uri;
 import android.os.Build;
 import android.os.Bundle;
 import android.provider.MediaStore;
 import android.view.Gravity;
 import android.view.LayoutInflater;
 import android.view.MenuItem;
 import android.view.View;
 import android.widget.EditText;
 import android.widget.TextView;

 import com.example.buyandselltickss.Fragment.HomeFragment;
 import com.example.buyandselltickss.Fragment.NotificationFragment;
 import com.example.buyandselltickss.Fragment.PerSonFragmemt;
 import com.example.buyandselltickss.Fragment.TipsFragment;
 import com.google.android.material.bottomnavigation.BottomNavigationView;
 import com.google.android.material.bottomsheet.BottomSheetDialog;
 import com.google.android.material.navigation.NavigationBarView;
 import com.google.android.material.navigation.NavigationView;

 import java.io.IOException;

 import de.hdodenhof.circleimageview.CircleImageView;

 import static com.example.buyandselltickss.Fragment.PerSonFragmemt.profile_image;


public class HomeMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    public static  final int MY_REQUEST_CODE = 10;
    private static final int FRAGMEN_HOME =0;
    private static final int FRAGMEN_NOTIFICATION =1;
    private static final int FRAGMEN_TIPS =2;
    private static final int FRAGMEN_PERSON =3;
    private  int CURRENTFRAGMENT = FRAGMEN_HOME;
    private  PerSonFragmemt mPerSonFragmemt = new PerSonFragmemt();
    private TextView textUsername;
    public static final String KEY_NAME = "saveData";
    SharedPreferences sharedPreferences;
    BottomSheetDialog bottomSheetDialog;
    public static Bitmap bitmap;

    private ActivityResultLauncher<Intent> activityResultLauncher =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() ==RESULT_OK) {
                Intent intent = result.getData();
                if (intent == null) {
                    return;
                }
                Uri uri =intent.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    mPerSonFragmemt.setBitmapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });


    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.navigatinonview);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        setSupportActionBar(toolbar);
         drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.navigatinon_home).setChecked(true);
        bottomNavigationView.getMenu().findItem(R.id.mHome).setChecked(true);

        //===============================================================================
        sharedPreferences =getSharedPreferences(KEY_NAME,MODE_PRIVATE);
        String name1 = sharedPreferences.getString("taikhoan",null);
        String name2 = sharedPreferences.getString("matkhau",null);

//        Intent intent = getIntent();
//        String name1 = intent.getStringExtra("tentaikhoan");
        View header = navigationView.getHeaderView(0);
        textUsername = header.findViewById(R.id.tennguoidung);
        textUsername.setText(name1);

        CircleImageView circleImageView = header.findViewById(R.id.profile_image1);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new PerSonFragmemt());
                bottomNavigationView.getMenu().findItem(R.id.mperson).setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                navigationView.callOnClick();

            }
        });
                header.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        replaceFragment(new PerSonFragmemt());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.callOnClick();
                    }
                });
                textUsername.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        replaceFragment(new PerSonFragmemt());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.callOnClick();
                    }
                });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.mHome){
                    OpenHomeFragment();
                    navigationView.getMenu().findItem(R.id.navigatinon_home).setChecked(true);
                }else if(id == R.id.notification){
                    OpenNotificatonFragment();
                    navigationView.getMenu().findItem(R.id.navigatinon_notification).setChecked(true);
                }else  if(id == R.id.mtips){
                    OpenTipsFragment();
                    navigationView.getMenu().findItem(R.id.navigatinon_tips).setChecked(true);
                }else  if(id == R.id.mperson){
                    OpenPerSonFragment();
                    navigationView.getMenu().findItem(R.id.navigatinon_person).setChecked(true);
                }else {
                    Intent intent = new Intent(HomeMainActivity.this, HomeSell.class);
                    startActivity(intent);
                }

                return true;
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.navigatinon_home){
            OpenHomeFragment();
            bottomNavigationView.getMenu().findItem(R.id.mHome).setChecked(true);
        }else if(id == R.id.navigatinon_notification){
            OpenNotificatonFragment();
            bottomNavigationView.getMenu().findItem(R.id.notification).setChecked(true);
        }else if(id == R.id.navigatinon_tips){
            OpenTipsFragment();
            bottomNavigationView.getMenu().findItem(R.id.mtips).setChecked(true);
        }else if(id == R.id.navigatinon_person){
            OpenPerSonFragment();
            bottomNavigationView.getMenu().findItem(R.id.mperson).setChecked(true);
        }else if(id == R.id.logout){
            bottomSheetDialog = new BottomSheetDialog(HomeMainActivity.this, R.style.BottomSheetTheme);
            View sheetview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_custom_bottom_sheet, null);

            sheetview.findViewById(R.id.txtDangXuat).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("taikhoan");
                editor.remove("matkhau");
                editor.apply();
                Intent intent = new Intent(HomeMainActivity.this, MainActivity.class);
                startActivity(intent);
                }
            });
            sheetview.findViewById(R.id.txtHuyDangXuat).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetDialog.setContentView(sheetview);
            bottomSheetDialog.show();


        }else if(id == R.id.contact){
            Intent intent = new Intent(HomeMainActivity.this,ContactMain.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
            return true;
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    private void OpenHomeFragment(){
        if(CURRENTFRAGMENT != FRAGMEN_HOME){
            replaceFragment(new HomeFragment());
            CURRENTFRAGMENT = FRAGMEN_HOME;
        }
    }
    private  void OpenNotificatonFragment(){
        if(CURRENTFRAGMENT != FRAGMEN_NOTIFICATION){
            replaceFragment(new NotificationFragment());
            CURRENTFRAGMENT = FRAGMEN_NOTIFICATION;
        }
    }
    private void OpenTipsFragment(){
        if(CURRENTFRAGMENT != FRAGMEN_TIPS){
            replaceFragment(new TipsFragment());
            CURRENTFRAGMENT = FRAGMEN_TIPS;
        }
    }
    private void OpenPerSonFragment(){
        if(CURRENTFRAGMENT != FRAGMEN_PERSON){
            replaceFragment(mPerSonFragmemt);
            CURRENTFRAGMENT = FRAGMEN_PERSON;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contenFrame,fragment );
        fragmentTransaction.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }
    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));


    }
}