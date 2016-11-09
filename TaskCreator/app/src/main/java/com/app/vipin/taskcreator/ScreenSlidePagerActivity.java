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

public class ScreenSlidePagerActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static List<Task> mtaskList;
    private static final int NUM_PAGES = 5;
    private Context mContext;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private int open_index;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
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
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
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
            ScreenSlidePageFragment screenSlidePageFragment = new ScreenSlidePageFragment();
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
                return "TAB "+ position;
            }
            return null;
        }
    }
}