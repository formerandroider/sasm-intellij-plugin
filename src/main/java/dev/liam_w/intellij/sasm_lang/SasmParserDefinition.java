package dev.liam_w.intellij.sasm_lang;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import dev.liam_w.intellij.sasm_lang.parser.SasmParser;
import dev.liam_w.intellij.sasm_lang.psi.SasmFile;
import dev.liam_w.intellij.sasm_lang.psi.SasmTypes;
import org.jetbrains.annotations.NotNull;

public class SasmParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(SasmTypes.COMMENT);

    public static final IFileElementType FILE = new IFileElementType(SasmLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new SasmLexerAdapter();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new SasmParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return SasmTypes.Factory.createElement(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new SasmFile(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        if (left.getElementType().equals(SasmTypes.OPERATION_IDENTIFIER) && right.getElementType().equals(SasmTypes.IDENTIFIER)) {
            return SpaceRequirements.MUST;
        }

        return SpaceRequirements.MAY;
    }
}
