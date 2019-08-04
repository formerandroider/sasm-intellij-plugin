package dev.liam_w.intellij.sasm_lang;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SasmReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    private String label;

    public SasmReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        label = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<SasmOperation> properties = SasmUtil.findOperations(project, label);
        List<ResolveResult> results = new ArrayList<>();
        for (SasmOperation operation : properties) {
            results.add(new PsiElementResolveResult(operation));
        }
        return results.toArray(new ResolveResult[0]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<SasmOperation> operations = SasmUtil.findOperations(project);
        List<LookupElement> variants = new ArrayList<>();
        for (final SasmOperation operation : operations) {
            if (operation.getLabel() != null && operation.getLabel().length() > 0) {
                variants.add(LookupElementBuilder.create(operation).
                        withIcon(SasmIcons.FILE)
                );
            }
        }
        return variants.toArray();
    }
}
