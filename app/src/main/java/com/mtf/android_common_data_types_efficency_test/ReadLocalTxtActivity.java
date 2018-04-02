package com.mtf.android_common_data_types_efficency_test;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadLocalTxtActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    ArrayMap<String, String> arrayMap = new ArrayMap<>();

    public void blueClick(View view) {
        File file = new File(Environment.getExternalStorageDirectory(),
                "/all.txt");
        read4(file);
//        readDB();
    }

    public void read1(File file) {//3363 3327
        long start = System.currentTimeMillis();
        if (file.exists()) {
            InputStreamReader reader;
            BufferedReader br;
            try {
                reader = new InputStreamReader(new FileInputStream(file));
                br = new BufferedReader(reader);
                String lineContent = null;
                while ((lineContent = br.readLine()) != null) {
                    String[] spl = lineContent.split(" ");
                    arrayMap.put(spl[0], spl[1]);
                }
                br.close();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("no this file");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("io exception");
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis() - start;
        Log.d("blueClick", end + "");
    }

    public void read4(File file) {// 3314
        long start = System.currentTimeMillis();
        try {
            final LineIterator it = FileUtils.lineIterator(file, "UTF-8");
            while (it.hasNext()) {
                String[] spl = it.nextLine().split(" ");
                arrayMap.put(spl[0], spl[1]);
            }
            it.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis() - start;
        Log.d("blueClick", end + "");
    }

    public void readDB() {//4162 4019
        long start = System.currentTimeMillis();
        File file = new File(Environment.getExternalStorageDirectory(),
                "/nextworks.db");
        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.
                openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from nextword", null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                arrayMap.put(cursor.getString(cursor.getColumnIndex("base_word")),
                        cursor.getString(cursor.getColumnIndex("next_word")));
            }
        }
        long end = System.currentTimeMillis() - start;
        Log.d("blueClick", end + "");
    }

    public void readDB2() {//4178 4185
        long start = System.currentTimeMillis();
        File file = new File(Environment.getExternalStorageDirectory(),
                "/nextworks.db");
        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.
                openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from nextword", null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                arrayMap.put(cursor.getString(cursor.getInt(0)),
                        cursor.getString(cursor.getInt(1)));
            }
        }
        long end = System.currentTimeMillis() - start;
        Log.d("blueClick", end + "");
    }


    public void read2(File file) {//10410
        long start = System.currentTimeMillis();
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
                String lineContent = null;
                while ((lineContent = br.readLine()) != null) {
                    String[] spl = lineContent.split(" ");
                    arrayMap.put(spl[0], spl[1]);
                }
                br.close();
                fileReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("no this file");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("io exception");
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis() - start;
        Log.d("blueClick", end + "");
    }

    public void read3(File file) { // 7254
        Log.d("blueClick", System.currentTimeMillis() + "}}}}}}}");
        Long filelength = file.length(); // 获取文件长度
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] fileContentArr = new String(filecontent).split("\n");
        for (String lineContent : fileContentArr) {
            String[] spl = lineContent.split(" ");
            arrayMap.put(spl[0], spl[1]);
        }
        Log.d("blueClick", System.currentTimeMillis() + "++++++" + arrayMap.size());
    }
}
