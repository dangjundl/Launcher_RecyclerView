package com.peng.launcherrecyclerviewhorizontal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RadioGroup radioGroup;
    int nums = 4;

    PackageManager myPackageManager;
    Button linebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("지금 넘버스 값뭐임?", "onClick: "+nums);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 화면을 landscape(가로) 화면으로 고정하고 싶은 경우

        linebutton = (Button) findViewById(R.id.line_edit);
        radioGroup = findViewById(R.id.radio_Group);

        myPackageManager = getPackageManager();

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, nums,LinearLayoutManager.HORIZONTAL,false));

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> intentList = myPackageManager.queryIntentActivities(intent, 0);
        MyAdapter myAdapter = new MyAdapter(intentList,this);
        mRecyclerView.addItemDecoration(new MovieItemDecoration(this));
        mRecyclerView.setAdapter(myAdapter);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                switch (i){
                    case R.id.line1:
                            nums = 1;
                            break;

                    case R.id.line2:
                        nums = 2;
                        break;
                    case R.id.line3:
                        nums = 3;
                        break;
                    case R.id.line4:
                        nums = 4;
                        break;
                    case R.id.line5:
                        nums = 5;
                        break;
                }

            }
        });



        linebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("dsadasd", "onClick: "+nums);
               mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
                mRecyclerView.setHasFixedSize(false);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), nums,LinearLayoutManager.HORIZONTAL,false));
           mRecyclerView.addItemDecoration(new MovieItemDecoration(getApplicationContext()));

            }
        });



        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

              //  Toast.makeText(getApplicationContext(),"됩니까?", Toast.LENGTH_SHORT).show();
                ResolveInfo cleckedResolveInfo =
                        intentList.get(pos);
                ActivityInfo clickedActivityInfo =
                        cleckedResolveInfo.activityInfo;

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setClassName(
                        clickedActivityInfo.applicationInfo.packageName,
                        clickedActivityInfo.name);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                startActivity(intent);

            }
        });

    }


}