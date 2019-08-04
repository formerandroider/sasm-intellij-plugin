// This is a generated file. Not intended for manual editing.
package dev.liam_w.intellij.sasm_lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static dev.liam_w.intellij.sasm_lang.psi.SasmTypes.*;
import dev.liam_w.intellij.sasm_lang.psi.*;
import com.intellij.navigation.ItemPresentation;

public class SasmOperationImpl extends SasmNamedElementImpl implements SasmOperation {

  public SasmOperationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SasmVisitor visitor) {
    visitor.visitOperation(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SasmVisitor) accept((SasmVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SasmOperationArg getOperationArg() {
    return findChildByClass(SasmOperationArg.class);
  }

  @Override
  public String getLabel() {
    return SasmPsiImplUtil.getLabel(this);
  }

  @Override
  public String getInstruction() {
    return SasmPsiImplUtil.getInstruction(this);
  }

  @Override
  public String getName() {
    return SasmPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return SasmPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return SasmPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public ItemPresentation getPresentation() {
    return SasmPsiImplUtil.getPresentation(this);
  }

}
