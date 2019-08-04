package dev.liam_w.intellij.sasm_lang;

import com.intellij.lang.Language;

public class SasmLanguage extends Language {
    public static final Language INSTANCE = new SasmLanguage();

    private SasmLanguage() {
        super("sasm");
    }
}
