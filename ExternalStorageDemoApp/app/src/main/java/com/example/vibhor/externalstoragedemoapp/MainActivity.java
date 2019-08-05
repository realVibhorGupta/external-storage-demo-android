package com.example.vibhor.externalstoragedemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private String data = editText.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);

    }

    public void onSaveInternalCache(View view) {

        File folder = getCacheDir();
        File myFile = new File(folder, "myData1.txt");
        writeData(myFile, data);

    }

    public void onSaveExternalCache(View view) {
        File folder = getExternalCacheDir();
        File myFile = new File(folder, "myData2.txt");
        writeData(myFile, data);

    }

    public void onSaveExternalPrivate(View view) {
        File folder = getExternalFilesDir("vibhorAppData1");
        File myFile = new File(folder, "myData3.txt");
        writeData(myFile, data);

    }

    public void onSaveExternalPublic(View view) {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder, "myData4.txt");
        writeData(myFile, data);


    }

    public void onNext(View view) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);

    }


    private void writeData(File myFile, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
