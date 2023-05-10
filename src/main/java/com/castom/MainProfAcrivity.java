package com.castom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainProfAcrivity extends AppCompatActivity {

    ImageView icon_chat, icon_back, icon_profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_prof);

        icon_chat = findViewById(R.id.icon_chat);
        icon_back = findViewById(R.id.icon_back);
        icon_profil = findViewById(R.id.icon_person);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainProfAcrivity.this, MainActivity.class));
            }
        });

        icon_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainProfAcrivity.this, ProfileActivity.class));
            }
        });


    }
}