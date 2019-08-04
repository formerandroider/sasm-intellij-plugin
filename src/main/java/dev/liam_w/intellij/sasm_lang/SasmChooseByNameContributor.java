package dev.liam_w.intellij.sasm_lang;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import dev.liam_w.intellij.sasm_lang.psi.SasmOperation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SasmChooseByNameContributor implements ChooseByNameContributor {
    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        List<SasmOperation> operations = SasmUtil.findOperations(project);
        List<String> names = new ArrayList<>(operations.size());
        for (SasmOperation operation : operations) {
            if (operation.getLabel() != null && operation.getLabel().length() > 0) {
                names.add(operation.getLabel());
            }
        }
        return names.toArray(new String[0]);
    }

    @NotNull
    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        // todo include non project items
        List<SasmOperation> operations = SasmUtil.findOperations(project, name);
        return operations.toArray(new NavigationItem[0]);
    }
}
