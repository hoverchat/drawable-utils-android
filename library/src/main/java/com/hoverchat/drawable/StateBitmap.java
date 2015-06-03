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

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Mostafa Gazar <mmegazar@gmail.com>
 */
public class StateBitmap {

    public enum State {

        Enabled(android.R.attr.state_enabled),
        Disabled(-android.R.attr.state_enabled),
        Checked(android.R.attr.state_checked),
        Unchecked(-android.R.attr.state_checked),
        Focused(android.R.attr.state_focused),
        Pressed(android.R.attr.state_pressed),
        Activated(android.R.attr.state_activated);

        int attr;

        State(int attr) {
            this.attr = attr;
        }

    }

    private State[] mStates;

    private boolean mIsNinePatch;
    private Bitmap mBitmap;
    private Integer mTintColor;
    private PorterDuff.Mode mTintMode;

    private StateBitmap(Builder builder) {
        mStates = builder.mStates;

        mIsNinePatch = builder.mIsNinePatch;
        mBitmap = builder.mBitmap;
        mTintColor = builder.mTintColor;
        mTintMode = builder.mTintMode;
    }

    public State[] getStates() {
        return mStates;
    }

    public boolean isNinePatch() {
        return mIsNinePatch;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public Integer getTintColor() {
        return mTintColor;
    }

    public PorterDuff.Mode getTintMode() {
        return mTintMode;
    }

    public static class Builder {

        private State[] mStates;

        private boolean mIsNinePatch;
        private Bitmap mBitmap;
        private Integer mTintColor;
        private PorterDuff.Mode mTintMode;

        public Builder() {
            // Set default tint mode.
            mTintMode = PorterDuff.Mode.SRC_IN;
        }

        public Builder setStates(@NonNull State[] states) {
            mStates = states;
            return this;
        }

        public Builder setIsNinePatch(boolean isNinePatch) {
            mIsNinePatch = isNinePatch;
            return this;
        }

        public Builder setBitmap(@NonNull Bitmap bitmap) {
            mBitmap = bitmap;
            return this;
        }

        public Builder setTintColor(@Nullable Integer tintColor) {
            mTintColor = tintColor;
            return this;
        }

        public Builder setTintMode(PorterDuff.Mode tintMode) {
            mTintMode = tintMode;
            return this;
        }

        public StateBitmap build() {
            return new StateBitmap(this);
        }

    }

}
