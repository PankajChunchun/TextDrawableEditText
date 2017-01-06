package textdrawable.com.kumar.pankaj.edittextwithtextdrawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view.PostfixedEditText;
import textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view.PrefixedEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*PrefixedEditText prefixedEditText = (PrefixedEditText) findViewById(R.id.editText);
        PostfixedEditText postfixedEditText = (PostfixedEditText) findViewById(R.id.editText2);

        prefixedEditText.setPrefix("$");
        postfixedEditText.setPrefix("%");*/
    }
}
