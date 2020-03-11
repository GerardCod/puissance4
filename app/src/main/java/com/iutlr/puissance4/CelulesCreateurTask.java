package com.iutlr.puissance4;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CelulesCreateurTask extends AsyncTask<Integer, Void, ArrayList<View>> {
    private Context context;
    private WeakReference<ColonneView> colonneView;

    public CelulesCreateurTask(Context context, ColonneView colonneView) {
        this.context = context;
        this.colonneView = new WeakReference<>(colonneView);
    }

    @Override
    protected ArrayList<View> doInBackground(Integer... integers) {
        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < integers[0]; i++) {
            ImageView imageView = new ImageView(context);
            Drawable drawable = this.context.getDrawable(R.drawable.cell);
            assert drawable != null;
            imageView.setImageDrawable(drawable.getCurrent());
            views.add(imageView);
        }
        return views;
    }

    @Override
    protected void onPostExecute(ArrayList<View> views) {
        super.onPostExecute(views);
        for (View image : views) {
            colonneView.get().addView(image);
        }
    }
}
