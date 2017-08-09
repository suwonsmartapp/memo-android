package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mIdEditText;
    private EditText mPasswordEditText;


    // 처음에 실행되는 부분
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIdEditText = (EditText) findViewById(R.id.idEdit);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEdit);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLogin = preferences.getBoolean("login", false);

        if (isLogin == true) {
            startActivity(new Intent(this, MemoListActivity.class));

            // 현재 화면 닫기
            finish();
        }
    }

    public void onLoginButtonClicked(View view) {
        if (mIdEditText.getText().toString().equals("admin") &&
                mPasswordEditText.getText().toString().equals("1111")) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("login", true);
            edit.commit();

            // 메인 화면으로 이동
            startActivity(new Intent(this, MemoListActivity.class));
        } else {
            Toast.makeText(this, "비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show();
        }
    }
}
