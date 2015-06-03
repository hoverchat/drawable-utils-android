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

package com.hoverchat.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mostafa Gazar <mmegazar@gmail.com>
 */
public class StateListDrawableBuilder {

    private Resources mRes;

    private List<StateBitmap> mStateDrawables;

    public StateListDrawableBuilder(Resources res) {
        mRes = res;

        mStateDrawables = new ArrayList<>();
    }

    public StateListDrawableBuilder addStateBitmap(StateBitmap stateBitmap) {
        if (stateBitmap == null) {
            return this;
        }

        if (stateBitmap.getStates() == null || stateBitmap.getStates().length == 0) {
            mStateDrawables.add(stateBitmap);
        } else {
            mStateDrawables.add(0, stateBitmap);
        }

        return this;
    }

    public StateListDrawable build() {
        StateListDrawable stateListDrawable = new StateListDrawable();

        Drawable drawable;
        StateBitmap.State[] states;
        int[] statesAttr;
        Integer tintColor;
        for (StateBitmap stateBitmap : mStateDrawables) {
            drawable = getDrawable(stateBitmap.getBitmap(), stateBitmap.isNinePatch());

            tintColor = stateBitmap.getTintColor();
            if (tintColor != null) {
                drawable.setColorFilter(tintColor, stateBitmap.getTintMode());
            }

            states = stateBitmap.getStates();
            if (states == null) {
                statesAttr = new int[]{};
            } else {
                statesAttr = new int[states.length];

                int i = 0;
                for (StateBitmap.State state : states) {
                    statesAttr[i++] = state.attr;
                }
            }

            stateListDrawable.addState(statesAttr, drawable);
        }

        return stateListDrawable;
    }

    private Drawable getDrawable(Bitmap bitmap, boolean isNinePatch) {
        if (bitmap == null) {
            return null;
        }

        if (isNinePatch) {
            DisplayMetrics displayMetrics = mRes.getDisplayMetrics();
            float dpToPx = displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT;

            byte[] chunk = bitmap.getNinePatchChunk();
            NinePatchChunk ninePatchChunk = NinePatchChunk.deserialize(chunk, dpToPx);
            Rect padding = ninePatchChunk != null ? ninePatchChunk.mPadding: new Rect();

            return new NinePatchDrawable(mRes, bitmap, chunk, padding, null);
        } else {
            return new BitmapDrawable(mRes, bitmap);
        }
    }

}
