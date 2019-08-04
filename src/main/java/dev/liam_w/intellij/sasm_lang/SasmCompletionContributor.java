package dev.liam_w.intellij.sasm_lang;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.AutoCompletionPolicy;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import dev.liam_w.intellij.sasm_lang.psi.SasmTypes;
import org.jetbrains.annotations.NotNull;

public class SasmCompletionContributor extends CompletionContributor {
    public SasmCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement().afterLeaf(PlatformPatterns.psiElement(SasmTypes.LABEL)).withLanguage(SasmLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {

                        resultSet.addElement(LookupElementBuilder.create("LDA").withAutoCompletionPolicy(AutoCompletionPolicy.ALWAYS_AUTOCOMPLETE));
                        resultSet.addElement(LookupElementBuilder.create("STO").withAutoCompletionPolicy(AutoCompletionPolicy.ALWAYS_AUTOCOMPLETE));
                        resultSet.addElement(LookupElementBuilder.create("INP").withAutoCompletionPolicy(AutoCompletionPolicy.ALWAYS_AUTOCOMPLETE));
                        resultSet.addElement(LookupElementBuilder.create("DAT").withAutoCompletionPolicy(AutoCompletionPolicy.ALWAYS_AUTOCOMPLETE));
                        resultSet.addElement(LookupElementBuilder.create("ADD").withAutoCompletionPolicy(AutoCompletionPolicy.ALWAYS_AUTOCOMPLETE));


                    }
                }
        );
    }
}
