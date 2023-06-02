package com.realappraiser.gharvalue.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.activities.PdfViewer;
import com.realappraiser.gharvalue.activities.PhotoLatLngTab;
import com.realappraiser.gharvalue.model.Document_list;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by kaptas on 18/1/18.
 */

@SuppressWarnings("ALL")
public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {
    private final Activity mContext;
    public ArrayList<Document_list> mDocument;
    private General general;
    private String tittle = "";
    private String documentname = "";
    private String document = "";

    public DocumentAdapter(Activity mContext, ArrayList<Document_list> documentRead) {
        this.mContext = mContext;
        this.mDocument = documentRead;
        Singleton.getInstance().documentRead = documentRead;

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        general = new General(mContext);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.documentadapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.documenttittle.setText(mDocument.get(position).getDocumentName());

        holder.documentparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDocument = Singleton.getInstance().documentRead;
                if (mDocument.size() > 0) {

                    document = mDocument.get(position).getDocument();
                    // get dummy refernece for avoid the click function of the document
                    if (document.equalsIgnoreCase("dummy")) {
                        Toast.makeText(mContext, mContext.getResources().getString(R.string.file_large), Toast.LENGTH_SHORT).show();
                    } else {
                        if (general.checkPermissions()) {
                            tittle = mDocument.get(position).getTitle();
                            documentname = mDocument.get(position).getDocumentName();
                            String caseID = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

                            if (documentname.contains("jpg") || documentname.contains("jpeg") || documentname.contains("png")|| documentname.contains("PNG")|| documentname.contains("JPEG")|| documentname.contains("JPG")) {
                                // Image
                                DocumentReaderPopup(document, tittle, mContext.getResources().getString(R.string.image_view) + " " + caseID);
                            } else if (documentname.contains("pdf")) {
                                // Pdf
                                /*Singleton.getInstance().documentbase64 = document;
                                Singleton.getInstance().document_name = documentname;
                                Singleton.getInstance().document_tittle = tittle;
                                mContext.startActivity(new Intent(mContext, PdfViewer.class));*/

                                // Excel and other
                                Singleton.getInstance().documentbase64 = document;
                                Singleton.getInstance().document_name = documentname;
                                String tittle = Singleton.getInstance().document_name;
                                String ps2 = Singleton.getInstance().documentbase64;
                                byte[] byteData = Base64.decode(ps2, Base64.DEFAULT);
                                ContextWrapper cw = new ContextWrapper(mContext);
                                File directory;
                                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                                    directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                                }else{
                                    directory = new File(String.valueOf(mContext.getExternalFilesDir("")));
                                }
                                File mypath = new File(directory, tittle);



                                BufferedOutputStream writer = null;
                                try {
                                    writer = new BufferedOutputStream(new FileOutputStream(mypath));
                                    writer.write(byteData);
                                    writer.flush();
                                    writer.close();

                                    File newPath = new File(directory,tittle);

                                    Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                                    Uri path = null;
                                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                                        path = Uri.fromFile(mypath);
                                    }else {
                                        path = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".fileprovider", mypath);
                                    }

                                    if(path!=null) {
                                        //  pdfOpenintent.setDataAndType(newpath, "application/*");
                                        pdfOpenintent.setDataAndType(path, "application/pdf");
                                    }
//                                    pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    pdfOpenintent.setPackage("com.adobe.reader");
                                    pdfOpenintent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                    pdfOpenintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                    try {
                                        mContext.startActivity(pdfOpenintent);
//                                        mContext.startActivity(Intent.createChooser(pdfOpenintent, "choseFile"));

                                    } catch (ActivityNotFoundException e) {

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else if (document.equalsIgnoreCase("")) {
                                general.CustomToast("No Data");
                            } else if (documentname.contains("xlsx") || documentname.contains("xls") || documentname.contains("odt") || documentname.contains("ods")) {
                                // Excel and other
                                Singleton.getInstance().documentbase64 = document;
                                Singleton.getInstance().document_name = documentname;
                                String tittle = Singleton.getInstance().document_name;
                                String ps2 = Singleton.getInstance().documentbase64;
                                byte[] byteData = Base64.decode(ps2, Base64.DEFAULT);
                                ContextWrapper cw = new ContextWrapper(mContext);
                                File directory;
                                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                                     directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                                }else{
                                    directory = new File(String.valueOf(mContext.getExternalFilesDir("")));
                                }
                                File mypath = new File(directory, tittle);

                                Uri newpath = Uri.parse(directory + "/" + tittle);

                                BufferedOutputStream writer = null;
                                try {
                                    writer = new BufferedOutputStream(new FileOutputStream(mypath));
                                    writer.write(byteData);
                                    writer.flush();
                                    writer.close();

                                    Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                                    Uri path=null;
                                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                                        path = Uri.fromFile(mypath);
                                    }else {
                                        path = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".fileprovider", mypath);
                                    }

                                    if(path!=null) {
                                        //  pdfOpenintent.setDataAndType(newpath, "application/*");
                                        pdfOpenintent.setDataAndType(path, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                                    }                                    //  pdfOpenintent.setDataAndType(newpath, "application/*");
                                    pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    pdfOpenintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    pdfOpenintent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                                    try {
                                        mContext.startActivity(pdfOpenintent);
                                    } catch (ActivityNotFoundException e) {

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }else if (documentname.contains("docx") ) {
                                // Excel and other
                                Singleton.getInstance().documentbase64 = document;
                                Singleton.getInstance().document_name = documentname;
                                String tittle = Singleton.getInstance().document_name;
                                String ps2 = Singleton.getInstance().documentbase64;
                                byte[] byteData = Base64.decode(ps2, Base64.DEFAULT);
                                ContextWrapper cw = new ContextWrapper(mContext);
                                File directory;
                                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                                     directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                                }else{
                                    directory = new File(String.valueOf(mContext.getExternalFilesDir("")));
                                }
                                File mypath = new File(directory, tittle);

                                Uri newpath = Uri.parse(directory + "/" + tittle);

                                BufferedOutputStream writer = null;
                                try {
                                    writer = new BufferedOutputStream(new FileOutputStream(mypath));
                                    writer.write(byteData);
                                    writer.flush();
                                    writer.close();

                                    Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                                    Uri path=null;
                                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                                        path = Uri.fromFile(mypath);
                                    }else {
                                        path = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".fileprovider", mypath);
                                    }

                                    if (path!=null) {
                                        //  pdfOpenintent.setDataAndType(newpath, "application/*");
                                        pdfOpenintent.setDataAndType(path, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                                    }
                                    pdfOpenintent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                    pdfOpenintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                    try {
                                        mContext.startActivity(pdfOpenintent);
                                    } catch (ActivityNotFoundException e) {
                                        callAlert();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }else if ( documentname.contains("doc") ) {
                                // Excel and other
                                Singleton.getInstance().documentbase64 = document;
                                Singleton.getInstance().document_name = documentname;
                                String tittle = Singleton.getInstance().document_name;
                                String ps2 = Singleton.getInstance().documentbase64;
                                byte[] byteData = Base64.decode(ps2, Base64.DEFAULT);
                                ContextWrapper cw = new ContextWrapper(mContext);
                                File directory;
                                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                                    directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                                }else{
                                    directory = new File(String.valueOf(mContext.getExternalFilesDir("")));
                                }
                                File mypath = new File(directory, tittle);

                                Uri newpath = Uri.parse(directory + "/" + tittle);

                                BufferedOutputStream writer = null;
                                try {
                                    writer = new BufferedOutputStream(new FileOutputStream(mypath));
                                    writer.write(byteData);
                                    writer.flush();
                                    writer.close();

                                    Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                                    Uri path=null;
                                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                                        path = Uri.fromFile(mypath);
                                    }else {
                                        path = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".fileprovider", mypath);
                                    }

                                    if (path!=null) {
                                        //  pdfOpenintent.setDataAndType(newpath, "application/*");
                                        pdfOpenintent.setDataAndType(path, "application/msword");
                                    }
                                    pdfOpenintent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                    pdfOpenintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                    try {
                                        mContext.startActivity(pdfOpenintent);
                                    } catch (ActivityNotFoundException e) {
                                        callAlert();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }


                }

            }
        });
    }


    private void callAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AlertDialogCustom));
        builder.setMessage(mContext.getResources().getString(R.string.document_alert))
                .setCancelable(false)
                .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void DocumentReaderPopup(final String documentimage, String title, String msg) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.document_reader);

        TextView popuptitle = (TextView) dialog.findViewById(R.id.title);
        TextView titletext = (TextView) dialog.findViewById(R.id.titletext);
        ImageView document_image = (ImageView) dialog.findViewById(R.id.document_read_image);
        Button yesBtn = (Button) dialog.findViewById(R.id.yesBtn);
        Button noBtn = (Button) dialog.findViewById(R.id.noBtn);
        yesBtn.setTypeface(general.mediumtypeface());
        noBtn.setTypeface(general.mediumtypeface());
        popuptitle.setTypeface(general.mediumtypeface());
        titletext.setTypeface(general.mediumtypeface());
        popuptitle.setText(msg);
        titletext.setText(title);

        //decode base64 string to image
        byte[] imageBytes = Base64.decode(documentimage, Base64.DEFAULT);
        final Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        document_image.setImageBitmap(decodedImage);

        PhotoViewAttacher pAttacher;
        pAttacher = new PhotoViewAttacher(document_image);
        pAttacher.update();


        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Singleton.getInstance().documentbase64 = document;
                Singleton.getInstance().document_name = documentname;*/
                dialog.dismiss();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public int getItemCount() {
        return mDocument.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView documenttittle;
        private LinearLayout documentparent;

        public ViewHolder(View itemView) {
            super(itemView);
            documenttittle = (TextView) itemView.findViewById(R.id.documenttittle);
            documentparent = (LinearLayout) itemView.findViewById(R.id.documentparent);
            documenttittle.setTypeface(general.regulartypeface());
        }
    }


}
