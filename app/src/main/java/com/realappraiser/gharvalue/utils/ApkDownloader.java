package com.realappraiser.gharvalue.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApkDownloader {
    private static final String TAG = "ApkDownloader";

    public ApkDownloader() {
    }

    public void Download(Context context, String fileUrl, String fileName){
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            File dir = new File (Environment.getExternalStorageDirectory()+"/RealAppraiser");

            if (!dir.exists())
                dir.mkdir();

            if (dir.isDirectory())
            {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++)
                {
                    new File(dir, children[i]).delete();
                }
            }

            File apkFile = new File(dir, fileName);

            FileOutputStream fileOutput = new FileOutputStream(apkFile);
            InputStream inputStream = urlConnection.getInputStream();

            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                fileOutput.write(buffer, 0, bufferLength);
            }
            fileOutput.close();

            Log.e(TAG, "Download: "+apkFile.getAbsolutePath() );

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

