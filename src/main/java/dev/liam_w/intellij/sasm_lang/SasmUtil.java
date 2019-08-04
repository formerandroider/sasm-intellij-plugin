package dev.liam_w.intellij.sasm_lang;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import dev.liam_w.intellij.sasm_lang.psi.SasmFile;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SasmUtil {

    public static List<SasmOperation> findOperations(Project project, String label) {
        return findOperations(project, label, null);
    }

    public static List<SasmOperation> findOperations(Project project, String label, String operationMatch) {
        List<SasmOperation> result = null;
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(SasmFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SasmFile sasmFile = (SasmFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (sasmFile != null) {
                SasmOperation[] operations = PsiTreeUtil.getChildrenOfType(sasmFile, SasmOperation.class);
                if (operations != null) {
                    for (SasmOperation operation : operations) {
                        if (label.equals(operation.getLabel()) && (operationMatch == null || operation.getInstruction().equals(operationMatch))) {
                            if (result == null) {
                                result = new ArrayList<SasmOperation>();
                            }
                            result.add(operation);
                        }
                    }
                }
            }
        }
        return result != null ? result : Collections.<SasmOperation>emptyList();
    }

    public static List<SasmOperation> findOperations(Project project) {
        List<SasmOperation> result = new ArrayList<SasmOperation>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(SasmFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SasmFile sasmFile = (SasmFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (sasmFile != null) {
                SasmOperation[] operations = PsiTreeUtil.getChildrenOfType(sasmFile, SasmOperation.class);
                if (operations != null) {
                    Collections.addAll(result, operations);
                }
            }
        }
        return result;
    }

}
