package com.castom;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    TextView profileNumber, profileLogin, profilePassword;
    Button editProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileNumber = findViewById(R.id.profileNumber);
        profileLogin = findViewById(R.id.profileLogin);
        profilePassword = findViewById(R.id.profilePassword);
        editProfile = findViewById(R.id.editButton);
        showAllUserData();
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passUserData();
            }
        });
    }
    public void showAllUserData(){

        String userUsername = profileNumber.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query checkUserDatabase = reference.orderByChild("number").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Intent intent = getIntent();
                String login = intent.getStringExtra("login");
                String number = intent.getStringExtra("number");
                String passwordUser = intent.getStringExtra("password");

                profileNumber.setText(number);
                profileLogin.setText(login);
                profilePassword.setText(passwordUser);
    }

            @Override
            public void onCancelled(DatabaseError error) {

            }

            public void passUserData(){
        String userUsername = profileNumber.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query checkUserDatabase = reference.orderByChild("number").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String numberFromDB = snapshot.child(userUsername).child("number").getValue(String.class);
                    String loginFromDB = snapshot.child(userUsername).child("login").getValue(String.class);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);

                    intent.putExtra("number", numberFromDB);
                    intent.putExtra("login", loginFromDB);
                    intent.putExtra("password", passwordFromDB);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
});
    }}