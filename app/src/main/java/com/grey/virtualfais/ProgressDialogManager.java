package com.grey.virtualfais;

import android.content.Context;
import android.support.v7.app.AlertDialog;


public class ProgressDialogManager {

    private static final ProgressDialogManager INSTANCE = new ProgressDialogManager();

    public static ProgressDialogManager get() {
        return INSTANCE;
    }

    private AlertDialog dialog;

    public void show(Context context) {
        dismiss();

        if (context == null) return;

        dialog = new AlertDialog.Builder(context, R.style.ProgressDialogTheme)
                .setView(R.layout.dialog_progress)
                .setCancelable(false).show();
    }

    public void dismiss() {
        if (dialog != null) dialog.dismiss();

        dialog = null;
    }
}
