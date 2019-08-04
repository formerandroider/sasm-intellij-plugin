package dev.liam_w.intellij.sasm_lang;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperation;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperationArg;
import org.jetbrains.annotations.NotNull;

public class SasmReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SasmOperationArg.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext
                                                                         context) {
                        SasmOperationArg operationArg = (SasmOperationArg) element;
                        String value = operationArg.getArgumentLabel();
                        if (value != null) {
                            return new PsiReference[]{
                                    new SasmReference(element, new TextRange(0, value.length()))};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
    }
}
