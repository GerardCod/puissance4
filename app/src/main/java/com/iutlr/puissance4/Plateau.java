package com.iutlr.puissance4;

import com.iutlr.puissance4.exceptions.ColonneInvalideException;
import com.iutlr.puissance4.exceptions.ColonnePleineException;
import com.iutlr.puissance4.exceptions.JoueurException;
import com.iutlr.puissance4.exceptions.PlateauInvalideException;

import java.util.List;

public class Plateau {
    private int largeur;
    private int hauteur;
    private List<Joueur> joueurs;
    /**
     * Construit un nouveau plateau de jeu vide
     * @param largeur la largeur du plateau
     * @param hauteur la hauteur du plateau
     * @param joueurs la liste des joueurs
     * @throws PlateauInvalideException si la taille du plateau est incorrecte (mini 4x4)
     * @throws JoueurException si le nombre de joueurs est invalide (5 max)
     */
    public Plateau(int largeur, int hauteur, List<Joueur> joueurs) throws PlateauInvalideException, JoueurException {
     // TODO
        if (largeur < 4 || hauteur < 4) {
            throw new PlateauInvalideException();
        } else if (joueurs.size() > 5) {
            throw new JoueurException();
        } else {
            this.largeur = largeur;
            this.hauteur = hauteur;
            this.joueurs = joueurs;
        }
    }

    /**
     * Retourne la largeur du plateau
     * @return la largeur du plateau
     */
    public int getLargeur() {
        // TODO
        return this.largeur;
    }

    /**
     * Retourne la hauteur du plateau
     * @return la hauteur du plateau
     */
    public int getHauteur() {
        // TODO
        return this.hauteur;
    }

    /**
     * Retourne le joueur qui doit jouer
     * @return le joueur qui doit jouer
     * @throws JoueurException si personne ne doit jouer (fin de partie...)
     */
    public Joueur getJoueurCourant() throws JoueurException {
        // TODO
        return joueurs.get(0);
    }

    /**
     * Joue une position
     * @param j le joueur jouant la position
     * @param colonne l'indexe de la colonne jouée
     * @throws ColonneInvalideException si l'index de la colonne n'existe pas
     * @throws ColonnePleineException si la colonne jouée est déjà pleine
     * @throws JoueurException si ce n'est pas a ce joueur de jouer
     */
    public EtatPartie jouer(Joueur j, int colonne) throws ColonneInvalideException, ColonnePleineException, JoueurException {
        // TODO
        return EtatPartie.EN_COURS;
    }

    /**
     * Indique le gagnant d'une partie
     * @return le gagnant si un joueur a déjà gagné, null sinon
     */
    public Joueur getGagnant() {
        // TODO
        return joueurs.get(0);
    }
}
