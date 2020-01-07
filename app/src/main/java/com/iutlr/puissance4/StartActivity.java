package com.iutlr.puissance4;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText edtLargeur;
    private EditText edtHauteur;
    private Button btnCreer;

    public static final String KEY_WIDTH = "width";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_QUANTITY_PLAYERS = "quantity_players";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        spinner = findViewById(R.id.spinner);
        edtLargeur = findViewById(R.id.edtLargueur);
        edtHauteur = findViewById(R.id.edtHauteur);
        btnCreer = findViewById(R.id.btnCreer);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.players_quantity, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void createGame(View view) {
        int playersQuantity = Integer.valueOf(spinner.getSelectedItem().toString());
        int width = Integer.valueOf(edtLargeur.getText().toString());
        int height = Integer.valueOf(edtHauteur.getText().toString());
        if (width >= 4 && height >= 4) {
            Intent tableIntent = new Intent(this, TableActivity.class);
            tableIntent.putExtra(KEY_WIDTH, width);
            tableIntent.putExtra(KEY_HEIGHT, height);
            tableIntent.putExtra(KEY_QUANTITY_PLAYERS, playersQuantity);
            startActivity(tableIntent);
        } else {
            DialogDisplayer.displayAlert(this,"La largueur et la hauteur doivent être plus grandes ou egales à 4");
        }
    }
}
