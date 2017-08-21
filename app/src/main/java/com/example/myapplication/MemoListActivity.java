package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoListActivity extends AppCompatActivity {

    // 전체 사물함
    private List<Map<String, String>> mDataList;
    private SimpleAdapter mAdapter;
    private int mPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        // 데이터를 준비
        mDataList = new ArrayList<>();

//        // 100개의 임시 데이터
//        for (int i = 0; i < 100; i++) {
//            Map<String, String> data = new HashMap<>();
//            data.put("제목", "제목 " + i);
//            data.put("내용", "어쩌구 저쩌구 " + i);
//            mDataList.add(data);
//        }

        // 어댑터
        mAdapter = new SimpleAdapter(this,
                mDataList,
                android.R.layout.simple_list_item_2,
                new String[] {"제목", "내용"},
                new int[] { android.R.id.text1, android.R.id.text2});

        // 리스트뷰
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);

        // 리스트 클릭
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> data = mDataList.get(position);

                Intent intent = new Intent(MemoListActivity.this, MemoDetailActivity.class);
                intent.putExtra("제목", data.get("제목"));
                intent.putExtra("내용", data.get("내용"));

                mPosition = position;

                startActivityForResult(intent, 2000);
            }
        });

        // +버튼
        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MemoListActivity.this, MemoDetailActivity.class),
                        1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1000 && resultCode == RESULT_OK && intent != null) {
            String title = intent.getStringExtra("제목");
            String description = intent.getStringExtra("내용");

            Map<String, String> data = new HashMap<>();
            data.put("제목", title);
            data.put("내용", description);
            mDataList.add(data);

            mAdapter.notifyDataSetChanged();
        } else if (requestCode == 2000) {
            String title = intent.getStringExtra("제목");
            String description = intent.getStringExtra("내용");

            Map<String, String> data = new HashMap<>();
            data.put("제목", title);
            data.put("내용", description);

            mDataList.set(mPosition, data);

            mAdapter.notifyDataSetChanged();
        }
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
