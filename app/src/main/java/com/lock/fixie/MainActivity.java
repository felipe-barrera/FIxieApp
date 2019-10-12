package com.lock.fixie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.lock.fixie.Fragments.MenuprincipalFragment;
import com.lock.fixie.Interfaces.IComunicaFragments;

public class MainActivity extends AppCompatActivity implements IComunicaFragments, MenuprincipalFragment.OnFragmentInteractionListener {


    private FirebaseAuth mAuth;
    Fragment fragmentInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);
        fragmentInicio = new MenuprincipalFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();


    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void Imagenes() {
        Intent I = new Intent(MainActivity.this, Cam_Activity.class);
        startActivity(I);

    }
    public void Ajustes() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent O = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(O);
        finish();

    }
    public void Perfil() {
        Toast.makeText(getApplicationContext(), "Ver Perfil", Toast.LENGTH_SHORT).show();

    }
    public void Registros() {
    }

}
