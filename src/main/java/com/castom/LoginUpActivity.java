package com.castom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginUpActivity extends AppCompatActivity {

    Button enter;
    EditText loginUsername;
    EditText loginPassword;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_up);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        enter = findViewById(R.id.enter1);
        loginText = findViewById(R.id.loginText);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginUsername.getText().toString().equals("admin") &&//авторизация для админа
                        loginPassword.getText().toString().equals("danganropa")){
                    startActivity(new Intent(LoginUpActivity.this, MainProfAcrivity.class));

                }
                else if(!validateUserName() | ! validatePassword()){

                } else {
                    checkUser();
                }

            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginUpActivity.this, SingUp.class));
            }
        });
    }

    public Boolean validateUserName(){
        String val = loginUsername.getText().toString();
        if (val.isEmpty()){
            loginUsername.setError("Введите логин");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()){
            loginPassword.setError("Введите пароль");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String num = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query checkUserDatabase = reference.orderByChild("number").equalTo(num);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.exists()){

                    String passwordFromDb = snapshot.child(num).child("password").getValue(String.class);

                    if (passwordFromDb.equals(userPassword)){
                        loginUsername.setError(null);
                        Intent intent = new Intent(LoginUpActivity.this, MainProfAcrivity.class);

                        String nameDb = snapshot.child(num).child("number").getValue(String.class);
                        //String emailDb = snapshot.child(num).child("login").getValue(String.class);

                        intent.putExtra("number", nameDb);
                        //intent.putExtra("login", emailDb);
                        intent.putExtra("password", passwordFromDb);

                        startActivity(intent);

                    } else {
                        loginPassword.setError("Пароль неверный");
                        loginPassword.requestFocus();
                    }
                } else {
                    loginUsername.setError("Пользователь не найден");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

}