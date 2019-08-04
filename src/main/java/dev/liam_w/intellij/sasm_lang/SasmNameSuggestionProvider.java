package dev.liam_w.intellij.sasm_lang;

import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.SuggestedNameInfo;
import com.intellij.refactoring.rename.NameSuggestionProvider;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperation;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class SasmNameSuggestionProvider implements NameSuggestionProvider {
    @Nullable
    @Override
    public SuggestedNameInfo getSuggestedNames(PsiElement element, @Nullable PsiElement nameSuggestionContext, Set<String> result) {

        System.out.printf("Class: %s", element.getParent().getClass().getName());

        if (element.getParent() instanceof SasmOperation) {
            SasmOperation operation = (SasmOperation) element.getParent();

            if (operation.getInstruction().equals("DAT") && operation.getOperationArg() != null) {
                result.add(operation.getOperationArg().getText());
            } else {
                System.out.printf("Instruction: %s", operation.getInstruction());
            }
        } else {
            System.out.printf("Class: %s", element.getParent().getClass().getName());
        }

        return SuggestedNameInfo.NULL_INFO;
    }
}
