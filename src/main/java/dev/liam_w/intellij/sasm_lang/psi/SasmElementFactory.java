package dev.liam_w.intellij.sasm_lang.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import dev.liam_w.intellij.sasm_lang.SasmFileType;

public class SasmElementFactory {
    public static SasmOperation createOperation(Project project, String label) {
        String text = String.format("%s NUL", label);

        final SasmFile file = createFile(project, text);
        return (SasmOperation) file.getFirstChild();
    }

    public static SasmOperation createDataOperation(Project project, String label, String value) {
        String text = String.format("%s DAT %s", label, value);

        final SasmFile file = createFile(project, text);
        return (SasmOperation) file.getFirstChild();
    }

    public static PsiElement createCRLF(Project project) {
        final SasmFile file = createFile(project, "\n");
        return file.getFirstChild();
    }

    public static SasmFile createFile(Project project, String text) {
        String name = "dummy.sasm";
        return (SasmFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, SasmFileType.INSTANCE, text);
    }

    public static SasmOperationArg createOperationArg(Project project, String label) {
        String text = String.format("DAT %s", label);

        final SasmFile file = createFile(project, text);
        return (SasmOperationArg) file.getFirstChild().getLastChild();
    }
}
