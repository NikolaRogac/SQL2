package com.example.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    TextView ime,prezime,godina,poeni,formular,id;
    EditText eIme,ePrezime,eGodina,ePoeni,eId;
    Button bUnesi,bPrikazi,bIzbrisi;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ime = (TextView)findViewById(R.id.tvIme);
        prezime = (TextView)findViewById(R.id.tvPrezime);
        godina = (TextView)findViewById(R.id.tvGodina);
        poeni = (TextView)findViewById(R.id.tvPoeni);
        formular = (TextView)findViewById(R.id.tvForm);
        id = (TextView)findViewById(R.id.tvId);

        eIme = (EditText) findViewById(R.id.etIme);
        ePrezime = (EditText) findViewById(R.id.etPrezime);
        eGodina = (EditText) findViewById(R.id.etGodina);
        ePoeni = (EditText) findViewById(R.id.etPoeni);
        eId = (EditText)findViewById(R.id.etId);

        bUnesi = (Button) findViewById(R.id.btnUnesi);
        bPrikazi = (Button) findViewById(R.id.btnPrikazi);
        bIzbrisi = (Button) findViewById(R.id.btnIzbrisi);

        dbHelper = new DbHelper(this);

        
        bUnesi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String ime1 = eIme.getText().toString();
                String prezime1 = ePrezime.getText().toString();
                String godina1 = eGodina.getText().toString();
                String poeni1 = ePoeni.getText().toString();

                if (ime1.equals("") || prezime1.equals("") || godina1.equals("") || poeni1.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Nisu popunjena sva polja!",Toast.LENGTH_SHORT).show();
                } else {

                    boolean unesen = dbHelper.unesiPodatke(ime1,prezime1,godina1,poeni1);
                    if (unesen == true) {
                        Toast.makeText(MainActivity.this, "Unesen polaznik", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Neuspesan unos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        bPrikazi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor cursor = dbHelper.prikaziPodatke();

                StringBuffer buffer = new StringBuffer();
                while(cursor.moveToNext())
                {
                buffer.append("ID: " + cursor.getInt(cursor.getColumnIndexOrThrow(DbContract.TabelaPolaznik._ID)) + "\n");
                buffer.append("IME: " + cursor.getString(cursor.getColumnIndexOrThrow(DbContract.TabelaPolaznik.COLUMN_NAME_IME)) + "\n");
                buffer.append("PREZIME: " + cursor.getString(cursor.getColumnIndexOrThrow(DbContract.TabelaPolaznik.COLUMN_NAME_PREZIME)) + "\n");
                buffer.append("GODINA UPISA: " + cursor.getInt(cursor.getColumnIndexOrThrow(DbContract.TabelaPolaznik.COLUMN_NAME_GODINA_UPISA)) + "\n");
                buffer.append("BROJ POENA: " + cursor.getInt(cursor.getColumnIndexOrThrow(DbContract.TabelaPolaznik.COLUMN_NAME_BROJ_POENA)) + "\n" + "\n");
                }


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("SPISAK");
                builder.setMessage(buffer.toString());
                builder.show();

                cursor.close();
            }
        });

        bIzbrisi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Integer delRow = dbHelper.izbrisiPodatke(eId.getText().toString());
                if(delRow>0)
                {
                    Toast.makeText(MainActivity.this,"OBRISAN",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"NIJE OBRISAN",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}