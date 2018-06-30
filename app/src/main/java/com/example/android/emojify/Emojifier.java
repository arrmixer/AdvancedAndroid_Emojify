package com.example.android.emojify;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class Emojifier {

    public static final String TAG = Emojifier.class.getSimpleName();

    public static void detectFaces(Context context, Bitmap pic) {
        FaceDetector faceDetector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        if(!faceDetector.isOperational()) {
            new AlertDialog.Builder(context).setMessage("Could not set up the face detector!").show();
            return;
        }

        //Build the frame for each pic
        Frame frame = new Frame.Builder().setBitmap(pic).build();

        //Setup SpareArray to detect the faces
        SparseArray<Face> faces = faceDetector.detect(frame);



        Log.i(TAG, "Number of faces = " + faces.size());

        //if no faces alert user
        if(faces.size() == 0){
            new AlertDialog.Builder(context).setMessage("No Faces present in picture").show();
            return;
        }

        //release resource when done
        faceDetector.release();


    }
}
