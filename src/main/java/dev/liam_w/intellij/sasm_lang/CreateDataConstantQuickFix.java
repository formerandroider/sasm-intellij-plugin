package dev.liam_w.intellij.sasm_lang;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import dev.liam_w.intellij.sasm_lang.psi.SasmElementFactory;
import dev.liam_w.intellij.sasm_lang.psi.SasmFile;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperation;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class CreateDataConstantQuickFix extends BaseIntentionAction {
    private String label;

    public CreateDataConstantQuickFix(String label) {
        this.label = label;

        setText("Create data constant");
    }

    @Nls(capitalization = Nls.Capitalization.Sentence)
    @NotNull
    @Override
    public String getFamilyName() {
        return "Sasm operations";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                Collection<VirtualFile> virtualFiles =
                        FileTypeIndex.getFiles(SasmFileType.INSTANCE, GlobalSearchScope.allScope(project));
                if (virtualFiles.size() == 1) {
                    createOperation(project, virtualFiles.iterator().next());
                } else {
                    final FileChooserDescriptor descriptor =
                            FileChooserDescriptorFactory.createSingleFileDescriptor(SasmFileType.INSTANCE);
                    descriptor.setRoots(ProjectUtil.guessProjectDir(project));
                    final VirtualFile file = FileChooser.chooseFile(descriptor, project, null);
                    if (file != null) {
                        createOperation(project, file);
                    }
                }
            }
        });
    }

    private void createOperation(final Project project, final VirtualFile file) {
        WriteCommandAction.writeCommandAction(project).run(() -> {
            SasmFile sasmFile = (SasmFile) PsiManager.getInstance(project).findFile(file);
            ASTNode lastChildNode = sasmFile.getNode().getLastChildNode();
            // TODO: Add another check for CRLF
            if (lastChildNode != null/* && !lastChildNode.getElementType().equals(SimpleTypes.CRLF)*/) {
                sasmFile.getNode().addChild(SasmElementFactory.createCRLF(project).getNode());
            }
            SasmOperation operation = SasmElementFactory.createDataOperation(project, label, "");
            sasmFile.getNode().addChild(operation.getNode());
            ((Navigatable) operation.getLastChild().getNavigationElement()).navigate(true);
            FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(4, 0, false, false, false);
        });
    }
}
