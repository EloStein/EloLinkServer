package de.spring.elolink_spring.translation;

import com.sun.java.accessibility.util.Translator;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LangHandler {
    Locale locale;

    public LangHandler(Locale locale){
        this.locale = locale;
    }

    public String getText(TranslationParts part, String key){
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(getBundleLocation(part), this.locale);
        } catch (MissingResourceException e){
            return "#MISSING RESOURCE BUNDLE/ ".concat(e.toString());
        }
        try {
            return bundle.getString(String.valueOf(key));
        } catch (MissingResourceException e){
            return "#MISSING TRANSLATION KEY/ ".concat(e.toString());
        }

    }

    public String getText(String path, TranslationParts part, String key){
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(getBundleLocation(path, part), this.locale);
        } catch (MissingResourceException e){
            return "#MISSING RESOURCE BUNDLE/ ".concat(e.toString());
        }
        try {
            return bundle.getString(String.valueOf(key));
        } catch (MissingResourceException e){
            return "#MISSING TRANSLATION KEY/ ".concat(e.toString());
        }
    }

    public String getBundleLocation(TranslationParts part){
        return getBundleLocation("translation.", part);
    }

    public String getBundleLocation(String path, TranslationParts part){
        return path.concat(part.toString().toLowerCase());
    }

}
