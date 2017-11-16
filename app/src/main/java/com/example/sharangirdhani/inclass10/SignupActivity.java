/*
    InClass 10
    Salman Mujtaba 800969897
    Sharan Giridhani 800960333
    SignupActivity
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
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle(getString(R.string.signupActivityTitle));

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        ((Button) findViewById(R.id.buttonCancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onClickSignup(View v){
        final String firstName = ((EditText) findViewById(R.id.editSignupFirstName)).getText().toString();
        final String lastName = ((EditText) findViewById(R.id.editSignupLastName)).getText().toString();
        final String email = ((EditText) findViewById(R.id.editSignupEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editSignupPassword)).getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.editSignupConfirmPassword)).getText().toString();
        boolean isValid = true;

        if (firstName.length() == 0) {
            ((EditText) findViewById(R.id.editSignupFirstName)).setError("Please provide your First Name");
            isValid = false;
        }

        if (lastName.length() == 0) {
            ((EditText) findViewById(R.id.editSignupLastName)).setError("Please provide your Last Name");
            isValid = false;
        }
        if (email.length() == 0) {
            ((EditText) findViewById(R.id.editSignupEmail)).setError("Please provide your E-mail");
            isValid = false;
        }
        if (password.length()==0){
            ((EditText) findViewById(R.id.editSignupPassword)).setError("Please provide a password");
            isValid = false;
        }
        if (!password.equals(confirmPassword)) {
            ((EditText) findViewById(R.id.editSignupConfirmPassword)).setError("Passwords do not match");
            isValid = false;
        }

        if (isValid) {
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String fullname = firstName + lastName;
                        Toast.makeText(SignupActivity.this,"User created successfully",Toast.LENGTH_SHORT).show();
                        FirebaseDatabase.getInstance().getReference("users")
                                .child(firebaseAuth.getCurrentUser().getUid())
                                .child("fullname").setValue(fullname);

                        Intent intent = new Intent(SignupActivity.this, ContactListActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        try{
                            throw task.getException();
                        }catch (FirebaseAuthWeakPasswordException e) {
                            Toast.makeText(SignupActivity.this,"Password too weak",Toast.LENGTH_SHORT).show();
                        }catch (FirebaseAuthUserCollisionException e) {
                            Toast.makeText(SignupActivity.this,"Email already exists",Toast.LENGTH_SHORT).show();
                        }catch (FirebaseAuthInvalidUserException e) {
                            Toast.makeText(SignupActivity.this,"Email not valid",Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(SignupActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
