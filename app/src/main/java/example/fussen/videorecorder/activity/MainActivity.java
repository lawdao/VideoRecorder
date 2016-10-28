package example.fussen.videorecorder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import example.fussen.videorecorder.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button recordVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recordVideo = (Button) findViewById(R.id.btn_record);

        recordVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == recordVideo) {
            startActivity(new Intent(MainActivity.this, RecordActivity.class));
        }
    }
}
