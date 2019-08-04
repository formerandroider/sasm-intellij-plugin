// This is a generated file. Not intended for manual editing.
package dev.liam_w.intellij.sasm_lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class SasmVisitor extends PsiElementVisitor {

  public void visitOperation(@NotNull SasmOperation o) {
    visitNamedElement(o);
  }

  public void visitOperationArg(@NotNull SasmOperationArg o) {
    visitNamedElement(o);
  }

  public void visitNamedElement(@NotNull SasmNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
