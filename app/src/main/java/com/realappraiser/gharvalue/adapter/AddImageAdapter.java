package com.realappraiser.gharvalue.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.communicator.DataResponse;
import com.realappraiser.gharvalue.communicator.JsonRequestData;
import com.realappraiser.gharvalue.communicator.RequestParam;
import com.realappraiser.gharvalue.communicator.ResponseParser;
import com.realappraiser.gharvalue.communicator.TaskCompleteListener;
import com.realappraiser.gharvalue.communicator.WebserviceCommunicator;
import com.realappraiser.gharvalue.model.ImageBase64;
import com.realappraiser.gharvalue.model.NewImage;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by kaptas on 20/12/17.
 */

@SuppressWarnings("ALL")
public class AddImageAdapter extends RecyclerView.Adapter<AddImageAdapter.ViewHolder> {
    private final FragmentActivity mContext;
    private final ArrayList<ImageBase64> item;
    private ArrayList<ImageBase64> image;
    private int IMAGEID = 0;
    private String mImageID = "";
    ArrayList<NewImage> newimage = new ArrayList<>();
    private final General general;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageitem;
        private final LinearLayout imagedefaultlayout;
        private final LinearLayout txtvalue;
        private final LinearLayout close;
        private final EditText edit;

        public ViewHolder(View itemView) {
            super(itemView);
            imageitem = (ImageView) itemView.findViewById(R.id.imageitem);
            imagedefaultlayout = (LinearLayout) itemView.findViewById(R.id.imagedefaultlayout);
            txtvalue = (LinearLayout) itemView.findViewById(R.id.txtvalue);
            edit = (EditText) itemView.findViewById(R.id.text);
            close = (LinearLayout) itemView.findViewById(R.id.close);

            final NewImage newImage = new NewImage();

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    try {
                        IMAGEID = position;
                        mImageID = Singleton.getInstance().imageID.get(position);
                        IMAGEID = Integer.parseInt(mImageID);
                        item.remove(position);
                        notifyItemRemoved(position);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                /*    JSONObject list = new JSONObject();
                    try {
                        list.put("Id", IMAGEID);
                        list.put("PropertyId", "2550");

                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    Log.i("Main", list.toString());*/

                    JsonRequestData data = new JsonRequestData();
                    data.setJobID(String.valueOf(IMAGEID));
                    data.setPropertyID(Singleton.getInstance().propertyId);//2561
                    data.setUrl(general.ApiBaseUrl() + SettingsUtils.DELETEIMAGE);
                  //  data.setUrl(SettingsUtils.DELETEIMAGE);

                    data.setRequestBody(RequestParam.deleteimageRequestParams(data));

                    WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, data, SettingsUtils.DELETE);
                    webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                        @Override
                        public void onTaskComplete(JsonRequestData requestData) {
                            parsedeleteimageResponse(requestData.getResponse());
                        }
                    });
                    webserviceTask.execute();
                }
            });
            edit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                   /* NewImage newImage = new NewImage();
                    int b = Singleton.getInstance().newimage.size();
                    for (int j = 1; j <= b + 1; j++) {
                        Log.d("ID", String.valueOf(j));
                        newImage.setEditTextValue(String.valueOf(j));
                    }
                    Singleton.getInstance().newimage.add(newImage);
                    newimage.add(newImage);*/
//                    newImage.setEditTextValue(charSequence.toString());
//                    newimage.set(getAdapterPosition(), newImage);
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    int position = getAdapterPosition();
                   /* if (position >= 1) {
                        String a1 = edit.getText().toString();
                        Log.d("TXT", a1);
                        Singleton.getInstance().imagetitle.add(edit.getText().toString().trim());
                        String a = String.valueOf(Singleton.getInstance().imagetitle);
                        base64.setEditTextValue(a);
                    } else {

                    }*/
                }
            });
        }
    }

    private void parsedeleteimageResponse(String response) {
        DataResponse dataResponse = ResponseParser.deleteimage(response);
        if (dataResponse.status.equalsIgnoreCase("1")) {
            Toast.makeText(mContext, dataResponse.info, Toast.LENGTH_LONG).show();
        }
    }

    public AddImageAdapter(FragmentActivity activity, ArrayList<ImageBase64> image) {
        this.mContext = activity;
        this.item = image;
        general = new General(mContext);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public AddImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_imageadapter, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final AddImageAdapter.ViewHolder holder, final int position) {

        if (position == 0) {
            holder.imagedefaultlayout.setVisibility(View.VISIBLE);
            if (item.size() > 0) {
                holder.txtvalue.setVisibility(View.VISIBLE);
            } else {
                holder.txtvalue.setVisibility(View.GONE);
            }
        }

        int x = holder.getLayoutPosition();

        if (item.get(x).getEditTextValue().length() > 0) {
            // if(steps.get(x).length() > 0) {
            holder.edit.setText(item.get(x).getEditTextValue());
        }

        final String a = item.get(position).getImage64();
        byte[] decodedString = Base64.decode(a, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        if (decodedByte != null) {
            getImageUri(mContext, decodedByte);
            Picasso.get().load(getImageUri(mContext, decodedByte))
                    .fit()
                    .into(holder.imageitem);

        } else {

        }


    }


    public ArrayList<ImageBase64> getStepList() {
        return item;
    }

    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}