package com.iutlr.puissance4;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DialogDisplayer {
    public static boolean displayAlert(Context context, String message) {
        AlertDialog alert = new AlertDialog.Builder(context)
                .setMessage(context.getString(R.string.alert_content, message))
                .setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(which);
                        dialog.dismiss();
                    }
                })
                .create();
        alert.show();
        return alert.getButton(-1).isPressed();
    }
}
