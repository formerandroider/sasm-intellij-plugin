package dev.liam_w.intellij.sasm_lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.util.IncorrectOperationException;
import dev.liam_w.intellij.sasm_lang.SasmIcons;
import dev.liam_w.intellij.sasm_lang.psi.SasmElementFactory;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperation;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperationArg;
import dev.liam_w.intellij.sasm_lang.psi.SasmTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SasmPsiImplUtil {

    public static String getLabel(SasmOperation element) {
        ASTNode labelNode = element.getNode().findChildByType(SasmTypes.LABEL);
        if (labelNode != null) {
            return labelNode.getText();
        } else {
            return null;
        }
    }

    public static String getInstruction(SasmOperation element) {
        ASTNode instructionNode = element.getNode().findChildByType(SasmTypes.OPERATION_IDENTIFIER);
        if (instructionNode != null) {
            return instructionNode.getText();
        } else {
            return null;
        }
    }

    @Nullable
    public static String getArgumentLabel(@NotNull SasmOperationArg element) {
        ASTNode argumentLabel = element.getNode().findChildByType(SasmTypes.IDENTIFIER);
        if (argumentLabel != null) {
            return argumentLabel.getText();
        } else {
            return null;
        }
    }

    public static String getName(SasmOperation element) {
        return getLabel(element);
    }

    public static PsiElement setName(SasmOperation element, String newName) {
        ASTNode labelNode = element.getNode().findChildByType(SasmTypes.LABEL);
        if (labelNode != null) {
            SasmOperation operation = SasmElementFactory.createOperation(element.getProject(), newName);
            ASTNode newLabelNode = operation.getFirstChild().getNode();
            element.getNode().replaceChild(labelNode, newLabelNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(SasmOperation element) {
        ASTNode labelNode = element.getNode().findChildByType(SasmTypes.LABEL);
        if (labelNode != null) {
            return labelNode.getPsi();
        } else {
            return null;
        }
    }

    public static String getName(SasmOperationArg element) {
        return getArgumentLabel(element);
    }

    public static PsiElement setName(SasmOperationArg element, String newName) {
        System.out.printf("SOA: %s\n", newName);
        ASTNode identifierNode = element.getNode().findChildByType(SasmTypes.IDENTIFIER);
        if (identifierNode != null) {
            SasmOperationArg operation = SasmElementFactory.createOperationArg(element.getProject(), newName);
            ASTNode newIdentifierNode = operation.getFirstChild().getNode();
            element.getNode().replaceChild(identifierNode, newIdentifierNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(SasmOperationArg element) {
        ASTNode identifierNode = element.getNode().findChildByType(SasmTypes.IDENTIFIER);
        if (identifierNode != null) {
            return identifierNode.getPsi();
        } else {
            return null;
        }
    }

    public static ItemPresentation getPresentation(final SasmOperation element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getLabel();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return element.getContainingFile().getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return SasmIcons.FILE;
            }
        };
    }
}
