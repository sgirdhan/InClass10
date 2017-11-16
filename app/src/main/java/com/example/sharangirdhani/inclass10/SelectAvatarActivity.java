/*
    InClass 10
    Salman Mujtaba 800969897
    Sharan Giridhani 800960333
    SelectAvatarActivity
*/
package com.example.sharangirdhani.inclass10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SelectAvatarActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
        getSupportActionBar().setTitle(getString(R.string.selectAvatarActivityTitle));
    }

    public void buttonF1(View view) {
        intent = new Intent();
        int selected = R.drawable.avatar_f_1;
        CreateNewContactActivity.VALUE_KEY = "f1";
        intent.putExtra(CreateNewContactActivity.VALUE_KEY,selected);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void buttonF2(View view) {
        intent = new Intent();
        int selected = R.drawable.avatar_f_2;
        CreateNewContactActivity.VALUE_KEY = "f2";

        intent.putExtra(CreateNewContactActivity.VALUE_KEY,selected);
        setResult(RESULT_OK, intent);
        finish();

    }

    public void buttonF3(View view) {
        intent = new Intent();
        int selected = R.drawable.avatar_f_3;
        intent.putExtra(CreateNewContactActivity.VALUE_KEY,selected);
        CreateNewContactActivity.VALUE_KEY = "f3";

        setResult(RESULT_OK, intent);
        finish();

    }

    public void buttonM1(View view) {
        intent = new Intent();
        int selected = R.drawable.avatar_m_1;
        CreateNewContactActivity.VALUE_KEY = "m1";
        intent.putExtra(CreateNewContactActivity.VALUE_KEY,selected);
        setResult(RESULT_OK, intent);
        finish();

    }
    public void buttonM2(View view) {
        intent = new Intent();
        int selected = R.drawable.avatar_m_2;
        CreateNewContactActivity.VALUE_KEY = "m2";
        intent.putExtra(CreateNewContactActivity.VALUE_KEY,selected);
        setResult(RESULT_OK, intent);
        finish();

    }
    public void buttonM3(View view) {
        intent = new Intent();
        int selected = R.drawable.avatar_m_3;
        CreateNewContactActivity.VALUE_KEY = "m3";
        intent.putExtra(CreateNewContactActivity.VALUE_KEY,selected);
        setResult(RESULT_OK, intent);
        finish();

    }
}
