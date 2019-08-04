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

public class SasmOperationArgImpl extends SasmNamedElementImpl implements SasmOperationArg {

  public SasmOperationArgImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SasmVisitor visitor) {
    visitor.visitOperationArg(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SasmVisitor) accept((SasmVisitor)visitor);
    else super.accept(visitor);
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
  @Nullable
  public String getArgumentLabel() {
    return SasmPsiImplUtil.getArgumentLabel(this);
  }

}
