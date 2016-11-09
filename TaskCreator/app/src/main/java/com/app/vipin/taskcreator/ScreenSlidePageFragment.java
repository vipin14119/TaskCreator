package com.app.vipin.taskcreator;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by vipin on 8/11/16.
 */
public class ScreenSlidePageFragment extends Fragment {

    private TextView task_title, task_detail;
    private Bundle passedData;
    public ScreenSlidePageFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        passedData = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        task_title = (TextView) rootView.findViewById(R.id.pager_task_title);
        task_detail = (TextView) rootView.findViewById(R.id.pager_task_detail);
        task_title.setText(passedData.getString("task_title"));
        task_detail.setText(passedData.getString("task_detail"));

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle b = getArguments();


    }
}
