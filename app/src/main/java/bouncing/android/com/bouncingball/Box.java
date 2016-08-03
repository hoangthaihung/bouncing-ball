package bouncing.android.com.bouncingball;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by dale on 8/3/2016.
 */
public class Box {
    int xMin, xMax, yMin, yMax;
    private Paint paint;        // paint style and color
    private Rect bounds;

    public Box(int color) {
        paint = new Paint();
        paint.setColor(color);
        bounds = new Rect();
    }

    public void set(int x, int y, int width, int height) {
        this.xMin = x;
        this.xMax = x + width - 1;
        this.yMin = y;
        this.yMax = y + height - 1;
        // The box's bounds do not change unless the view's size changes
        bounds.set(xMin, yMin, xMax, yMax);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(bounds,paint);
    }
}
