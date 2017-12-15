package dsbmobile.com.app.database_login.utils;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by giova on 11/12/2017.
 */

public class SupportMethods {
    //block character text
    public final static String BLOCKCHARACTERSETPASSWORD = "~`!@#$%^&*()_-\\|/?\"{}[],.;'=+";
    public final static String BLOCKABC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~`!@#$%^&*()_-\\|/?{}[],.;'=+\"";

    public SupportMethods(){}

    public static InputFilter isValid(final String specialCharacter){
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source != null && specialCharacter.contains("" + source)){
                    return "";
                }
                return null;
            }
        };
        return filter;
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
