package com.quxiangtech.customViews.dashboardview.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.quxiangtech.myapplication.R;


public class VerifyCodeDialog extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "VerifyCodeDialog";
    private View mErrorView;
    private VerifyCodeView.OnInputCompleteListener mListener;

    public void setOnInputCompleteListener(VerifyCodeView.OnInputCompleteListener listener) {
        mListener = listener;
    }

    public static VerifyCodeDialog newInstance() {

        Bundle args = new Bundle();

        VerifyCodeDialog fragment = new VerifyCodeDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View rootView = getLayoutInflater().inflate(R.layout.dialog_verify_code, null);
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);

        rootView.findViewById(R.id.close).setOnClickListener(this);
        mErrorView = rootView.findViewById(R.id.error);
        mErrorView.setVisibility(View.INVISIBLE);
        VerifyCodeView verifyCodeView = rootView.findViewById(R.id.verify_code);
        verifyCodeView.setInputCompleteListener(new VerifyCodeView.OnInputCompleteListener() {
            @Override
            public void onInput(String s) {
                if (mListener != null) {
                    mListener.onInput(s);
                }
            }
        });

        return alertDialog;
    }

    public void show(@NonNull FragmentManager manager) {
        show(manager, "verify_code_dialog");
    }

    public void showError() {
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.close) {
            dismiss();
        }
    }
}
