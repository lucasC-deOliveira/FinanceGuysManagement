package com.example.financeguysv1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.financeguysv1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
        private EditText txtemail ;
        private EditText txtSenha;
        private Button btnEnviar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MainActivity.usuario.signOut();
        componentes();
        btnEnviar.setOnClickListener(view -> {
            txtSenha= findViewById(R.id.idSenhaUsuario);
            txtemail = findViewById(R.id.idemailUsario);
            String senha = txtSenha.getText().toString();
            String email = txtemail.getText().toString();
            if (senha.isEmpty()||email.isEmpty()){
                Snackbar snackbar = Snackbar.make(view,"Campos devem ser preenchidos ",Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            }else{
                verificarUsuario(view);
            }
        });

    }

    private void verificarUsuario(View view) {
        String senha = txtSenha.getText().toString();
        String email = txtemail.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Snackbar snackbar = Snackbar.make(view,"Conta logada com sucesso ",Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            telaMenu();
                        }
                    },2000);
                }
            }
        });
    }


private void telaMenu(){
    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
    startActivity(intent);
    finish();

}

    private void componentes(){
txtSenha = findViewById(R.id.idNomeUsuario);
txtemail = findViewById(R.id.idSenhaUsuario);
btnEnviar = findViewById(R.id.idLogar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioLocal = FirebaseAuth.getInstance().getCurrentUser();
        if (usuarioLocal != null){
            telaMenu();
        }
    }
}