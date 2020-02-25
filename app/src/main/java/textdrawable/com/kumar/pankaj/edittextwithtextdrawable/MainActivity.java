package textdrawable.com.kumar.pankaj.edittextwithtextdrawable;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view.PostfixedEditText;
import textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view.PrefixedEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrefixedEditText prefixedEditText = findViewById(R.id.prefix_drawable_edittext);
        PostfixedEditText postfixedEditText = findViewById(R.id.postfix_drawable_edittext);

        prefixedEditText.setPrefix("$");
        postfixedEditText.setPrefix("%");
    }
}
