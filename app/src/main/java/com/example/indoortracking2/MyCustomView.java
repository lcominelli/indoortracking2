package com.example.indoortracking2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

public class MyCustomView extends View {

    // onDraw commands
    boolean drawNewFootPrint = false;
    static boolean refreshFootPrints = false;
    static boolean clearCanva = false;

    public float left;
    public float top;
    public float right;
    public float bottom;

    public int cheight = 0;
    public int cwidth = 0;

    static FootPrint rootFootPrint= null;
    FootPrint lastFootPrint = null;

    public MyCustomView(Context context) {
        super(context);
    }


    public MyCustomView(Context context,
                        AttributeSet set) {
        super(context,set);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // update the canvas width
        cheight = canvas.getHeight();
        cwidth = canvas.getWidth();


        Paint paint = new Paint();
        paint.setColor(Color.BLACK);


        // continue OnDraw by drawing rectangles
        //canvas.drawRect(100,
        //        100,
        //        120,
        //        80,
        //        paint);
        FootPrint dummyfp = new FootPrint(100,
                                         100,
                                           120,
                                            80,
                                            null);
        dummyfp.drawFootPrint(canvas,paint);

        // clear if asked
        if (clearCanva == true) {
            canvas.drawColor(Color.LTGRAY);
            clearCanva = false;
        }

        // stop OnDraw here if foot prints draw false
        if ((drawNewFootPrint == false) &&
                (refreshFootPrints == false)) return;

        // continue OnDraw by drawing rectangles
        if (drawNewFootPrint == true) {
            FootPrint fp = new FootPrint(left,
                    top,
                    right,
                    bottom,
                    lastFootPrint);
            lastFootPrint = fp;
            if (rootFootPrint == null) rootFootPrint = fp;
            fp.drawFootPrint(canvas, paint);
            drawNewFootPrint = false;
        } else if (refreshFootPrints == true) {
            if (rootFootPrint != null) rootFootPrint.refreshFootPathDrawing(canvas, paint);
            refreshFootPrints = false;
        }
            //canvas.drawRect(left,
        //        top,
        //        right,
        //        bottom,
        //        paint
        //        );
    }

    public void resetView() {

        drawNewFootPrint = true;
        clearCanva = true;
        left = cwidth/2-10;
        top = cheight/2+10;
        right= cwidth/2+10;
        bottom = cheight/2-10;
        invalidate();
    }

    public void refreshView() {
        //resetView();
        invalidate();
    }

    public void translateView(float xoffset, float yoffset) {
        clearCanva = true;
        refreshFootPrints = true;
        if (rootFootPrint!=null) rootFootPrint.translateFootPath(xoffset,yoffset);
        invalidate();
    }


}
