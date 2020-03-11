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
     * Variable pour gerer le changement de joueur
     */
    private int positionCourant;

    /**
     * Ce tableau represent les celules disponibles par chaque colonne du plateau de jeu.
     */
    private int[] celulesDisponibles;

    /**
     * Cette matrice est pour trouver le joueur gagnant
     */
    private int[][] plateau;

    /**
     * Cette variable est pour garder la valeur du joueur gagnant;
     */
    private Joueur joueurGagnant;

    /**
     * Garde la valeur de la partie actuelle
     */
    private EtatPartie partie;

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
            throw new JoueurException("La quantité maximum de joueurs par partie est de 5");
        } else {
            this.largeur = largeur;
            this.hauteur = hauteur;
            this.joueurs = joueurs;
            this.positionCourant = 0;
            celulesDisponibles = new int[largeur];
            plateau = new int[largeur][hauteur];
            initialiserCelulesDisponibles();
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
        if (partie == EtatPartie.VICTOIRE || partie == EtatPartie.EGAL) {
            throw new JoueurException("La partie est terminée personne doit jouer");
        }
        return joueurs.get(positionCourant);
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
        if (colonne > largeur || colonne < 0) {
            throw new ColonneInvalideException("L'index de la colonne est incorrect");
        } else if (celulesDisponibles[colonne] < 1) {
            throw new ColonnePleineException("La colonne est déjà pleine");
        } else if (j != getJoueurCourant()) {
            throw new JoueurException("Ce joeur ne peut pas jouer encore");
        } else {
            celulesDisponibles[colonne] -= 1;
            plateau[colonne][celulesDisponibles[colonne]] = j.getImageResId();
        }
        Joueur joueurGagnant = getGagnant();
        positionCourant = (positionCourant < (joueurs.size() - 1)) ? (positionCourant + 1) : 0;
        int colonneDisponible = premiereColonneDisponible();
        partie = (joueurGagnant != null) ? EtatPartie.VICTOIRE : (colonneDisponible != -1) ? EtatPartie.EN_COURS : EtatPartie.EGAL;
        return partie;
    }

    /**
     * Indique le gagnant d'une partie
     * @return le gagnant si un joueur a déjà gagné, null sinon
     */
    public Joueur getGagnant() {
        // TODO
        if (joueurGagnant == null) {
            Joueur joueur = joueurs.get(positionCourant);

            for (int i = 0; i < largeur; i++) {
                if (verifierPlateau(joueur, 0, 1, i, 0, 0) || verifierPlateau(joueur, 1, 1, i, 0, 0) || verifierPlateau(joueur, -1, 1, i, 0, 0)) {
                    joueurGagnant = joueur;
                    return joueurGagnant;
                }
            }

            for (int i = 0; i < hauteur; i ++) {
                if (verifierPlateau(joueur, 1, 0, 0, i, 0) || verifierPlateau(joueur, 1, 1, 0, i, 0) || verifierPlateau(joueur, -1, 1, largeur - 1, i, 0)) {
                    joueurGagnant = joueur;
                    return joueurGagnant;
                }
            }

            return null;
        }
        return joueurGagnant;
    }

    /**
     * Cette methode rendre le tableau de celules disponibles par chaque colonne du plateau
     * @return le tableaux de celules disponibles
     */
    public int[] getCelulesDisponibles() {
        return celulesDisponibles;
    }

    /**
     * Cette methode mis en place le nombre de celules disponibles de chaque colonne
     */
    private void initialiserCelulesDisponibles() {
        for (int i = 0; i < celulesDisponibles.length; i++) {
            celulesDisponibles[i] = hauteur;
        }
    }

    /**
     * Rendre l'index du premiere colonne pas pleine dans le tableau de celulesDisponibles
     * @return index du colonne
     *         -1 si toutes les colonnes sont déjà pleines
     */
    private int premiereColonneDisponible() {
        for (int i = 0; i < celulesDisponibles.length; i++) {
            if (celulesDisponibles[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Verifie si le joueur courant a 4 celules consecutives dans le plateau.
     * @param joueur le joueur courant
     * @return true si le joueur a 4 celules consecutives
     *         false dans le cas contraire
     */
    private boolean verifierPlateau(Joueur joueur, int dirX, int dirY, int colonne, int ligne, int total) {
        if (total >= 4) {
            return true;
        }

        if (colonne < 0 || colonne >= largeur || ligne < 0 || ligne >= hauteur) {
            return false;
        }

        int celule = plateau[colonne][ligne];

        if (celule == joueur.getImageResId()) {
            return verifierPlateau(joueur, dirX, dirY, colonne + dirX, ligne + dirY, total + 1);
        } else {
            return  verifierPlateau(joueur, dirX, dirY, colonne + dirX, ligne + dirY, 0);
        }
    }

}
