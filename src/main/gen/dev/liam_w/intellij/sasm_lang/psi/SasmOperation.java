// This is a generated file. Not intended for manual editing.
package dev.liam_w.intellij.sasm_lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface SasmOperation extends SasmNamedElement {

  @Nullable
  SasmOperationArg getOperationArg();

  String getLabel();

  String getInstruction();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

  ItemPresentation getPresentation();

}
