package com.maverick.findmyfood.appmain;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.maverick.findmyfood.R;

public class AppDialog extends AppCompatDialogFragment
{
    private EditText review;
    AppDialogListner listner;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_layout,null);
        review=(EditText)view.findViewById(R.id.edit_review);
        builder.setView(view).setTitle("Add a Review").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("Post", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("inside post");
                String post=review.getText().toString();
                listner.postReview(post);
            }
        });
        return   builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listner = (AppDialogListner) context;
        }
        catch (Exception e)
        {
            System.out.println("implement interface AppDialogListner");
        }
    }

    public interface AppDialogListner
    {
        void postReview(String post);
    }
}
