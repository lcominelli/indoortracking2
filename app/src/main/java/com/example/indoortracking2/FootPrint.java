package com.example.indoortracking2;

import android.graphics.Canvas;
import android.graphics.Paint;

public class FootPrint {

    public float left;
    public float top;
    public float right;
    public float bottom;

    public float xoffset;
    public float yoffset;

    public FootPrint nextFootPrint = null;

    public FootPrint(float left,
                     float top,
                     float right,
                     float bottom,
                     FootPrint lastFootPrint) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        if (lastFootPrint != null) lastFootPrint.nextFootPrint=this;
    }

    public void drawFootPrint(Canvas canvas,
                              Paint paint) {
        canvas.drawRect(left,
                top,
                right,
                bottom,
                paint
        );
    }

    public void refreshFootPathDrawing (Canvas canvas,
                                  Paint paint) {
        canvas.drawRect(left+xoffset,
                top+yoffset,
                right+xoffset,
                bottom+yoffset,
                paint
        );
        if (nextFootPrint != null) nextFootPrint.refreshFootPathDrawing(canvas, paint);
    }

    public void translateFootPath(float xoffset, float yoffset) {
        this.xoffset = xoffset;
        this.yoffset = yoffset;
        if (nextFootPrint != null) nextFootPrint.translateFootPath(xoffset,yoffset);
    }


}
