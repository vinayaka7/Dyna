package com.dynaforms.utilities;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class AppUtils {

    public static void shortToast(Context context, String toastMessage) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }
    public static void loadImageFromApi(ImageView imageView, int imageDrawable) {

        Glide.with(imageView.getContext()).load(imageDrawable)
                .into(imageView);

    }
}
