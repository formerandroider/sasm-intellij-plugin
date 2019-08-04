// This is a generated file. Not intended for manual editing.
package dev.liam_w.intellij.sasm_lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static dev.liam_w.intellij.sasm_lang.psi.SasmTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SasmParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t instanceof IFileElementType) {
      r = parse_root_(t, b, 0);
    }
    else {
      r = false;
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return sasmFile(b, l + 1);
  }

  /* ********************************************************** */
  // LABEL? OPERATION_IDENTIFIER operation_arg?
  public static boolean operation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "operation")) return false;
    if (!nextTokenIs(b, "<operation>", LABEL, OPERATION_IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPERATION, "<operation>");
    r = operation_0(b, l + 1);
    r = r && consumeToken(b, OPERATION_IDENTIFIER);
    r = r && operation_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LABEL?
  private static boolean operation_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "operation_0")) return false;
    consumeToken(b, LABEL);
    return true;
  }

  // operation_arg?
  private static boolean operation_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "operation_2")) return false;
    operation_arg(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER | NUM
  public static boolean operation_arg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "operation_arg")) return false;
    if (!nextTokenIs(b, "<operation arg>", IDENTIFIER, NUM)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPERATION_ARG, "<operation arg>");
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, NUM);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CRLF | COMMENT | operation*
  static boolean sasmFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sasmFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CRLF);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = sasmFile_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // operation*
  private static boolean sasmFile_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sasmFile_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!operation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "sasmFile_2", c)) break;
    }
    return true;
  }

}
