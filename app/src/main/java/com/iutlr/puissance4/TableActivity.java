package com.iutlr.puissance4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iutlr.puissance4.exceptions.ColonneInvalideException;
import com.iutlr.puissance4.exceptions.ColonnePleineException;
import com.iutlr.puissance4.exceptions.JoueurException;
import com.iutlr.puissance4.exceptions.PlateauInvalideException;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    private LinearLayout containerPlateau;
    private ImageView playerAvatar;
    private TextView playerName;
    private EtatPartie etatPartie;
    private Plateau plateauDeJeu;

    private final int[] PLAYER_AVATARS = {R.drawable.player1, R.drawable.player2, R.drawable.player3, R.drawable.player4, R.drawable.player5};
    private ArrayList<Joueur> joueurArrayList;
    public Joueur currentPlayer;

    private int colonnesDisponibles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        containerPlateau = findViewById(R.id.containerPlateau);
        etatPartie = EtatPartie.EN_COURS;
        int width = getIntent().getIntExtra(StartActivity.KEY_WIDTH, 0);
        int height = getIntent().getIntExtra(StartActivity.KEY_HEIGHT, 0);
        int quantityPlayers = getIntent().getIntExtra(StartActivity.KEY_QUANTITY_PLAYERS, 0);

        colonnesDisponibles = width * height;
        initPlayers(quantityPlayers);
        initPlateau(width, height);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

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

    private void initPlayers(int quantity) {
        for (int i = 0; i < quantity; i++) {
            joueurArrayList.add(new Joueur("Player " + (i+1), PLAYER_AVATARS[i]));
        }
    }

    private void initPlateau(int width, int height) {
        try {
            plateauDeJeu = new Plateau(width, height, joueurArrayList);
        } catch (PlateauInvalideException | JoueurException ex) {
            ex.printStackTrace();
        }
    }
}
