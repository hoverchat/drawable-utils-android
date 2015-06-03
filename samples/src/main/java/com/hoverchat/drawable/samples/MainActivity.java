/*
 * Copyright (C) 2015 HoverChat
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.hoverchat.drawable.samples;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.hoverchat.drawable.StateBitmap;
import com.hoverchat.drawable.StateListDrawableBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Mostafa Gazar <mmegazar@gmail.com>
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupImageButtonSamples();
    }

    private void setupImageButtonSamples() {
        setupBitmapImageButton();
        setupTintImageButton();
        setupNinePatchImageButton();
    }

    private void setupBitmapImageButton() {
        ImageButton bitmapImageButton = (ImageButton) findViewById(R.id.bitmap_image_button);

        // Source state bitmaps.
        StateBitmap normalStateBitmap = new StateBitmap.Builder()
                .setStates(new StateBitmap.State[]{})
                .setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_button_bitmap_normal))
                .build();
        StateBitmap pressedStateBitmap = new StateBitmap.Builder()
                .setStates(new StateBitmap.State[]{StateBitmap.State.Pressed})
                .setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_button_bitmap_pressed))
                .build();

        Drawable srcDrawable  = new StateListDrawableBuilder(getResources())
                .addStateBitmap(normalStateBitmap)
                .addStateBitmap(pressedStateBitmap)
                .build();

        bitmapImageButton.setImageDrawable(srcDrawable);
        bitmapImageButton.setBackgroundResource(R.drawable.button_background);
    }

    private void setupTintImageButton() {
        ImageButton tintImageButton = (ImageButton) findViewById(R.id.tint_image_button);

        // Source state bitmaps.
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_button_tint_normal);
        StateBitmap normalStateBitmap = new StateBitmap.Builder()
                .setStates(new StateBitmap.State[]{})
                .setBitmap(bitmap)
                .setTintColor(Color.MAGENTA)
                .setTintMode(PorterDuff.Mode.SRC_IN) // Default is SRC_IN.
                .build();
        StateBitmap pressedStateBitmap = new StateBitmap.Builder()
                .setStates(new StateBitmap.State[]{StateBitmap.State.Pressed})
                .setBitmap(bitmap)
                .setTintColor(Color.YELLOW)
                .build();
        StateBitmap disableStateBitmap = new StateBitmap.Builder()
                .setStates(new StateBitmap.State[]{StateBitmap.State.Disabled})
                .setBitmap(bitmap)
                .setTintColor(Color.LTGRAY)
                .build();

        Drawable srcDrawable  = new StateListDrawableBuilder(getResources())
                .addStateBitmap(normalStateBitmap)
                .addStateBitmap(pressedStateBitmap)
                .addStateBitmap(disableStateBitmap)
                .build();

        tintImageButton.setImageDrawable(srcDrawable);
        tintImageButton.setBackgroundResource(R.drawable.button_background);
    }

    private void setupNinePatchImageButton() {
        ImageButton ninePatchImageButton = (ImageButton) findViewById(R.id.nine_patch_image_button);

        StateListDrawableBuilder stateListDrawableBuilder = new StateListDrawableBuilder(getResources());

        // Source state bitmaps.
        try {
            InputStream normalBitmapStream = getAssets().open("ic_button_nine_patch_normal.9.png");
            StateBitmap normalStateBitmap = new StateBitmap.Builder()
                    .setStates(new StateBitmap.State[]{})
                    .setBitmap(BitmapFactory.decodeStream(normalBitmapStream))
                    .setIsNinePatch(true)
                    .build();

            stateListDrawableBuilder.addStateBitmap(normalStateBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream pressedBitmapStream = getAssets().open("ic_button_nine_patch_pressed.9.png");
            StateBitmap pressedStateBitmap = new StateBitmap.Builder()
                    .setStates(new StateBitmap.State[]{StateBitmap.State.Pressed})
                    .setBitmap(BitmapFactory.decodeStream(pressedBitmapStream))
                    .setIsNinePatch(true)
                    .build();

            stateListDrawableBuilder.addStateBitmap(pressedStateBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Drawable srcDrawable  = stateListDrawableBuilder.build();

        ninePatchImageButton.setBackgroundDrawable(srcDrawable);
    }

}
