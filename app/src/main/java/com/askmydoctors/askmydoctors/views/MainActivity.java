package com.askmydoctors.askmydoctors.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.adapters.PagerAdapterDokter;
import com.askmydoctors.askmydoctors.utils.Config;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    public static  final String DEFAULT = "N/A";
    ViewPager pager;
    TabLayout tabLayout;
    String kode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String username = Config.GetString(this, "username");

        if (username.equals(DEFAULT) | username.equals("") ){
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);

        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        kode = Config.GetString(MainActivity.this, "kode");

//        String tkn = FirebaseInstanceId.getInstance().getToken();
//        Log.d("App", "Token ["+tkn+"]");

        if (kode.equals("2")){
            FragmentManager manager=getSupportFragmentManager();
            PagerAdapterDokter adapter=new com.askmydoctors.askmydoctors.adapters.PagerAdapterDokter(manager);
            pager.setAdapter(adapter);

            tabLayout.setupWithViewPager(pager);
            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setTabsFromPagerAdapter(adapter);
        }else if (kode.equals("3")){
            FragmentManager manager=getSupportFragmentManager();
            PagerAdapter adapter=new com.askmydoctors.askmydoctors.adapters.PagerAdapter(manager);
            pager.setAdapter(adapter);

            tabLayout.setupWithViewPager(pager);
            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setTabsFromPagerAdapter(adapter);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (kode.equals("2")){
            getMenuInflater().inflate(R.menu.menu_item, menu);
        }else if (kode.equals("3")){
            getMenuInflater().inflate(R.menu.menu_item_pengguna, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profil:
                Intent i = new Intent(MainActivity.this, ProfilDokterActivity.class);
                i.putExtra("username", Config.GetString(MainActivity.this, "username"));
                startActivity(i);
                return true;

            case R.id.action_logout:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("Apa anda yakin ingin keluar?");
                alertDialogBuilder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Config.SetString(MainActivity.this, "username", "N/A");
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }
                        });

                alertDialogBuilder.setNegativeButton("Tidak",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });

                //Showing the alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }


}
