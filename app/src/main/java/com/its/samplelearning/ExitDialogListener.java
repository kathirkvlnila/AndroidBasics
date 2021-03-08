package com.its.samplelearning;

import android.content.DialogInterface;

public interface ExitDialogListener {

    void onCancelClicked(DialogInterface dialog);

    void onProceedClicked();
}
