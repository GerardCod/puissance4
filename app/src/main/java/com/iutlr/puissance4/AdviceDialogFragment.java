package com.iutlr.puissance4;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class AdviceDialogFragment extends DialogFragment {
    public interface AdviceDialogListener {
        void onPositiveButtonClick(DialogFragment dialog);
        void onNegativeButtonClick(DialogFragment dialog);
    }

    AdviceDialogListener listener;
    private String message;
    private String type;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (AdviceDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement AdviceDialogListener.");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setTitle(type);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.bouton_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onPositiveButtonClick(AdviceDialogFragment.this);
            }
        });

        builder.setNegativeButton(R.string.bouton_non, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onNegativeButtonClick(AdviceDialogFragment.this);
            }
        });

        return builder.create();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }
}
