package dev.liam_w.intellij.sasm_lang;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import dev.liam_w.intellij.sasm_lang.psi.SasmNamedElement;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperation;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperationArg;
import dev.liam_w.intellij.sasm_lang.psi.SasmTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SasmFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new SasmLexerAdapter(),
                TokenSet.create(SasmTypes.OPERATION),
                TokenSet.create(SasmTypes.COMMENT),
                TokenSet.EMPTY);
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof SasmNamedElement;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {

        if (element instanceof SasmOperation) {
            return "sasm operation label";
        }

        return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        String name = ((SasmNamedElement) element).getName();

        if (name != null) {
            return name;
        } else {
            return "";
        }
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        if (element instanceof SasmOperation) {
            return element.getText();
        } else if (element instanceof SasmOperationArg) {
            return element.getParent().getText();
        } else {
            return "";
        }
    }
}
