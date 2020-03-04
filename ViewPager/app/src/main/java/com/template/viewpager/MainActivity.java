package com.template.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity";

    private static final int PERMISSION_REQUEST_CODE=100;

    ArrayList fileList = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList images = new ArrayList<>();
        images.add(R.drawable.pretty);
        images.add(R.drawable.serious);
        images.add(R.drawable.tiz);
        images.add(R.drawable.shorts);
        images.add(R.drawable.smile);
        images.add(R.drawable.snazo);
        images.add(R.drawable.xuma);
        images.add(R.drawable.kev);

        File rootDirectory = Environment.getDataDirectory();
        getFile(rootDirectory);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new AdapterSlider(MainActivity.this, images));

    }       //end onCreate()


    // Pass directory path to this function and it will return the files
    public void getFile(File dir) {
        File listFile[] = dir.listFiles();
        
        if (listFile != null && listFile.length > 0) {
            Log.d(TAG, "getFile: Folder is not empty");
            
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    //fileList.add(listFile[i]);
                    getFile(listFile[i]);
                }
                else {

                    //if file has following extensions, add to the list of files
                    if(listFile[i].getName().endsWith(".jpg")
                            || listFile[i].getName().endsWith(".png")
                            || listFile[i].getName().endsWith(".jpeg")
                            || listFile[i].getName().endsWith(".gif")
                    ){
                        Log.d(TAG, "getFile: "+listFile[i].getAbsolutePath());

                        Drawable drawable = Drawable.createFromPath(String.valueOf(listFile[i]));
                        fileList.add(drawable);

                    }

                }       //end else

            }       //end for()
        }
        else {
            Log.d(TAG, "getFile: Folder is empty");
        }

    }       //end getFile()


//    public void getExternalImages(){
//
//        String state = Environment.getExternalStorageState();
//        if (Environment.MEDIA_MOUNTED.equals(state)) {
//            if (Build.VERSION.SDK_INT >= 23) {
//                if (checkPermission()) {
//
//                    File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/images.jpeg");
//                    if (dir.exists()) {
//                        Log.d("path", dir.toString());
//                        BitmapFactory.Options options = new BitmapFactory.Options();
//                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//                        Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(dir), options);
//                        imageView.setImageBitmap(bitmap);
//                    }
//                }
//                else {
//                    requestPermission(); // Code for permission
//                }
//            }
//            else {
//                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/images.jpeg");
//                if (dir.exists()) {
//                    Log.d("path", dir.toString());
//                    BitmapFactory.Options options = new BitmapFactory.Options();
//                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//                    Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(dir), options);
//                    imageView.setImageBitmap(bitmap);
//                }
//            }
//        }
//
//    }       //end getExternalImages()


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result <= PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            return false;
        }

    }       //end checkPermission()


    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(MainActivity.this, "Write External Storage permission allows us to read  files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }

    }       //end requestPermission()


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length>0 && grantResults[0] <= PackageManager.PERMISSION_GRANTED){
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                }
                else{
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }

                break;
            }       //end case

        }       //end switch()

    }       //end onRequestPermissionsResult()



}       //end class
