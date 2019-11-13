package com.iutlr.puissance4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class JoueurView extends FrameLayout {
    private String title;

    private TextView txtTitle;
    private EditText edtNomJoueur;
    private ViewGroup parent;

    public JoueurView (Context context, String title, final ViewGroup parent) {
        super(context);
        inflate(context, R.layout.joueur_item, this);
        this.title = title;
        this.parent = parent;
        this.txtTitle = findViewById(R.id.txtNomJoueur);
        this.txtTitle.setText(this.title);
        this.edtNomJoueur = findViewById(R.id.edtNomUtilisateur);
    }
}
