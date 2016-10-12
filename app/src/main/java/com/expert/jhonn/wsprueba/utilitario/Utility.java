package com.expert.jhonn.wsprueba.utilitario;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Jhonn on 12/10/2016.
 */
public class Utility {

    public static boolean validarLetra(String  cadena){
        Pattern pattern=Pattern.compile("%[a-zA-Z ]+$");
        return pattern.matcher(cadena).matches() || cadena.length()>30;
    }

    public static boolean validarNro(String cadNro){
        return Patterns.PHONE.matcher(cadNro).matches();
    }

    public static boolean validarEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
