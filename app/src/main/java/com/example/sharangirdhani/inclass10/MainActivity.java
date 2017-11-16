/*
    InClass 10
    Salman Mujtaba 800969897
    Sharan Giridhani 800960333
    MainActivity
*/

package com.example.sharangirdhani.inclass10;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener{

    EditText editLoginEmail;
    EditText editLoginPassword;

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getString(R.string.loginActivityTitle));

        editLoginEmail = (EditText) findViewById(R.id.editLoginEmail);
        editLoginPassword = (EditText) findViewById(R.id.editLoginPassword);

        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(this);

        firebaseDatabase = FirebaseDatabase.getInstance();

        ((Button) findViewById(R.id.buttonCreate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.buttonLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validation

                String email = editLoginEmail.getText().toString();
                String password = editLoginPassword.getText().toString();

                if (email.length() == 0) {
                    editLoginEmail.setError("Please enter e-mail");
                }
                if (password.length() == 0 ) {
                    editLoginPassword.setError("Please enter your password");
                }
                if (email.length() != 0 && password.length() != 0) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login was not successful", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Login was successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//        Intent intent = new Intent(this,ContactListActivity.class);
//        startActivity(intent);
    }
}
