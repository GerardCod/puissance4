package com.iutlr.puissance4;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.iutlr.puissance4.exceptions.ColonneInvalideException;
import com.iutlr.puissance4.exceptions.ColonnePleineException;
import com.iutlr.puissance4.exceptions.JoueurException;
import com.iutlr.puissance4.exceptions.PlateauInvalideException;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity implements View.OnClickListener, AdviceDialogFragment.AdviceDialogListener {
    private LinearLayout containerPlateau;
    private ImageView playerAvatar;
    private TextView playerName;
    private EtatPartie etatPartie;
    private Plateau plateauDeJeu;

    private final int[] PLAYER_AVATARS = {R.drawable.player1, R.drawable.player2, R.drawable.player3, R.drawable.player4, R.drawable.player5};
    private ArrayList<Joueur> joueurArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        etatPartie = EtatPartie.EN_COURS;
        playerAvatar = findViewById(R.id.playerImage);
        playerName = findViewById(R.id.playerName);
        containerPlateau = findViewById(R.id.containerPlateau);

        int width = getIntent().getIntExtra(StartActivity.KEY_WIDTH, 0);
        int height = getIntent().getIntExtra(StartActivity.KEY_HEIGHT, 0);
        int quantityPlayers = getIntent().getIntExtra(StartActivity.KEY_QUANTITY_PLAYERS, 0);

        joueurArrayList = new ArrayList<>();
        initPlayers(quantityPlayers);
        initPlateau(width, height);

        try {
            playerAvatar.setImageDrawable(getDrawable(plateauDeJeu.getJoueurCourant().getImageResId()).getCurrent());
            playerName.setText(plateauDeJeu.getJoueurCourant().getNom());
        } catch (JoueurException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (int i = 0; i < plateauDeJeu.getLargeur(); i++) {
            final ColonneView colonneView = new ColonneView(getApplicationContext(), plateauDeJeu.getHauteur(), i);
            colonneView.setOnClickListener(this);
            containerPlateau.addView(colonneView);
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

    @Override
    public void onClick(View v) {
        ColonneView colonne = (ColonneView) v;
        int celule = plateauDeJeu.getCelulesDisponibles()[colonne.getId()] - 1;
        try {
            Joueur joueur = plateauDeJeu.getJoueurCourant();
            colonne.ocupperColonne(joueur, celule);
            etatPartie = plateauDeJeu.jouer(joueur, colonne.getId());
            playerAvatar.setImageDrawable(getApplicationContext().getDrawable(joueur.getImageResId()));
            playerName.setText(joueur.getNom());

            if (etatPartie == EtatPartie.VICTOIRE) {
                showMessage("VICTOIRE", plateauDeJeu.getGagnant().getNom() + " a gagné le jeu. Voulez-vous recommercer la partie?");
            } else if (etatPartie == EtatPartie.EGAL) {
                showMessage("EGAL", "Voulez-vous recommencer la partie?");
            }
        } catch (JoueurException | ColonnePleineException | ColonneInvalideException e) {
            DialogDisplayer.displayAlert(this, e.getMessage());
        }
    }

    public void showMessage(String type, String message) {
        AdviceDialogFragment dialog = new AdviceDialogFragment();
        dialog.setMessage(message);
        dialog.setType(type);
        dialog.show(getSupportFragmentManager(), "AdviceDialogFragment");
    }

    @Override
    public void onPositiveButtonClick(DialogFragment dialog) {
        int quantityPlayers = getIntent().getIntExtra(StartActivity.KEY_QUANTITY_PLAYERS, 0);
        Intent intent = new Intent(this, this.getClass());
        intent.putExtra(StartActivity.KEY_WIDTH, plateauDeJeu.getLargeur());
        intent.putExtra(StartActivity.KEY_HEIGHT, plateauDeJeu.getHauteur());
        intent.putExtra(StartActivity.KEY_QUANTITY_PLAYERS, quantityPlayers);
        startActivity(intent);
        finish();
    }

    @Override
    public void onNegativeButtonClick(DialogFragment dialog) {
        dialog.dismiss();
        finish();
    }
}
