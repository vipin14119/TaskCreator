package com.app.vipin.taskcreator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;



public class AddTaskFragment extends DialogFragment {

    private DialogInterface.OnDismissListener onDismissListener;
    private DBHandler db;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener){
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface){
        super.onDismiss(dialogInterface);
        if (onDismissListener != null){
            onDismissListener.onDismiss(dialogInterface);
        }
    }

    public AddTaskFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add A New Task");

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_add_task, null));

        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addTask();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AddTaskFragment.this.getDialog().cancel();
            }
        });
        return builder.create();
    }

    public void addTask(){
        EditText title_input = (EditText)getDialog().findViewById(R.id.ET_title);
        EditText detail_input = (EditText)getDialog().findViewById(R.id.ET_detail);

        String title = title_input.getText().toString();
        String detail = detail_input.getText().toString();

        if (!title.equals("") && !detail.equals("")){
            db = new DBHandler(getActivity());
            Task task = new Task(title, detail, 0);
            db.addTask(task);
            MainActivity.taskList.add(task);
            MainActivity.mAdapter.notifyDataSetChanged();
            Toast.makeText(getActivity().getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
        }
    }


}
