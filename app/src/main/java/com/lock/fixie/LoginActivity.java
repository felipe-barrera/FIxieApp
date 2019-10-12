package com.lock.fixie;

import android.content.Intent;
import android.net.nsd.NsdManager;
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


public class LoginActivity extends AppCompatActivity {

    private EditText mEditTextContraseña;
    private EditText mEditTextEmail;

    private Button Login;
    TextView Registrarse;

    private String email = "";
    private String password = "";

    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        Registrarse = (TextView) findViewById(R.id.Registrarse);
        mEditTextContraseña = (EditText) findViewById(R.id.editText2);
        mEditTextEmail = (EditText) findViewById(R.id.email);
        Login = (Button) findViewById(R.id.ingresar);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEditTextEmail.getText().toString();
                password = mEditTextContraseña.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    loginUser();
                } else {
                    Toast.makeText(LoginActivity.this, "La contraseña debe tener al menos  6 caracteres", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Registrarse = (TextView) findViewById(R.id.Registrarse);

        Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent I = new Intent(LoginActivity.this, RegistrarseActivity.class);
                startActivity(I);
            }
        });


    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             if(task.isSuccessful()){
                 startActivity(new Intent(LoginActivity.this, MainActivity.class));
                 finish();
             }else{
                 Toast.makeText(LoginActivity.this, "Error compruebe sus datos", Toast.LENGTH_SHORT).show();
             }
            }
        });
    }
}
