package com.example.indoortracking2;

import android.graphics.Canvas;
import android.graphics.Paint;

public class FootPrint {

    public float left;
    public float top;
    public float right;
    public float bottom;

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

    public void refreshFootPrint (Canvas canvas,
                                  Paint paint,
                                  float xoffset,
                                  float yoffset) {
        canvas.drawRect(left+xoffset,
                top+yoffset,
                right+xoffset,
                bottom+yoffset,
                paint
        );


    }

}
