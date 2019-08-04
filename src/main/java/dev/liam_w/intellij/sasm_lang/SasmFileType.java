package dev.liam_w.intellij.sasm_lang;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SasmFileType extends LanguageFileType {
    public static final FileType INSTANCE = new SasmFileType();

    private SasmFileType() {
        super(SasmLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "sasm";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Sasm script";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "sasm";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return SasmIcons.FILE;
    }
}
