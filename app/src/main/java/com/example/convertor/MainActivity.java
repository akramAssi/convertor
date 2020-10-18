package com.example.convertor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.text.Bidi;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        nav = findViewById(R.id.bottomNav);
        getSupportFragmentManager().beginTransaction().replace(R.id.cont,new darkFragment()).commit();
        nav.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId())
                {


                    case R.id.light:
                    {
                        fragment =new lightFragment();
                        nav.setBackgroundResource(R.color.bottom_navigation);
                        nav.setItemIconTintList(ContextCompat.getColorStateList(getBaseContext(), R.drawable.tab_light));
                        nav.setItemTextColor( ContextCompat.getColorStateList(getBaseContext(), R.drawable.tab_light));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.status));

                        break;
                    }
                    case R.id.normal:
                    {
                        fragment =new normalFragment();
                        nav.setBackgroundResource(R.color.bottom_navigation);
                        nav.setItemIconTintList(ContextCompat.getColorStateList(getBaseContext(), R.drawable.tab_light));
                        nav.setItemTextColor( ContextCompat.getColorStateList(getBaseContext(), R.drawable.tab_light));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.status));
                        break;
                    }
                    case R.id.dark:
                    {
                        fragment =new darkFragment();
                        nav.setBackgroundResource(R.color.bottom_navigation_dark);
                        nav.setItemIconTintList(ContextCompat.getColorStateList(getBaseContext(), R.drawable.tab_color));
                        nav.setItemTextColor( ContextCompat.getColorStateList(getBaseContext(), R.drawable.tab_color));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.backGround_dark));
                    }
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.cont,fragment).commit();
                return true;
            }
        });

//        setLtr();


    }

    private void setLtr() {
        String languageToLoad = "en"; // ltr language English
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        //layout direction
        Bidi b = new Bidi(languageToLoad, Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
        b.isLeftToRight();
        //save current locale in SharedPreferences
        SharedPreferences languagepref = getSharedPreferences("language", MODE_PRIVATE);
        SharedPreferences.Editor editor = languagepref.edit();
        editor.putString("languageToLoad", languageToLoad);
        editor.commit();

        Intent refresh = new Intent(this, MainActivity.class);
        finish();
        startActivity(refresh);
    }

}