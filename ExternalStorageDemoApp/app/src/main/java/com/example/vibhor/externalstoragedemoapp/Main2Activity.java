package com.example.vibhor.externalstoragedemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {


    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText= (EditText) findViewById(R.id.editText2);


    }

    public void onLoadInternalCache(View view){


      File folder=  getCacheDir();
        File myFile = new File(folder, "myData1.txt");

        String data = readData(myFile);
        if(data != null) editText.setText(data);
        else
        editText.setText("no data found");
    }
    public void onLoadExternalCache(View view){
        File folder= getExternalCacheDir();
        File myFile = new File(folder, "myData2.txt");

        String data = readData(myFile);
        if(data != null) editText.setText(data);
        else
            editText.setText("no data found");
    }
    public void onLoadExternalPrivate(View view)
    {
        File folder= getExternalFilesDir("vibhorAppData1");
        File myFile = new File(folder, "myData3.txt");

        String data = readData(myFile);
        if(data != null) editText.setText(data);
        else
            editText.setText("no data found");
    }
    public   void onLoadExternalPublic(View view)
    {
        File folder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder, "myData4.txt");

        String data = readData(myFile);
        if(data != null) editText.setText(data);
        else
            editText.setText("no data found");
    }
    public void onPrevious(View view)
    {
        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }
    private String  readData(File myFile){

        FileInputStream  fileInputStream= null;
        StringBuffer stringBuffer=new StringBuffer();

        try {
            fileInputStream=new FileInputStream(myFile);
            int read=-1;
           while( (read = fileInputStream.read())!= -1)
           {
                stringBuffer.append((char) read);
           }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream == null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
            return null;
    }

}
