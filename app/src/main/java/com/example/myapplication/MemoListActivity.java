package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        // 데이터를 준비
        List<Map<String, String>> dataList = new ArrayList<>();

        // 100개의 임시 데이터
        for (int i = 0; i < 100; i++) {
            Map<String, String> data = new HashMap<>();
            data.put("제목", "제목 " + i);
            data.put("내용", "어쩌구 저쩌구 " + i);
            dataList.add(data);
        }

        // 어댑터
        SimpleAdapter adapter = new SimpleAdapter(this,
                dataList,
                android.R.layout.simple_list_item_2,
                new String[] {"제목", "내용"},
                new int[] { android.R.id.text1, android.R.id.text2});

        // 리스트뷰
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        // 그리드뷰
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(adapter);

        // 스피너
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_log_out) {
            // login 상태를 false 값으로 바꾸자
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("login", false);
            edit.apply();

            // 로그인 화면으로 이동
            startActivity(new Intent(this, MainActivity.class));

            // 이 화면을 닫자
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
