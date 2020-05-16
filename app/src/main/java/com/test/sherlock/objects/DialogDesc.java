package com.test.sherlock.objects;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.sherlock.R;


import java.util.Objects;
import java.util.Random;

public class DialogDesc {


     private static void showDialogWithRating(final Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.test_rv_item_desc_dialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        TextView title = dialog.findViewById(R.id.test_rv_dialog_desc_title);
        TextView desc = dialog.findViewById(R.id.test_rv_dialog_desc_text);
        Button button = dialog.findViewById(R.id.test_rv_dialog_desc_button_dismiss);
        title.setText("Просьба");
        desc.setText("Оставьте отзыв, чтобы мы смогли стать лучше");
        button.setText("Оставить отзыв");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = "com.test.sherlock";
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public static void startDialogWithOfferToSetMarkWithChance(Context context){
        Random random = new Random();
        int rand_int = random.nextInt(20);
        if(rand_int == 1) showDialogWithRating(context);
    }
}
