package com.iutlr.puissance4;

import com.iutlr.puissance4.exceptions.ColonneInvalideException;
import com.iutlr.puissance4.exceptions.ColonnePleineException;
import com.iutlr.puissance4.exceptions.JoueurException;
import com.iutlr.puissance4.exceptions.PlateauInvalideException;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Plateau plateau;
    private List<Joueur> joueurList;

    @Before
    public void init() {
        joueurList = new ArrayList<>();
        joueurList.add(new Joueur("Betsaida", 1));
        joueurList.add(new Joueur("Gerardo", 2));
        joueurList.add(new Joueur("Adalberto", 3));
        joueurList.add(new Joueur("María", 4));
        joueurList.add(new Joueur("Fernando", 5));
    }

    @Test(expected = JoueurException.class)
    public void plateau_isPlayersListCorrect() throws Exception {
        joueurList.add(new Joueur("Miguel", 6));
        plateau = new Plateau(10, 10, joueurList);
    }

    @Test(expected = PlateauInvalideException.class)
    public void plateau_IsTailleCorrecte() throws Exception {
        plateau = new Plateau(3, 2, joueurList);
    }

    @Test
    public void joueur_isGagnantCorrect() throws Exception {

        plateau = new Plateau(10, 10, joueurList);
        plateau.jouer(plateau.getJoueurCourant(), 0);
        plateau.jouer(plateau.getJoueurCourant(), 1);
        plateau.jouer(plateau.getJoueurCourant(), 2);
        plateau.jouer(plateau.getJoueurCourant(), 3);
        plateau.jouer(plateau.getJoueurCourant(), 4);
        plateau.jouer(plateau.getJoueurCourant(), 0);
        plateau.jouer(plateau.getJoueurCourant(), 1);
        plateau.jouer(plateau.getJoueurCourant(), 2);
        plateau.jouer(plateau.getJoueurCourant(), 3);
        plateau.jouer(plateau.getJoueurCourant(), 4);
        plateau.jouer(plateau.getJoueurCourant(), 0);
        plateau.jouer(plateau.getJoueurCourant(), 1);
        plateau.jouer(plateau.getJoueurCourant(), 2);
        plateau.jouer(plateau.getJoueurCourant(), 3);
        plateau.jouer(plateau.getJoueurCourant(), 4);
        plateau.jouer(plateau.getJoueurCourant(), 0);

        assertEquals(joueurList.get(0), plateau.getGagnant());
    }

    @Test
    public void joueur_isCurrentCorrect() throws Exception {
        plateau = new Plateau(4, 4, joueurList);
        assertEquals(joueurList.get(0), plateau.getJoueurCourant());
    }

    @Test
    public void partie_isEnCours() throws Exception {
        plateau = new Plateau(10, 10, joueurList);
        assertEquals(EtatPartie.EN_COURS, plateau.jouer(plateau.getJoueurCourant(), 0));
    }

    @Test(expected = JoueurException.class)
    public void joueur_isJoueurCorrect() throws Exception {
        plateau = new Plateau(5,5, joueurList);
        plateau.jouer(plateau.getJoueurCourant(), 0);
        plateau.jouer(joueurList.get(0), 0);
    }

    @Test(expected = ColonnePleineException.class)
    public void colonne_isPleine() throws Exception {
        plateau = new Plateau(5, 5, joueurList);
        plateau.jouer(plateau.getJoueurCourant(), 0);
        plateau.jouer(plateau.getJoueurCourant(), 0);
        plateau.jouer(plateau.getJoueurCourant(), 0);
        plateau.jouer(plateau.getJoueurCourant(), 0);
        plateau.jouer(plateau.getJoueurCourant(), 0);
        plateau.jouer(plateau.getJoueurCourant(), 0);
    }

    @Test(expected = ColonneInvalideException.class)
    public void colonne_isInvalide() throws Exception {
        plateau = new Plateau(5, 5, joueurList);
        plateau.jouer(plateau.getJoueurCourant(), 6);
    }

    @Test
    public void plateau_isPremiereColonneDisponibleCorrect() throws Exception {
        plateau = new Plateau(4, 4, joueurList);
        int[] celulesDisponibles = {4, 4, 4, 4};
        assertArrayEquals(celulesDisponibles, plateau.getCelulesDisponibles());
    }

    @Test(expected = JoueurException.class)
    public void joueur_PersonneDoitJouer() throws Exception {
        plateau = new Plateau(4, 4, joueurList);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                plateau.jouer(plateau.getJoueurCourant(), j);
            }
        }

        plateau.jouer(plateau.getJoueurCourant(), 0);
    }
}