# TextDrawableEditText

Sometime we need to use EditTexts which would have fixed prefix text or postfix text.
Like a EditText with $ in starts of it always and can not be deleted by user.
Before this library we used to add an ImageView and add and image for required fixed text (like $ or %). In this way we face few problems like

* Base alignment of text and Image
* Image required for the text

This library remove those problems and you do not worry about alignment and images at-least (and obviously it faster than ImageView solution), and each view is subclass of AppCompatEditText, you can apply any customization supported by Android for AppCompatEditText.


## How to use

##### textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view.TextDrawableEditText

```
<textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view.TextDrawableEditText
        android:id="@+id/pre_post_drawable_edittext"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="36dp"
        android:ems="10"
        android:hint="Showing Pre/ Post text"
        android:inputType="textPersonName"
        custom:postfixText="%"
        custom:prePostTextColor="@color/colorPrimary"
        custom:prePostTextSize="50sp"
        custom:prefixText="$" />
```

You can set pre/ post texts from xml or java/ kotlin too. For xml you can look below attributes in above example code

```
<textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view.TextDrawableEditText
        custom:postfixText="%"
        custom:prePostTextColor="@color/colorPrimary"
        custom:prePostTextSize="16sp"
        custom:prefixText="$" />
```

Result you get is

| Empty View        | Filled With Some Text           |
| ----------------- |:-------------:|
| <img src="https://github.com/PankajChunchun/TextDrawableEditText/blob/master/images/pre_post_empty.png" width="250" height="50">      | <img src="https://github.com/PankajChunchun/TextDrawableEditText/blob/master/images/pre_post.png" width="250" height="60"> |

##### textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view.PrefixedEditText

```
prefixedEditText.setPrefix("$");
```

Result you get is

| Empty View        | Filled With Some Text           |
| ----------------- |:-------------:|
| <img src="https://github.com/PankajChunchun/TextDrawableEditText/blob/master/images/prefix_empty_example.png" width="250" height="60">      | <img src="https://github.com/PankajChunchun/TextDrawableEditText/blob/master/images/prefix_example.png" width="250" height="60"> |


##### textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view.PostfixedEditText

```
postfixedEditText.setPrefix("$");
```

Result you get is

| Empty View        | Filled With Some Text           |
| ----------------- |:-------------:|
| <img src="https://github.com/PankajChunchun/TextDrawableEditText/blob/master/images/postfix_empty_example.png" width="250" height="60">      | <img src="https://github.com/PankajChunchun/TextDrawableEditText/blob/master/images/postfix_example.png" width="250" height="60"> |
