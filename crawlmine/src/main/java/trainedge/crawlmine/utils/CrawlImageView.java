package trainedge.crawlmine.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

import com.google.api.services.vision.v1.model.Vertex;

import java.util.List;

import static android.R.id.list;

public class CrawlImageView extends android.support.v7.widget.AppCompatImageView {

    private Canvas canvas;
    private Paint paint;

    public CrawlImageView(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public CrawlImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CrawlImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void drawTextAndRectangle(String text, float leftCorner, float topCorner, float rightCorner, float bottomCorner, int color) {
        Canvas canvas = new Canvas(((BitmapDrawable) getDrawable()).getBitmap());
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(5);
        paint.setAlpha(1);
        canvas.drawRect(leftCorner, topCorner, rightCorner, bottomCorner, paint);
        canvas.drawText(text, (int) leftCorner, (int) topCorner, rightCorner, bottomCorner, paint);

    }

    public void drawRectangle(List<Vertex> vertices) {
        canvas = new Canvas(((BitmapDrawable) getDrawable()).getBitmap());
        float[] convert= new float[vertices.size()];
        canvas.drawPoints(convert,paint);
        //refresh();


    }

    public void refresh() {


        invalidate();
    }
}
