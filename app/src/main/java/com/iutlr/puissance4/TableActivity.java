package com.iutlr.puissance4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.iutlr.puissance4.exceptions.ColonneInvalideException;
import com.iutlr.puissance4.exceptions.ColonnePleineException;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    private TableLayout plateau;
    private ImageView playerAvatar;
    private TextView playerName;
    private EtatPartie etatPartie = EtatPartie.EN_COURS;
    private Plateau plateauDeJeu;

    private final int[] PLAYER_AVATARS = {R.drawable.player1, R.drawable.player2, R.drawable.player3, R.drawable.player4, R.drawable.player5};
    private ArrayList<Joueur> joueurArrayList;
    public Joueur currentPlayer;

    private int colonnesDisponibles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        plateau = findViewById(R.id.plateau);
        playerAvatar = findViewById(R.id.playerImage);
        playerName = findViewById(R.id.playerName);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int width = getIntent().getIntExtra(StartActivity.KEY_WIDTH, 0);
        int height = getIntent().getIntExtra(StartActivity.KEY_HEIGHT, 0);
        int quantity = getIntent().getIntExtra(StartActivity.KEY_QUANTITY_PLAYERS, 0);
        colonnesDisponibles = width * height;

        initPlateau(width, height);
        joueurArrayList = initPlayers(quantity);

        try {
            plateauDeJeu = new Plateau(width, height, joueurArrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        currentPlayer = joueurArrayList.get(0);
        playerAvatar.setImageDrawable(getDrawable(currentPlayer.getImageResId()));
        playerName.setText(currentPlayer.getNom());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("You've restarted the activity");
        plateau.removeAllViewsInLayout();
        colonnesDisponibles = plateauDeJeu.getLargeur() * plateauDeJeu.getLargeur();

        initPlateau(plateauDeJeu.getLargeur(), plateauDeJeu.getHauteur());
        playerAvatar.setImageDrawable(getDrawable(currentPlayer.getImageResId()));
        playerName.setText(currentPlayer.getNom());
    }

    public void nextPlayer() {
        --colonnesDisponibles;
        int currentPosition = joueurArrayList.indexOf(currentPlayer);

        if (currentPosition == (joueurArrayList.size() - 1)) {
            currentPlayer = joueurArrayList.get(0);
        } else {
            currentPlayer = joueurArrayList.get((currentPosition + 1));
        }

        playerAvatar.setImageDrawable(getDrawable(currentPlayer.getImageResId()));
        playerName.setText(currentPlayer.getNom());

        if (colonnesDisponibles == 0) {
            if (DialogDisplayer.displayAlert(this, "Le plateau est déjà plein, est-ce que tu veux recommencer la partie?"))
                onRestart();
        }
    }

    private void initPlateau(int width, int height) {
        int colonneId = 1;
        for (int i = 0; i < height; i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < width; j++) {
                ColonneItem colonneItem = new ColonneItem(this, this, colonneId);
                row.addView(colonneItem);
                colonneId++;
            }
            plateau.addView(row);
        }
    }

    private ArrayList<Joueur> initPlayers(int quantity) {
        ArrayList<Joueur> joueurs = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            joueurs.add(new Joueur(getString(R.string.player_name, (i+1)), PLAYER_AVATARS[i]));
        }
        return joueurs;
    }
}
