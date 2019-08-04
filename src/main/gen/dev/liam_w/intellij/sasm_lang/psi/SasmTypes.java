// This is a generated file. Not intended for manual editing.
package dev.liam_w.intellij.sasm_lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import dev.liam_w.intellij.sasm_lang.psi.impl.*;

public interface SasmTypes {

  IElementType OPERATION = new SasmElementType("OPERATION");
  IElementType OPERATION_ARG = new SasmElementType("OPERATION_ARG");

  IElementType COMMENT = new SasmTokenType("COMMENT");
  IElementType CRLF = new SasmTokenType("CRLF");
  IElementType IDENTIFIER = new SasmTokenType("IDENTIFIER");
  IElementType LABEL = new SasmTokenType("LABEL");
  IElementType NUM = new SasmTokenType("NUM");
  IElementType OPERATION_IDENTIFIER = new SasmTokenType("OPERATION_IDENTIFIER");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == OPERATION) {
        return new SasmOperationImpl(node);
      }
      else if (type == OPERATION_ARG) {
        return new SasmOperationArgImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
