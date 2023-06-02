package com.realappraiser.gharvalue.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.realappraiser.gharvalue.R;

import java.util.ArrayList;

/**
 * @author Paresh Mayani (@pareshmayani)
 */
@SuppressWarnings("ALL")
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private ArrayList<String> mImagesList;
    private Context mContext;
    private SparseBooleanArray mSparseBooleanArray;

    public ImageAdapter(Context context, ArrayList<String> imageList) {
        mContext = context;
        mSparseBooleanArray = new SparseBooleanArray();
        mImagesList = new ArrayList<String>();
        this.mImagesList = imageList;

    }

    public ArrayList<String> getCheckedItems() {
        ArrayList<String> mTempArry = new ArrayList<String>();

        for(int i=0;i<mImagesList.size();i++) {
            if(mSparseBooleanArray.get(i)) {
                mTempArry.add(mImagesList.get(i));
            }
        }

        return mTempArry;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
        }
    };

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_multiphoto_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String imageUrl = mImagesList.get(position);

        Glide.with(mContext)
                .load("file://"+imageUrl)
                .centerCrop()
                .placeholder(R.drawable.holder)
                .error(R.drawable.holder)
                .into(holder.imageView);

        holder.checkBox.setTag(position);
        holder.checkBox.setChecked(mSparseBooleanArray.get(position));
        holder.checkBox.setOnCheckedChangeListener(mCheckedChangeListener);
    }

    @Override
    public int getItemCount() {
        return mImagesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CheckBox checkBox;
        public ImageView imageView;
        public RelativeLayout parentImageLay;

        public MyViewHolder(View view) {
            super(view);

            checkBox = (CheckBox) view.findViewById(R.id.checkBox1);
            imageView = (ImageView) view.findViewById(R.id.imageView1);
            parentImageLay = (RelativeLayout) view.findViewById(R.id.parentImageLay);
        }
    }

}
