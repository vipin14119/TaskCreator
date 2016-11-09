package com.app.vipin.taskcreator;

/**
 * Created by vipin on 8/11/16.
 */
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

public class ViewPagerActivity extends FragmentActivity {
    private static List<Task> mtaskList;
    private ViewPager mPager;
    private int open_index;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        mtaskList = MainActivity.taskList;
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        open_index = getIntent().getExtras().getInt("open_index");
        mPager.setCurrentItem(open_index, true);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }
//        public ScreenSlidePagerAdapter(Activity activity, ViewPager pager){
//            super(activity.getFragmentManager());
//            mContext = activity;
//            mPager = pager;
//
//        }

        @Override
        public Fragment getItem(int position) {
            Task task = mtaskList.get(position);
            TaskDetailPagerFragment screenSlidePageFragment = new TaskDetailPagerFragment();
            Bundle args = new Bundle();
            args.putInt("task_id", task.getId());
            args.putString("task_title", task.getTitle());
            args.putString("task_detail", task.getDetail());
            screenSlidePageFragment.setArguments(args);
            return screenSlidePageFragment;
            /*return new ScreenSlidePageFragment();*/
        }


        @Override
        public int getCount() {
            return mtaskList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position >= 0){
                return "TASK "+ (position + 1);
            }
            return null;
        }
    }
}