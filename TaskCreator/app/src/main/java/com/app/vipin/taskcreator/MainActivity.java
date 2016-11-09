package com.app.vipin.taskcreator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<Task> taskList = new ArrayList<>();
    private RecyclerView recyclerView;
    public static TaskAdapter mAdapter;
    private String MSG = "TASK CREATOR: ++++++++++ ";
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        prepareTaskData();
        mAdapter = new TaskAdapter(taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(final MenuItem item){
        int id = item.getItemId();
        if (id == R.id.add_task){
            AddTaskFragment addTaskFragment = new AddTaskFragment();
            addTaskFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    Log.d(MSG, "I am returned");
//                    Intent intent = getIntent();
//                    finish();
//                    startActivity(intent);

                }
            });
            addTaskFragment.show(getSupportFragmentManager(), "AddTaskFragment");
//            Log.d(MSG, "I am returned");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareTaskData() {
        dbHandler = new DBHandler(this);
        taskList =  dbHandler.getTasks();
    }
    public void makePager(View view){
        int open_index = recyclerView.getChildAdapterPosition(view);
        /*int open_index = mAdapter.item_position;*/
        Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
        intent.putExtra("open_index", open_index);
        startActivity(intent);
    }
}