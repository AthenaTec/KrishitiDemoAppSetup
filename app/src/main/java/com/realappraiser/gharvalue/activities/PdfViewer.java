package com.realappraiser.gharvalue.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by kaptas on 23/12/17.
 */

@SuppressWarnings("ALL")
public class PdfViewer extends AppCompatActivity  implements OnPageChangeListener, OnLoadCompleteListener,
        OnPageErrorListener {

    private static final String TAG = General.class.getSimpleName();
    public String SAMPLE_FILE = "sample.pdf";
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";

    public PDFView pdfView;
    public Integer pageNumber = 0;
    public String pdfFileName, stringToConvert;
    private String uriSting;
    private TextView document_header, document_title;
    private General general;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdfreader);

        initView();
    }

    private void initView() {
        // stringConvert();
        general = new General(PdfViewer.this);
        document_header = (TextView) findViewById(R.id.document_header);
        document_title = (TextView) findViewById(R.id.document_title);
        pdfView = (PDFView) findViewById(R.id.pdfView);

        document_header.setTypeface(general.mediumtypeface());
        document_title.setTypeface(general.mediumtypeface());

        String caseID = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID,"");
        document_header.setText(getResources().getString(R.string.document_view)+" "+caseID);
        document_title.setText(Singleton.getInstance().document_tittle);

       /* getFileFromBase64AndSaveInSDCard(stringToConvert,
                "sampleb.pdf", "pdf", pdfView);*/

        getFileFromBase64AndSaveInSDCard(Singleton.getInstance().documentbase64, Singleton.getInstance().document_name, "pdf", pdfView);

    }

    /**********
     * PDF Conversion Functiionality from base64 string to pdf file and view it
     * **********/
    public GetFilePathAndStatus getFileFromBase64AndSaveInSDCard(String base64, String filename, String extension, PDFView pdfview) {
        GetFilePathAndStatus getFilePathAndStatus = new GetFilePathAndStatus();
        pdfView = pdfview;
        try {
            byte[] pdfAsBytes = Base64.decode(base64, 0);
            FileOutputStream os;
            os = new FileOutputStream(getReportPath(filename, extension), false);
            os.write(pdfAsBytes);
            os.flush();
            os.close();

            File file = new File(uriSting);
            // OpenPdfFile(file);
            SAMPLE_FILE = file.getName();
            displayFromFile(file);

            getFilePathAndStatus.filStatus = true;
            getFilePathAndStatus.filePath = getReportPath(filename, extension);
            return getFilePathAndStatus;
        } catch (IOException e) {
            e.printStackTrace();
            getFilePathAndStatus.filStatus = false;
            getFilePathAndStatus.filePath = getReportPath(filename, extension);
            return getFilePathAndStatus;
        }
    }

    private void OpenPdfFile(File file) {
        Uri path = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(path, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    private void displayFromFile(File FilePath) {
        pdfFileName = SAMPLE_FILE;

        pdfView.fromFile(FilePath)
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    public String getReportPath(String filename, String extension) {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "RealAppraiser/Document");
        if (!file.exists()) {
            file.mkdirs();
        }
        //    uriSting = (file.getAbsolutePath() + "/" + filename + "." + extension);
        uriSting = (file.getAbsolutePath() + "/" + filename);
        return uriSting;

    }

    public static class GetFilePathAndStatus {
        public boolean filStatus;
        public String filePath;
    }

    /*************
     * PDF viewer library functions
     * ************/
    @Override
    public void onPageChanged(int page,int pageCount){
        pageNumber=page;
        setTitle(String.format("%s %s / %s",pdfFileName,page+1,pageCount));
    }

    @Override
    public void loadComplete(int nbPages){
        PdfDocument.Meta meta=pdfView.getDocumentMeta();
        Log.e(TAG,"title = "+meta.getTitle());
        Log.e(TAG,"author = "+meta.getAuthor());
        Log.e(TAG,"subject = "+meta.getSubject());
        Log.e(TAG,"keywords = "+meta.getKeywords());
        Log.e(TAG,"creator = "+meta.getCreator());
        Log.e(TAG,"producer = "+meta.getProducer());
        Log.e(TAG,"creationDate = "+meta.getCreationDate());
        Log.e(TAG,"modDate = "+meta.getModDate());

        printBookmarksTree(pdfView.getTableOfContents(),"-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep){
        for(PdfDocument.Bookmark b:tree){

            Log.e(TAG,String.format("%s %s, p %d",sep,b.getTitle(),b.getPageIdx()));

            if(b.hasChildren()){
                printBookmarksTree(b.getChildren(),sep+"-");
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onPageError(int page,Throwable t){
        Log.e(TAG,"Cannot load page "+page);
    }

}
