package com.realappraiser.gharvalue.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.realappraiser.gharvalue.R;

/**
 * Created by kaptas on 30/12/17.
 *
 */

@SuppressWarnings("ALL")
public class MultiSelectPopup extends Dialog implements android.view.View.OnClickListener {

    public Activity mContext;
    public Dialog d;
    public Button yes, no;

    public MultiSelectPopup(Activity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.mContext = activity;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.multiselect_popup);
        yes = (Button) findViewById(R.id.okBtn);
        no = (Button) findViewById(R.id.cancelBtn);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.multiselect_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);

      /*  final MultiselectAdapter adapter = new MultiselectAdapter(mContext, Singleton.getInstance().floorUsages_list);
        recyclerView.setAdapter(adapter);*/
        TextView popuptitle = (TextView) findViewById(R.id.title);
        popuptitle.setText("Multi Select Spinner");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:


              /*  mContext.finish();
                mContext.finishAffinity();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(intent);
*/
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
