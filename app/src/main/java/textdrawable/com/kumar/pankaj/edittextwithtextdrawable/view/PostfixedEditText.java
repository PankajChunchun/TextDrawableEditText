package textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

// Custom EditText that displays a fixed prefix in line with the text.
// The trick here is to draw the prefix as a drawable and attach it via setCompoundDrawables().
/**
 * This class is designed to show read-only prefix text in {@link android.widget.EditText}.
 *
 * <pre>
 *   - To set postfix text use {@link PostfixedEditText#setPrefix(String)}
 *   - To set text color of postfix text use {@link PostfixedEditText#setPrefixTextColor(int)} or
 *   {@link PostfixedEditText#setPrefixTextColor(ColorStateList)}
 *
 * </pre>
 * <p>
 * Created by Pankaj on 1/6/2017.
 */
public class PostfixedEditText extends AppCompatEditText {

    private ColorStateList mPrefixTextColor;

    public PostfixedEditText(Context context) {
        this(context, null);
    }

    public PostfixedEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public PostfixedEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPrefixTextColor = getTextColors();
    }

    public void setPrefix(String prefix) {
        setCompoundDrawables(null, null, new TextDrawable(prefix), null);
    }

    public void setPrefixTextColor(int color) {
        mPrefixTextColor = ColorStateList.valueOf(color);
    }

    public void setPrefixTextColor(ColorStateList color) {
        mPrefixTextColor = color;
    }

    private class TextDrawable extends Drawable {
        private String mText = "";

        public TextDrawable(String text) {
            mText = text;
            setBounds(0, 0, (int) getPaint().measureText(mText) + 2, (int) getTextSize());
        }

        @Override
        public void draw(Canvas canvas) {
            Paint paint = getPaint();
            paint.setColor(mPrefixTextColor.getColorForState(getDrawableState(), 0));
            int lineBaseline = getLineBounds(0, null);
            canvas.drawText(mText, 0, canvas.getClipBounds().top + lineBaseline, paint);
        }

        @Override
        public void setAlpha(int alpha) {/* Not supported */}

        @Override
        public void setColorFilter(ColorFilter colorFilter) {/* Not supported */}

        @Override
        public int getOpacity() {
            return PixelFormat.RGBA_8888;
        }
    }
}