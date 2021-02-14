package com.app.pengingatutang;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.app.pengingatutang.adapter.PeminjamanListAdapter;
import com.app.pengingatutang.database.DBHelper;
import com.app.pengingatutang.entities.Peminjaman;
import com.app.pengingatutang.function.FunctionPeminjaman;


public class MainActivity extends AppCompatActivity{

    DBHelper dbHelper = new DBHelper(this);
    Cursor cursor;
    int amount;
    int pay;
    RecyclerView recyclerView;

    String save_pattern_key = "key_code";
    PatternLockView mPatternLockView;
    String final_pattern = "";

    @Override
    protected void onStart() {
        super.onStart();

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        TextView totalLend = findViewById(R.id.total_lend);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.isOpen();

        cursor = sqLiteDatabase.rawQuery("SELECT SUM(amount) FROM peminjaman",null);
        if (cursor.moveToFirst()){
            amount = cursor.getInt(0);
        }else {
            amount = -1;
        }
        cursor.close();



        totalLend.setText(formatRupiah.format((double)amount));
        cursor = sqLiteDatabase.rawQuery("SELECT SUM (pay) FROM pembayaran",null);
        if (cursor.moveToFirst()){
            pay = cursor.getInt(0);
        }else {
            pay = -1;
        }
        cursor.close();
        TextView collected = findViewById(R.id.collected);

        collected.setText(formatRupiah.format((double)pay));
        TextView remaining = findViewById(R.id.remaining);
        int result;
        result = amount - pay;


        remaining.setText(formatRupiah.format((double)result));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



            setContentView(R.layout.activity_main);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        Button btntambah = (Button) findViewById(R.id.button);
        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,AddActivity.class);
                    startActivity(intent);
                }
            });

            recyclerView = findViewById(R.id.list_view_peminjaman);
            FunctionPeminjaman functionPeminjaman =  new FunctionPeminjaman(this);
            ArrayList<Peminjaman> listPeminjaman = new ArrayList<>();
            listPeminjaman.addAll(functionPeminjaman.findAll());
            PeminjamanListAdapter adapter = new PeminjamanListAdapter(this,listPeminjaman);
            recyclerView.setAdapter(adapter);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            DividerItemDecoration divider = new DividerItemDecoration(this,layoutManager.getOrientation());
            recyclerView.addItemDecoration(divider);



    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_debt_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.About:
                showDialog();

            default:
                new IllegalArgumentException("Ups");
        }
        return super.onOptionsItemSelected(item);
    }
    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);


        alertDialogBuilder.setTitle("PBO IF-2");


        alertDialogBuilder

                .setNegativeButton("Oke",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();
    }
}
