package com.hoverchat.drawable;

import android.graphics.Rect;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

// http://stackoverflow.com/questions/11065996/ninepatchdrawable-does-not-get-padding-from-chunk
public class NinePatchChunk {

    public final Rect mPadding = new Rect();

    public int mDivX[];
    public int mDivY[];
    public int mColor[];

    private static void readIntArray(final int[] data, final ByteBuffer buffer) {
        for (int i = 0, n = data.length; i < n; ++i)
            data[i] = buffer.getInt();
    }

    private static void checkDivCount(final int length) {
        if (length == 0 || (length & 0x01) != 0)
            throw new RuntimeException("invalid nine-patch: " + length);
    }

    public static NinePatchChunk deserialize(final byte[] data, float factor) {
        final ByteBuffer byteBuffer =
            ByteBuffer.wrap(data).order(ByteOrder.nativeOrder());

        if (byteBuffer.get() == 0) return null; // is not serialized

        final NinePatchChunk chunk = new NinePatchChunk();
        chunk.mDivX = new int[byteBuffer.get()];
        chunk.mDivY = new int[byteBuffer.get()];
        chunk.mColor = new int[byteBuffer.get()];

        checkDivCount(chunk.mDivX.length);
        checkDivCount(chunk.mDivY.length);

        // skip 8 bytes
        byteBuffer.getInt();
        byteBuffer.getInt();

        chunk.mPadding.left   = (int) (byteBuffer.getInt() * factor);
        chunk.mPadding.right  = (int) (byteBuffer.getInt() * factor);
        chunk.mPadding.top    = (int) (byteBuffer.getInt() * factor);
        chunk.mPadding.bottom = (int) (byteBuffer.getInt() * factor);

        // skip 4 bytes
        byteBuffer.getInt();

        readIntArray(chunk.mDivX, byteBuffer);
        readIntArray(chunk.mDivY, byteBuffer);
        readIntArray(chunk.mColor, byteBuffer);

        return chunk;
    }
}