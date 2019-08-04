package dev.liam_w.intellij.sasm_lang;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperation;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperationArg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SasmFoldingBuilder extends FoldingBuilderEx {
    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        FoldingGroup group = FoldingGroup.newGroup("sasm");

        List<FoldingDescriptor> descriptors = new ArrayList<>();
        Collection<SasmOperationArg> operationArgs =
                PsiTreeUtil.findChildrenOfType(root, SasmOperationArg.class);
        for (final SasmOperationArg operationArg : operationArgs) {

            String label = operationArg.getArgumentLabel();

            if (label != null) {
                Project project = operationArg.getProject();
                final List<SasmOperation> operations = SasmUtil.findOperations(project, label, "DAT");
                if (operations.size() == 1) {
                    descriptors.add(new FoldingDescriptor(operationArg.getNode(),
                            new TextRange(operationArg.getTextRange().getStartOffset(),
                                    operationArg.getTextRange().getEndOffset()),
                            group) {
                        @Nullable
                        @Override
                        public String getPlaceholderText() {
                            // IMPORTANT: keys can come with no values, so a test for null is needed
                            // IMPORTANT: Convert embedded \n to backslash n, so that the string will look like it has LF embedded
                            // in it and embedded " to escaped "
                            SasmOperationArg arg = operations.get(0).getOperationArg();
                            if (arg != null) {
                                return arg.getText();
                            }

                            return "";
                        }
                    });
                }
            }
        }
        return descriptors.toArray(new FoldingDescriptor[0]);
    }

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        return "...";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return true;
    }
}
