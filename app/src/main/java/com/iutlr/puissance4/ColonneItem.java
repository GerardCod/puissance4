package com.iutlr.puissance4;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ColonneItem extends ConstraintLayout implements View.OnClickListener {
    private Context context;
    private ImageView imageColonne;
    private int colonneId;
    private boolean pleine;
    TableActivity parent;

    public ColonneItem(Context context, final TableActivity parent, int colonneId) {
        super(context);
        this.context = context;
        this.colonneId = colonneId;
        inflate(this.context, R.layout.colonne_item, this);
        this.pleine = false;
        this.parent = parent;
        imageColonne = findViewById(R.id.imageColonne);
        imageColonne.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (pleine) {
            DialogDisplayer.displayAlert(context, "La colonne est déjà pleine.");
        } else {
            selectColonne(parent.currentPlayer);
            parent.nextPlayer();
        }
    }

    public boolean isPleine() {
        return pleine;
    }

    public void selectColonne(Joueur joueur) {
        switch (joueur.getNom()) {
            case "Player 1":
                imageColonne.setImageDrawable(context.getDrawable(R.drawable.colonne_player1));
                break;
            case "Player 2":
                imageColonne.setImageDrawable(context.getDrawable(R.drawable.colonne_player2));
                break;
            case "Player 3":
                imageColonne.setImageDrawable(context.getDrawable(R.drawable.colonne_player3));
                break;
            case "Player 4":
                imageColonne.setImageDrawable(context.getDrawable(R.drawable.colonne_player4));
                break;
            case "Player 5":
                imageColonne.setImageDrawable(context.getDrawable(R.drawable.colonne_player5));
                break;
        }
        pleine = true;
    }
}
