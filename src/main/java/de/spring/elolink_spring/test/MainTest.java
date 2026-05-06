package de.spring.elolink_spring.test;

import de.spring.elolink_spring.translation.LangHandler;
import de.spring.elolink_spring.translation.TranslationParts;

import java.util.Locale;

public class MainTest {
    public static void main(String[] args) {
        LangHandler l = new LangHandler(Locale.US);
        System.out.println(l.getText(TranslationParts.EMAIL, "verifyYourAccount"));
    }
}
