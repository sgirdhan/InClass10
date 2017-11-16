/*
    InClass 10
    Salman Mujtaba 800969897
    Sharan Giridhani 800960333
    CreateNewContactActivity
*/

package com.example.sharangirdhani.inclass10;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.sharangirdhani.inclass10.databinding.ActivityCreateNewContactBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNewContactActivity extends AppCompatActivity {

    Profile prf;
    ImageView imgAvatar ;

    public static final int REQ_CODE = 100;
    public static String VALUE_KEY = null;
    private ActivityCreateNewContactBinding binding;

    final static String PROFILE_KEY = "PROFILE";
    EditText txtViewN, txtViewE, txtViewP;
    SeekBar seekB;
    String prog;
    RadioGroup rg;
    RadioButton rb;
    int selected = 0;
    String cat;
    String image;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_new_contact);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_new_contact);
        getSupportActionBar().setTitle(getString(R.string.newContactActivityTitle));

        cat = "SIS";

        rg = (RadioGroup) findViewById(R.id.radioGroupDepartment);
        rb = (RadioButton) rg.findViewById(rg.getCheckedRadioButtonId());

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked) {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    cat = checkedRadioButton.getText().toString();
                }
            }
        });

        avatar = (ImageView) findViewById(R.id.imageView);


        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send to the list
                txtViewN = (EditText) findViewById(R.id.editTextName);
                txtViewE = (EditText) findViewById(R.id.editTextEmail);
                txtViewP = (EditText) findViewById(R.id.editTextPhone);
                String name = txtViewN.getText().toString();
                String email = txtViewE.getText().toString();
                String phone = txtViewP.getText().toString();


                if(!isEmailValid(email)) {
                    Toast.makeText(getApplicationContext(), R.string.emailInvalidError, Toast.LENGTH_SHORT).show();
                }
                else if(name == null || name.equals("") || email == null || email.equals("") || phone == null || phone.equals("") || cat == null || image == null || image.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the inputs", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("demo", image );
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("contacts").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    DatabaseReference newExpenseReference = databaseReference.push();
                    String profileId = newExpenseReference.getKey();
                    Profile profile = new Profile(name, cat, email, phone, image, profileId);
                    newExpenseReference.setValue(profile);

                    Intent intent = new Intent(CreateNewContactActivity.this, ContactListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void avatarClick(View view) {
        Intent intent = new Intent(CreateNewContactActivity.this, SelectAvatarActivity.class);
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                String returnedVal = data.getExtras().getString(VALUE_KEY);
                switch (VALUE_KEY) {
                    case "f1":
                        image = "avatar_f_1";
                        binding.imageView.setImageResource(R.drawable.avatar_f_1);
                        break;
                    case "f2":
                        image = "avatar_f_2";
                        binding.imageView.setImageResource(R.drawable.avatar_f_2);

                        break;
                    case "f3":
                        image = "avatar_f_3";
                        binding.imageView.setImageResource(R.drawable.avatar_f_3);

                        break;
                    case "m1":
                        image = "avatar_m_1";
                        binding.imageView.setImageResource(R.drawable.avatar_m_1);

                        break;
                    case "m2":
                        image = "avatar_m_2";
                        binding.imageView.setImageResource(R.drawable.avatar_m_2);

                        break;
                    case "m3":
                        image = "avatar_m_3";
                        binding.imageView.setImageResource(R.drawable.avatar_m_3);
                        break;


                }
            }
        }
    }
}
