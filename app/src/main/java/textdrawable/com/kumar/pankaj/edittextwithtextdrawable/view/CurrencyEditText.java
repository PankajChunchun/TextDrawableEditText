package textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;
/**
 * Created by Sangeeta on 1/14/2017.
 */
public class CurrencyEditText extends EditText {
    private static final int NO_LIMIT = -1;
    private static final int TYPE_CURRENCY = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
    private static final NumberFormat US_CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);
    private String mLastEligibleCurrency = "";
    private int mLastEligibleCursorPosition = 0;
    private StringBuilder mBuilder;
    private int mDigitsBeforeDecimal = NO_LIMIT;

    public CurrencyEditText(Context context) {
        this(context, null);
    }

    public CurrencyEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
        // readAttributes(context, attrs);
    }

    public CurrencyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // readAttributes(context, attrs);
        init(context, attrs, defStyle);
    }


    private void init(Context context, AttributeSet attrs, int defStyle) {
        this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        this.addTextChangedListener(onTextChangeListener);
        this.mBuilder = new StringBuilder();
    }

    private TextWatcher onTextChangeListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0)
                return;

            removeTextChangedListener(this);

            // 1. Remove commas from currency text
            // 2. Match it with Matcher
            // 3. If Matched
            // >>>> 3.0. Format EditText's value with Currency formatter
            // >>>> 3.1. Cache mLastEligibleCurrency with current text from EditText
            // >>>> 3.2. Cache mLastEligibleCursorPosition with current cursor position into EditText
            // >>>> 3.3. Replace EditText's value with result of step 3.0
            // 4. If not Matched
            // >>>> 4.0. Format EditText's value with Currency formatter
            // >>>> 4.1. Cache mLastEligibleCurrency with current text from EditText
            // >>>> 4.2. Cache mLastEligibleCursorPosition with current cursor position into EditText
            // >>>> 4.3. Replace EditText's value with result of step 3.0

            mLastEligibleCursorPosition = getSelectionStart();
            String tmpText = getText().toString();
            if (mDigitsBeforeDecimal != NO_LIMIT) {
                tmpText = tmpText.replaceAll(",", "");
            }

            if (isValidInput(tmpText)) {
                mLastEligibleCurrency = getCurrencyFormattedText(tmpText);
                mLastEligibleCursorPosition = before + 1;
            }

/*
            mBuilder.delete(0, mBuilder.length()-1);
            mBuilder.append(getText().toString().replaceAll(",", ""));



            *//***
             * Clear input to get clean text before format
             * '\u0020' is empty character
             *//*
            String text = s.toString();
            text = text.replace(groupDivider, '\u0020').replace(monetaryDivider, '\u0020')
                    .replace(".", "").replace(" ", "")
                    .replace(currencySymbol, "").trim();
            try {
                text = format(text);
            } catch (ParseException e) {
                Log.e(getClass().getCanonicalName(), e.getMessage());
            }
            setText(text);
            setSelection(text.length());*/


            setText(mLastEligibleCurrency);
            setSelection(mLastEligibleCursorPosition);
            addTextChangedListener(this);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private String getCurrencyFormattedText(String currency) {
        String str = currency.trim().replace("$", "").replaceAll(",", "");
        if (str.length() == 0) {
            return "";
        } else if (".".equals(str)) {
            return ".";
        }
        return US_CURRENCY_FORMATTER.format(Double.parseDouble(str)).replace("$", "");
    }

    /**
     * Validate input if EditText has correct input.
     * @param input - It will include commas also to check length of digits before DOT. So in case of restricting digits before DOT,
     * remove comma from input before calling this method.
     * @return <code>true</code> if input is valid, otherwise <code>false</code>.
     */
    private boolean isValidInput(String input) {
        System.out.print(input + " >>> ");

        if (input == null || input.isEmpty()) {
            return true; // TODO Check if can be false
        }

        String[] splits = input.split("\\.");

        // Check existence of more than 1 DOT
        if (splits.length > 2) {
            // More than 1 decimal can not be allowed
            return false;
        }

        // 1. If only DOT present into input, splits will be EMPTY
        // 2. If any digit does exists after or before DOT, splits size will be 2
        // 3. If DOT does not exists into input, splits size will be 1

        if (splits.length == 0) {
            // Only DOT exists, allow it.
            return true;
        }

        boolean isValidInput = true;

        // Validate FRACTION part.
        if (splits.length == 2) {
            // Check if maximum 2 digits exist after DOT
            isValidInput = splits[1].length() <= 2 ? true : false;
        }

        // Validate INTEGERS part if required
        if (isValidInput && mDigitsBeforeDecimal != NO_LIMIT) {
            // splits.length >= 1 will always be true, either DOT exists or not.
            if (splits.length >= 1) {
                return splits[0].replace(",", "").length() <= mDigitsBeforeDecimal;
            }
        }

        // Return isValidInput, in case of INTEGER part was not validated.
        return isValidInput;
    }
}