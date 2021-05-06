package com.example.characteranalyzer2app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity
{
    private Button captureImagebtn,detectTextbtn;
    private ImageView imageView;
    private TextView textview;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startActivity(new Intent(MainActivity.this,DrawActivity.class));
        captureImagebtn= findViewById(R.id.capture_imagebtn);
        detectTextbtn= findViewById(R.id.detect_text_image_btn);
        imageView = findViewById(R.id.image_View);
        textview= findViewById(R.id.text_dsiplay);



        captureImagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              dispatchTakePictureIntent();
              textview.setText("");
            }
        });
        detectTextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
            detectTextFromImage();
            }
        });

    }




    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager())!= null)
        {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
             imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
    private void detectTextFromImage()
    {

        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextDetector firebaseVisionTextDetector= FirebaseVision.getInstance().getVisionTextDetector();
        firebaseVisionTextDetector.detectInImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>()
        {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText)
            {
            displayTextFromImage(firebaseVisionText);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Toast.makeText(MainActivity.this, "Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error:  ", e.getMessage());
            }
        });
    }

    private void displayTextFromImage(FirebaseVisionText firebaseVisionText)
    {
        List<FirebaseVisionText.Block> blockList= firebaseVisionText.getBlocks();
        if(blockList.size()==0)
        {
            Toast.makeText(this, "No text found in image", Toast.LENGTH_SHORT).show();
        }
        else
        {
            for (FirebaseVisionText.Block block: firebaseVisionText.getBlocks())
            {
                String text = block.getText();
                textview.setText(text);
            }
        }
    }


}



