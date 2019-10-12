package com.lock.fixie;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrarseActivity extends AppCompatActivity {


    private EditText mEditTexName;
    private EditText mEditTextApellidos;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonRegister;

    // Variables de datos a registrar
    private String nombre= "";
    private String apellidos= "";
    private String email= "";
    private String password= "";
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mEditTexName = (EditText) findViewById(R.id.Nombres);
        mEditTextApellidos = (EditText) findViewById(R.id.Apellidos);
        mEditTextEmail = (EditText) findViewById(R.id.Email);
        mEditTextPassword = (EditText) findViewById(R.id.Contraseña);
        mButtonRegister = (Button) findViewById(R.id.Registrarme);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = mEditTexName.getText().toString();
                apellidos = mEditTextApellidos.getText().toString();
                email = mEditTextEmail.getText().toString();
                password = mEditTextPassword.getText().toString();


                if (!nombre.isEmpty() && !apellidos.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    if (password.length() >= 6) {
                        registerUser();

                    } else {
                        Toast.makeText(RegistrarseActivity.this, "La contraseña debe tener al menos  6 caracteres", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(RegistrarseActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void registerUser(){
       mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Map <String, Object> map = new HashMap<>();
                   map.put("nombre", nombre);
                   map.put("email", email);
                   map.put("password", password);
                   String id = mAuth.getCurrentUser().getUid();
                   mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task2) {
                           if(task2.isSuccessful()){
                               startActivity(new Intent(RegistrarseActivity.this, LoginActivity.class));
                               finish();
                           }else{
                               startActivity(new Intent(RegistrarseActivity.this, LoginActivity.class));
                               finish();
                           }
                       }
                   });
               }else{
                   Toast.makeText(RegistrarseActivity.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
}
