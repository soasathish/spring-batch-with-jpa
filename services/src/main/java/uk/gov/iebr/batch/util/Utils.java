package uk.gov.iebr.batch.util;


public class Utils {
    public static String 
    obtenirValeurParametre(String valeurActuelle, String parametre){
        if(parametreEstVide(parametre)) return "";  
        return "*".equals(parametre) ? valeurActuelle : parametre;
    }

    public static String 
    obtenirValeurParametre(Integer valeurActuelle, String parametre){
        if(parametreEstVide(parametre)) return "";  
        return "*".equals(parametre) ?
               valeurActuelle.toString() : parametre;
    }

    public static boolean
    parametreEstVide(String parametre){
        return parametre.startsWith("{") && parametre.endsWith("}");
    }
}
