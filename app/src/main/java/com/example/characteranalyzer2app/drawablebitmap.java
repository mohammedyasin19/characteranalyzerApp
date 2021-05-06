package com.example.characteranalyzer2app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class drawablebitmap extends AppCompatActivity {


    private static final String Tag = "BITMAP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawablebitmap);
        Bitmap bitmap = drawableToBitmap(getDrawable(R.drawable.white));
        storeImage(bitmap);
    }

    private String storeImage(Bitmap bitmapImage) {
        File directory = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File myPath = new File(directory, System.currentTimeMillis() + "__.jpeg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Log.d(Tag, "Save successfull");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(Tag, "Error while saving file 1");

        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(Tag, "Error while saving file 2");

            }
        }
        return myPath.getAbsolutePath();
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }




}
