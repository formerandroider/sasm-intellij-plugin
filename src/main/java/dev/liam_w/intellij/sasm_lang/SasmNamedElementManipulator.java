package dev.liam_w.intellij.sasm_lang;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.util.IncorrectOperationException;
import dev.liam_w.intellij.sasm_lang.psi.SasmNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SasmNamedElementManipulator extends AbstractElementManipulator<SasmNamedElement> {
    @Nullable
    @Override
    public SasmNamedElement handleContentChange(@NotNull SasmNamedElement element, @NotNull TextRange range, String newContent) throws IncorrectOperationException {
        return (SasmNamedElement) element.setName(newContent);
    }
}
