package com.example.financeguysv1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.financeguysv1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {
   private EditText txtUsuario ;
    private EditText txtPassword ;
    private EditText txtConfirmSenha ;
    private EditText txtEmail ;
     private Button txtbutton  ;
     private String idUsuario ;
     private static FirebaseDatabase DB ;
     private static DatabaseReference myRefe ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro_activity);
        FirebaseDatabase db ;
        Component();
        txtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = txtUsuario.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confimPassword = txtConfirmSenha.getText().toString();
                if (nome.isEmpty()||email.isEmpty()||password.isEmpty()){
                   Snackbar snackbar = Snackbar.make(view,"todos os campos devem ser preenchidos ",Snackbar.LENGTH_LONG);
                   snackbar.setBackgroundTint(Color.WHITE);
                   snackbar.setTextColor(Color.BLACK);
                   snackbar.show();
               }if(!password.equals(confimPassword)){
                    Snackbar snackbar = Snackbar.make(view,"As senhas nao se correspodem ",Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else{
                   confirmaCadastro(view);

               }
            }
        });
    }
    private void confirmaCadastro(View view ){
        EditText txtEmail = findViewById(R.id.idEmail);
        EditText txtSenha = findViewById(R.id.idPassword);
        String email = txtEmail.getText().toString();
        String senha = txtSenha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if (task.isSuccessful()){
              salvandoNoBancoDeDados();
               Snackbar snackbar = Snackbar.make(view,"Cadastro realizado com sucesso  ",Snackbar.LENGTH_LONG);
               snackbar.setBackgroundTint(Color.WHITE);
               snackbar.setTextColor(Color.BLACK);
               snackbar.show();
               Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
               startActivity(intent);
           } else{
               String bk ;
               try{
                 throw task.getException();
               }catch (FirebaseAuthWeakPasswordException e){
                   bk = "Digite uma senha que contenha menos de 6 caracteres ";
               }catch (FirebaseAuthUserCollisionException e) {
                   bk = "Ja teve cadastro dessa conta ";
               } catch (FirebaseAuthInvalidCredentialsException e ){
                   bk = "Email ja cadastrado ";
               }catch (Exception e){
                    bk = "Erro ao cadastrar o usuario ";
               }
               Snackbar snackbar = Snackbar.make(view,bk,Snackbar.LENGTH_LONG);
               snackbar.setBackgroundTint(Color.WHITE);
               snackbar.setTextColor(Color.BLACK);
               snackbar.show();
           }
            }
        });

        }
   public void Component(){
    txtUsuario = findViewById(R.id.idNomeUsuario);
    txtPassword = findViewById(R.id.idPassword);
    txtConfirmSenha = findViewById(R.id.idConfimSenha);
    txtEmail = findViewById(R.id.idEmail);
    txtbutton = findViewById(R.id.idButton);
}
        private void salvandoNoBancoDeDados(){
            String nome = txtUsuario.getText().toString();
            String senha = txtPassword.getText().toString();
            String email =txtEmail.getText().toString();
            FirebaseFirestore fs = FirebaseFirestore.getInstance();
            Map<String,Object> usuario =new HashMap<>();
            usuario.put("Nome",nome);
            usuario.put("Senha",senha);
            usuario.put("E-mail",email);
            idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DocumentReference dc = fs.collection("Usuarios").document(idUsuario);
            dc.set(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db","Os dados foram salvos com sucesso");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db-Error","houve uma falha ao salvar os dados "+ e.toString());
                }
            });


        }
}