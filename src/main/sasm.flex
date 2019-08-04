package dev.liam_w.intellij.sasm_lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import dev.liam_w.intellij.sasm_lang.psi.SasmTypes;
import com.intellij.psi.TokenType;

%%

%class SasmLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

DIGIT=[0-9]

%state IN_OPR_ARGS IN_LABEL

%%

<YYINITIAL>^--(.*)\n? {return SasmTypes.COMMENT;}
<YYINITIAL>[a-z]+ {
    yybegin(IN_LABEL);
    return SasmTypes.LABEL;
}
<YYINITIAL,IN_LABEL>[A-Z]{3} {
    yybegin(IN_OPR_ARGS);
    return SasmTypes.OPERATION_IDENTIFIER;
}
<IN_OPR_ARGS>" " {return TokenType.WHITE_SPACE;}
<IN_OPR_ARGS>[a-z]+ {
    return SasmTypes.IDENTIFIER;
}
<IN_OPR_ARGS>{DIGIT}+ { return SasmTypes.NUM; }
<IN_OPR_ARGS>\n+ { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
[ \t]+ {return TokenType.WHITE_SPACE;}
\n+ {return TokenType.WHITE_SPACE;}
[^] { return TokenType.BAD_CHARACTER; }