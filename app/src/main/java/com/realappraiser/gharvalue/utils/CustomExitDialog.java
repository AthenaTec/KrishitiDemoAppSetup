package com.realappraiser.gharvalue.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.realappraiser.gharvalue.R;

/**
 * Created by kaptas on 23/12/17.
 */

@SuppressWarnings("ALL")
class CustomExitDialog extends Dialog implements android.view.View.OnClickListener {

    private Activity mContext;
    public Dialog d;
    private Button yes;
    private Button no;

    public CustomExitDialog(Activity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.mContext = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_exit_dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                mContext.finish();
                mContext.finishAffinity();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(intent);

                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
