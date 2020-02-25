# TextDrawableEditText
Sometime we need to use EditTexts which would have fixed prefix text or postfix text.
Like a EditText with $ in starts of it always and can not be deleted by user.
Before this library we used to add an ImageView and add and image for required fixed text (like $ or %). In this way we face few problems like

1. Base alignment of text and Image
2. Image required for the text

This library remove those problems and you do not worry about alignment and images at-least (and obviously it faster than ImageView solution)


# How to use

<code>
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
</code>

You can set pre/ post texts from xml or java/ kotlin too. For xml you can look below attributes in above example code

<code>
<textdrawable.com.kumar.pankaj.edittextwithtextdrawable.view.TextDrawableEditText
        custom:postfixText="%"
        custom:prePostTextColor="@color/colorPrimary"
        custom:prePostTextSize="16sp"
        custom:prefixText="$" />
</code>

Result you get is
