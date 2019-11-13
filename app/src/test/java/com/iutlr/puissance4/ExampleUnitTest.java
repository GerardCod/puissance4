package com.iutlr.puissance4;

import com.iutlr.puissance4.exceptions.JoueurException;
import com.iutlr.puissance4.exceptions.PlateauInvalideException;

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
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test(expected = JoueurException.class)
    public void plateau_isPlayersListCorrect() throws Exception {
        List<Joueur> joueurList = new ArrayList<>();
        joueurList.add(new Joueur("Betsaida", 1));
        joueurList.add(new Joueur("Gerardo", 2));
        joueurList.add(new Joueur("Adalberto", 3));
        joueurList.add(new Joueur("María", 4));
        joueurList.add(new Joueur("Fernando", 5));

        Plateau plateau = new Plateau(10, 10, joueurList);
    }

    @Test(expected = PlateauInvalideException.class)
    public void plateau_IsTailleCorrecte() throws Exception {
        List<Joueur> joueurs = new ArrayList<>();
        Plateau plateau = new Plateau(3, 2, joueurs);
    }

    @Test
    public void joueur_isGagnantCorrect() throws Exception {
        Joueur joueur1 = new Joueur("Gerardo", 1);
        Joueur joueur2 = new Joueur("Adalberto", 2);
        List<Joueur> joueurList = new ArrayList<>();
        joueurList.add(joueur1);
        joueurList.add(joueur2);
        Plateau plateau = new Plateau(10, 10, joueurList);

        assertEquals(joueur1, plateau.getGagnant());
    }

    @Test
    public void joueur_isCurrentCorrect() throws Exception {
        Joueur joueur1 = new Joueur("Gerardo", 1);
        Joueur joueur2 = new Joueur("Adalberto", 2);
        List<Joueur> joueurList = new ArrayList<>();
        joueurList.add(joueur1);
        joueurList.add(joueur2);
        Plateau plateau = new Plateau(10, 10, joueurList);

        assertEquals(joueur1, plateau.getJoueurCourant());
    }

    @Test
    public void partie_isEnCours() throws Exception {
        Joueur joueur1 = new Joueur("Gerardo", 1);
        Joueur joueur2 = new Joueur("Adalberto", 2);
        List<Joueur> joueurList = new ArrayList<>();
        joueurList.add(joueur1);
        joueurList.add(joueur2);
        Plateau plateau = new Plateau(10, 10, joueurList);

        assertEquals(EtatPartie.EN_COURS, plateau.getJoueurCourant());
    }
}