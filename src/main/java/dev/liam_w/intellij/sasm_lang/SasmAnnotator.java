package dev.liam_w.intellij.sasm_lang;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperation;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperationArg;
import dev.liam_w.intellij.sasm_lang.psi.impl.SasmOperationImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SasmAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof SasmOperationArg) {
            SasmOperationArg operationArg = (SasmOperationArg) element;
            String argumentLabel = operationArg.getArgumentLabel();

            if (argumentLabel != null) {
                Project project = element.getProject();
                List<SasmOperation> operations = SasmUtil.findOperations(project, argumentLabel);
                if (operations.size() == 0) {
                    TextRange range = new TextRange(element.getTextRange().getStartOffset(),
                            element.getTextRange().getEndOffset());
                    holder.createErrorAnnotation(range, "Unresolved label").registerFix(new CreateDataConstantQuickFix(argumentLabel));
                }
            }
        }
    }
}
