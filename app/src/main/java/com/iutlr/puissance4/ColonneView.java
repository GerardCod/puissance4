package com.iutlr.puissance4;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ColonneView extends LinearLayout {
    private Context context;
    private int id;

    public ColonneView(Context context, int hauteur, int id) {
        super(context);
        this.context = context;
        this.id = id;
        setOrientation(LinearLayout.VERTICAL);
        new CelulesCreateurTask(context, this).execute(hauteur);
    }

    public ColonneView(Context context) {
        super(context);
        this.context = context;
    }

    public ColonneView(Context context, @Nullable AttributeSet attrs, Context context1) {
        super(context, attrs);
        this.context = context1;
    }

    public ColonneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Context context1) {
        super(context, attrs, defStyleAttr);
        this.context = context1;
    }

    public int getId() {
        return id;
    }

    public void ocupperColonne(Joueur joueur, int idCelule) {
        ImageView image = (ImageView) getChildAt(idCelule);
        Drawable drawable = context.getDrawable(obtenirImageDeCelule(joueur.getImageResId()));
        if (image != null && drawable != null) {
            image.setImageDrawable(drawable.getCurrent());
        }
    }

    private int obtenirImageDeCelule(int joueurImage) {
        switch (joueurImage) {
            case R.drawable.player1:
                return R.drawable.colonne_player1;
            case R.drawable.player2:
                return R.drawable.colonne_player2;
            case R.drawable.player3:
                return R.drawable.colonne_player3;
            case R.drawable.player4:
                return R.drawable.colonne_player4;
            case R.drawable.player5:
                return R.drawable.colonne_player5;
            default:
                return R.drawable.cell;
        }
    }
}
