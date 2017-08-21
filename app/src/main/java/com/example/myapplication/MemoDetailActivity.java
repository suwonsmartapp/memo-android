package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MemoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);

        if (getIntent() != null) {
            String title = getIntent().getStringExtra("제목");
            String description = getIntent().getStringExtra("내용");

            ((EditText)findViewById(R.id.title_edit)).setText(title);
            ((EditText)findViewById(R.id.desc_edit)).setText(description);
        }

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = ((EditText)findViewById(R.id.title_edit)).getText().toString();
                String description = ((EditText)findViewById(R.id.desc_edit)).getText().toString();

                Intent intent = new Intent();
                intent.putExtra("제목", title);
                intent.putExtra("내용", description);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }




}
