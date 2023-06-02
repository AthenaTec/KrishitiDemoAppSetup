package com.realappraiser.gharvalue.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.iceteck.silicompressorr.SiliCompressor;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.adapter.ImageAdapter;
import com.realappraiser.gharvalue.fragments.PhotoLatLong;
import com.realappraiser.gharvalue.fragments.PhotoLatLong_ka;
import com.realappraiser.gharvalue.model.GetPhoto;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.ItemOffsetDecoration;
import com.realappraiser.gharvalue.utils.SettingsUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * @author Paresh Mayani (@pareshmayani)
 */
@SuppressWarnings("ALL")
public class MultiPhotoSelectActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static ImageAdapter imageAdapter;
    private static final int REQUEST_FOR_STORAGE_PERMISSION = 123;
    private General general;
    private String mBase64 = "";
    private ArrayList<String> selectedItems;
    boolean is_fragment_flat_ka = false;
    boolean real_appraiser_jaipur = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_photo_select);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String page_is = bundle.getString("page_is");
            is_fragment_flat_ka = true;
            Snackbar.make(findViewById(R.id.button1), getResources().getString(R.string.one_photo), Snackbar.LENGTH_SHORT).show();
        } else {
            is_fragment_flat_ka = false;
        }

        real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);

        general = new General(MultiPhotoSelectActivity.this);
        populateImagesFromGallery();
    }

    public void btnChoosePhotosClick(final View v) {

//        general.showloading(MultiPhotoSelectActivity.this);
        v.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> selectedItems = imageAdapter.getCheckedItems();
                if (selectedItems != null && selectedItems.size() > 0) {

                    if (is_fragment_flat_ka) {
                        int new_addimage = selectedItems.size();
                        if (new_addimage == 1) {
                            v.setVisibility(View.VISIBLE);
                            Intent intent = new Intent();
                            intent.putStringArrayListExtra("multiple_photo", selectedItems);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            general.hideloading();
                            v.setVisibility(View.VISIBLE);
                            general.CustomToast(getResources().getString(R.string.one_photo));
                        }
                    } else {
                        String image = selectedItems.get(0);
                        int old_imgresult = 0;
                        if(real_appraiser_jaipur) {
                            // Jaipur
                            old_imgresult = PhotoLatLong.GetPhoto_list_response.size();
                        }else {
                            // Kakode
                            old_imgresult = PhotoLatLong_ka.GetPhoto_list_response.size();
                        }
                        int new_addimage = selectedItems.size();
                        int finalImagecount = old_imgresult + new_addimage;

                        if (finalImagecount <= 22) {
                            v.setVisibility(View.VISIBLE);
                            Intent intent = new Intent();
                            intent.putStringArrayListExtra("multiple_photo", selectedItems);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            general.hideloading();
                            v.setVisibility(View.VISIBLE);
                            general.CustomToast(getResources().getString(R.string.sizelimit));
                        }
                    }

                } else {
                    general.hideloading();
                    v.setVisibility(View.VISIBLE);
                    general.CustomToast(getResources().getString(R.string.selectmsg));
                }
            }
        }, 1000);
    }

    private void populateImagesFromGallery() {
        if (!mayRequestGalleryImages()) {
            return;
        }

        ArrayList<String> imageUrls = loadPhotosFromNativeGallery();
        initializeRecyclerView(imageUrls);
    }

    private boolean mayRequestGalleryImages() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        if (checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
            //promptStoragePermission();
            showPermissionRationaleSnackBar();
        } else {
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE}, REQUEST_FOR_STORAGE_PERMISSION);
        }

        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        switch (requestCode) {

            case REQUEST_FOR_STORAGE_PERMISSION: {

                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        populateImagesFromGallery();
                    } else {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
                            showPermissionRationaleSnackBar();
                        } else {
                            Toast.makeText(this, "Go to settings and enable permission", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                break;
            }
        }
    }

    private ArrayList<String> loadPhotosFromNativeGallery() {
        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        //noinspection deprecation
        @SuppressWarnings("deprecation") Cursor imagecursor = managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy + " DESC");

        ArrayList<String> imageUrls = new ArrayList<String>();

        for (int i = 0; i < imagecursor.getCount(); i++) {
            imagecursor.moveToPosition(i);
            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
            imageUrls.add(imagecursor.getString(dataColumnIndex));

            System.out.println("=====> Array path => " + imageUrls.get(i));
        }
        return imageUrls;
    }

    private void initializeRecyclerView(ArrayList<String> imageUrls) {
        imageAdapter = new ImageAdapter(this, imageUrls);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 4);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ItemOffsetDecoration(this, R.dimen.item_offset));
        recyclerView.setAdapter(imageAdapter);
    }

    private void showPermissionRationaleSnackBar() {
        Snackbar.make(findViewById(R.id.button1), getString(R.string.permission_rationale),
                Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Request the permission
                ActivityCompat.requestPermissions(MultiPhotoSelectActivity.this,
                        new String[]{READ_EXTERNAL_STORAGE},
                        REQUEST_FOR_STORAGE_PERMISSION);
            }
        }).show();

    }


    /*****
     * Load the image from selected arraylist
     * *******/
    private void GetConvertedImage() {
        general.showloading(MultiPhotoSelectActivity.this);
        for (int i = 0; i < imageAdapter.getCheckedItems().size(); i++) {
            //String realPath = imageAdapter.getCheckedItems().get(i);
            String realPath = null;
            realPath = SiliCompressor.with(MultiPhotoSelectActivity.this).compress(Integer.parseInt(imageAdapter.getCheckedItems().get(i)));
            convertToBase64(realPath);

            if (i == imageAdapter.getCheckedItems().size() - 1) {
                general.hideloading();
                general.CustomToast(",,Total photos Loaded: " + selectedItems.size());

            }
        }

        int old_imgresult = PhotoLatLong.GetPhoto_list_response.size();
        int new_addimage = selectedItems.size();
        int finalImagecount = old_imgresult + new_addimage;
        if (old_imgresult <= 22) {
            Intent intent = new Intent();
            intent.putStringArrayListExtra("multiple_photo", selectedItems);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            general.CustomToast(getResources().getString(R.string.sizelimit));
        }
           /* }
            else {
                general.CustomToast(getResources().getString(R.string.imagelimit));
            }*/
    }

    /*******
     * Convert the image and add it to main photo adapter
     * ******/
    private String convertToBase64(String imagePath) {
        Bitmap bmp = null;
        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        String encodedImage = null;
        try {
            bmp = BitmapFactory.decodeFile(imagePath);
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bt = bos.toByteArray();
            encodedImage = Base64.encodeToString(bt, Base64.DEFAULT);
            Log.d("base64image", encodedImage);

            mBase64 = encodedImage;
            if (mBase64 != null) {
                GetPhoto getPhoto_new_image = new GetPhoto();
                getPhoto_new_image.setNewimage(true);
                getPhoto_new_image.setLogo(mBase64);
                getPhoto_new_image.setId(0);
                // TODO - we want to set property type in session and set it here

                String PropertyId = SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, "");
                if (!general.isEmpty(PropertyId)) {
                    getPhoto_new_image.setPropertyId(Integer.valueOf(PropertyId));
                } else {
                    getPhoto_new_image.setPropertyId(0);
                }

                PhotoLatLong.GetPhoto_list_response.add(getPhoto_new_image);
                // recycler_photo_adapter.setphoto_adapter(GetPhoto_list_response);

            }


        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return encodedImage;
    }
}