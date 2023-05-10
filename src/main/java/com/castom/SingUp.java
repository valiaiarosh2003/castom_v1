package com.castom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingUp extends AppCompatActivity {

    EditText number_phone, reg_login, reg_password;
    Button enter2;
    TextView loginRegistText;

    FirebaseDatabase database;
    DatabaseReference reference;
    String idUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        number_phone = findViewById(R.id.number_phone);
        //reg_login = findViewById(R.id.reg_login);
        reg_password = findViewById(R.id.reg_password);
        enter2 = (Button) findViewById(R.id.enter2);
        loginRegistText = findViewById(R.id.loginRegistText);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        enter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("User");

                String number = number_phone.getText().toString();
                //String login = reg_login.getText().toString();
                String password = reg_password.getText().toString();
                idUser = FirebaseDatabase.getInstance().getReference().child("User").push().getKey();

                HelperClass helperClass = new HelperClass(number, password, idUser);
                reference.child(number).setValue(helperClass);

                progressDialog.dismiss();

                Toast.makeText(SingUp.this, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SingUp.this, MainProfAcrivity.class);
                startActivity(intent);
            }
        });


        loginRegistText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingUp.this, LoginUpActivity.class));
            }
        });
    }
}