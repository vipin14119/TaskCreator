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
    public ScreenSlidePageFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle b = getArguments();

        task_title = (TextView) getActivity().findViewById(R.id.pager_task_title);
        task_detail = (TextView) getActivity().findViewById(R.id.pager_task_detail);
        task_title.setText(b.getString("task_title"));
        task_detail.setText(b.getString("task_detail"));
    }
}
