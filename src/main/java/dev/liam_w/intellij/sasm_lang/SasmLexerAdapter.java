package dev.liam_w.intellij.sasm_lang;

import com.intellij.lexer.FlexAdapter;

public class SasmLexerAdapter extends FlexAdapter {
    public SasmLexerAdapter() {
        super(new SasmLexer(null));
    }
}
