package dev.liam_w.intellij.sasm_lang;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import dev.liam_w.intellij.sasm_lang.psi.SasmNamedElement;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperation;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperationArg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SasmRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, @Nullable PsiElement context) {
        return element instanceof SasmNamedElement;
    }

    @Override
    public boolean isSafeDeleteAvailable(@NotNull PsiElement element) {
        return element instanceof SasmOperation || element instanceof SasmOperationArg;
    }
}
