package textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

import textdrawable.com.kumar.pankaj.edittextwithtextdrawable.R;

/**
 * Created by Sangeeta on 1/6/2017.
 */
public class TextDrawableEditText extends EditText {

    private String mPrefixText;
    private String mPostfixText;
    private ColorStateList mPrePostTextColor;
    private float mPrePostTextSize;

    public TextDrawableEditText(Context context) {
        this(context, null);
    }

    public TextDrawableEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
        readAttributes(context, attrs);
    }

    public TextDrawableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPrePostTextColor = getTextColors();
        readAttributes(context, attrs);
    }

    public void setPrefixText(String prefixText) {
        this.mPrefixText = prefixText;
        setupDrawables();
    }

    public void setPostfixText(String postfixText) {
        this.mPostfixText = postfixText;
        setupDrawables();
    }

    public void setPrePostTextSize(float mPrePostTextSize) {
        this.mPrePostTextSize = mPrePostTextSize;
    }

    public void setPrePostTextColor(int color) {
        mPrePostTextColor = ColorStateList.valueOf(color);
    }

    public void setPrePostTextColor(ColorStateList color) {
        mPrePostTextColor = color;
    }

    private void readAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TextDrawableEditText,
                0, 0);
        try {
            mPrefixText = a.getString(R.styleable.TextDrawableEditText_prefixText);
            mPostfixText = a.getString(R.styleable.TextDrawableEditText_postfixText);
            mPrePostTextSize = a.getDimension(R.styleable.TextDrawableEditText_prePostTextSize, -1);
            mPrePostTextColor = a.getColorStateList(R.styleable.TextDrawableEditText_prePostTextColor);
        } finally {
            a.recycle();
        }
        setupDrawables();
    }

    private void setupDrawables() {
        if (TextUtils.isEmpty(mPrefixText) && TextUtils.isEmpty(mPostfixText)) {
            // Nothing to do.
            return;
        }
        Drawable leftDrawable = null;
        Drawable rightDrawable = null;
        if (!TextUtils.isEmpty(mPrefixText)) {
            leftDrawable = new TextDrawable(mPrefixText);
        }

        if (!TextUtils.isEmpty(mPostfixText)) {
            rightDrawable = new TextDrawable(mPostfixText);
        }

        setCompoundDrawables(leftDrawable, null, rightDrawable, null);
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
            paint.setColor(mPrePostTextColor.getColorForState(getDrawableState(), 0));
            int lineBaseline = getLineBounds(0, null);
            canvas.drawText(mText, 0, canvas.getClipBounds().top + lineBaseline, paint);
        }

        @Override
        public void setAlpha(int alpha) {/* Not supported */}

        @Override
        public void setColorFilter(ColorFilter colorFilter) {/* Not supported */}

        @Override
        public int getOpacity() {
            return 1;
        }
    }
}
