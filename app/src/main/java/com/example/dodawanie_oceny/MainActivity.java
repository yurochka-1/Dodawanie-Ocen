package com.example.dodawanie_oceny;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    Spinner spinner, spinner2;
    CheckBox checkBox;
    EditText editText;
    Button buttonDodaj, buttonWyswietl;
    ListView listView;
    ArrayAdapter <Ocena>arrayAdapter;
    ArrayList <Ocena>arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        checkBox = findViewById(R.id.checkBox);
        editText = findViewById(R.id.editTextNumber);
        buttonDodaj = findViewById(R.id.button);
        buttonWyswietl = findViewById(R.id.button2);
        listView = findViewById(R.id.listView);

        arrayList = new ArrayList<>();
        ArrayList<Ocena> arrayListWybrany = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayListWybrany);
        listView.setAdapter(arrayAdapter);

        buttonDodaj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int wartoscOceny = seekBar.getProgress();
                        String przedmiot = spinner.getSelectedItem().toString();
                        boolean czyLiczone = checkBox.isChecked();
                        int waga;
                        if (czyLiczone) {
                            waga = Integer.parseInt(editText.getText().toString());
                        }else{
                            waga = 0;
                        }
                        arrayList.add(new Ocena(wartoscOceny, waga, przedmiot, czyLiczone));
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );

        buttonWyswietl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String wybranyPrzedmiot = spinner2.getSelectedItem().toString();
                        arrayListWybrany.clear();
                        //Toast.makeText(MainActivity.this, wybranyPrzedmiot, Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < arrayList.size(); i++){
                            if (arrayList.get(i).przedmiot.equals(wybranyPrzedmiot)){
                                arrayListWybrany.add(arrayList.get(i));

                            }

                        }
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        TextView textView = (TextView)view;

                        if (textView.getPaintFlags() == Paint.STRIKE_THRU_TEXT_FLAG){
                            textView.setPaintFlags(Paint.CURSOR_AFTER);
                        }else{
                            textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        }
                    }
                }
        );

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        arrayList.remove(arrayListWybrany.get(position));
                        arrayListWybrany.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        return false;
                    }
                }
        );
    }
}