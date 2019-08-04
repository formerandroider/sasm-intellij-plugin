package dev.liam_w.intellij.sasm_lang.psi;

import com.intellij.psi.tree.IElementType;
import dev.liam_w.intellij.sasm_lang.SasmLanguage;
import org.jetbrains.annotations.NotNull;

public class SasmElementType extends IElementType {
    public SasmElementType(@NotNull String debugName) {
        super(debugName, SasmLanguage.INSTANCE);
    }
}
