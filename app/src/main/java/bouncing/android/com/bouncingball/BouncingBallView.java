package bouncing.android.com.bouncingball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dale on 8/3/2016.
 */
public class BouncingBallView extends View {
    private Ball ball;
    private Box box;
    private StatusMessage statusMsg;

    private float previousX;
    private float previousY;

    public BouncingBallView(Context context) {
        super(context);

        box = new Box(0xff00003f);          // ARGB
        ball = new Ball(Color.GREEN);
        statusMsg = new StatusMessage(Color.CYAN);

        this.setFocusableInTouchMode(true);
    }

    // Called back to draw the view. Also called by invalidate()
    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the components
        box.draw(canvas);
        ball.draw(canvas);
        statusMsg.draw(canvas);

        // update the position of the ball, including collision detection and reaction.
        ball.moveWithCollisionDetection(box);
        statusMsg.update(ball);

        // Delay
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        invalidate();   // Force a re-draw
    }

    // Called back when the view is first created or its size changes.
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        box.set(0,0,w,h);
        previousX = 0.0f;
        previousY = 0.0f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();
        float deltaX, deltaY;
        float scalingFactor = 5.0f / ((box.xMax > box.yMax) ? box.yMax : box.xMax);
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                // modify rotational angles according to movement
                deltaX = currentX - previousX;
                deltaY = currentY - previousY;
                ball.speedX += deltaX * scalingFactor;
                ball.speedY += deltaY * scalingFactor;
        }

        // Save current x,y
        previousX = currentX;
        previousY = currentY;

        return true;
    }
}
