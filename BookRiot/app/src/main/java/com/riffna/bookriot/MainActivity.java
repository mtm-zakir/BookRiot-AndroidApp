package com.riffna.bookriot;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.riffna.bookriot.adapters.BookListAdapter;
import com.riffna.bookriot.model.BookModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BookListAdapter.BookListClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Book Riot");

        List<BookModel> bookModelList =  getBookData();

        initRecyclerView(bookModelList);
    }

    private void initRecyclerView(List<BookModel> bookModelList ) {
        RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BookListAdapter adapter = new BookListAdapter(bookModelList, this);
        recyclerView.setAdapter(adapter);
    }

    private List<BookModel> getBookData() {
        InputStream is = getResources().openRawResource(R.raw.book);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try{
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while(( n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0,n);
            }
        }catch (Exception e) {

        }

        String jsonStr = writer.toString();
        Gson gson = new Gson();
        BookModel[] bookModels =  gson.fromJson(jsonStr, BookModel[].class);
        List<BookModel> restList = Arrays.asList(bookModels);

        return  restList;

    }

    @Override
    public void onItemClick(BookModel bookModel) {
        Intent intent = new Intent(MainActivity.this, BookMenuActivity.class);
        intent.putExtra("BookModel", bookModel);
        startActivity(intent);

    }

    //Back Press Exit
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Confirm Exit");
        alertDialog.setMessage(" \"Are you sure you want to exit?\"");
        alertDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}