package dev.liam_w.intellij.sasm_lang.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.FileViewProvider;
import dev.liam_w.intellij.sasm_lang.SasmFileType;
import dev.liam_w.intellij.sasm_lang.SasmLanguage;
import org.jetbrains.annotations.NotNull;

public class SasmFile extends PsiFileBase {

    public SasmFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SasmLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SasmFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Sasm script";
    }

    @NotNull
    @Override
    public TextRange getTextRangeInParent() {
        return new TextRange(0, getText().length());
    }
}
