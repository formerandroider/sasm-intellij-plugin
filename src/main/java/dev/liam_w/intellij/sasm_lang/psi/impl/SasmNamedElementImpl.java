package dev.liam_w.intellij.sasm_lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.intellij.util.IncorrectOperationException;
import dev.liam_w.intellij.sasm_lang.psi.SasmNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class SasmNamedElementImpl extends ASTWrapperPsiElement implements SasmNamedElement {
    public SasmNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public void delete() throws IncorrectOperationException {
        final ASTNode parentNode = getParent().getNode();
        assert parentNode != null;

        ASTNode node = getNode();
        ASTNode prev = node.getTreePrev();
        ASTNode next = node.getTreeNext();
        parentNode.removeChild(node);
        if ((prev == null || prev.getElementType() == TokenType.WHITE_SPACE) && next != null &&
                next.getElementType() == TokenType.WHITE_SPACE) {
            parentNode.removeChild(next);
        }
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this);
    }
}
