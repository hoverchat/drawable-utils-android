drawable-utils-android
======================
Easy to use state Drawable(s).

Usage
-----
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
        
        mImageButton.setImageDrawable(srcDrawable);
