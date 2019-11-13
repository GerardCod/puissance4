package com.iutlr.puissance4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class StartActivity extends AppCompatActivity {

    EditText edtNombre;
    EditText edtLargeur;
    EditText edtHauteur;
    Button btnCreer;
    Button btnAjouter;
    ScrollView blueScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        edtNombre = findViewById(R.id.edtJoueurs);
        edtLargeur = findViewById(R.id.edtLargueur);
        edtHauteur = findViewById(R.id.edtHauteur);
        btnCreer = findViewById(R.id.btnCreer);
        btnAjouter = findViewById(R.id.btnAjouter);
        blueScroll = findViewById(R.id.blueScroll);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nombre = Integer.valueOf(edtNombre.getText().toString());
                LinearLayout linearLayout = new LinearLayout(StartActivity.this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                for (int i = 0; i < nombre; i++) {
                    JoueurView joueurView = new JoueurView(StartActivity.this, "Joueur " + (i + 1), blueScroll);

                }
            }
        });
    }
}
