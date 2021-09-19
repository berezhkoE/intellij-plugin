package com.github.berezhkoe.intellijplugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.idea.core.KotlinFileTypeFactoryUtils;
import org.jetbrains.kotlin.psi.KtNamedFunction;

public class MyCurrentMethodAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        Document document = editor.getDocument();

        Project openProject = ProjectManager.getInstance().getOpenProjects()[0];
        PsiFile psiFile = PsiDocumentManager.getInstance(openProject).getPsiFile(document);

        if (psiFile != null) {
            KtNamedFunction method = PsiTreeUtil.getParentOfType(
                    psiFile.findElementAt(editor.getCaretModel().getOffset()), KtNamedFunction.class);

            if (method != null) {
                TextRange methodTextRange = method.getTextRange();
                int methodStartOffset = methodTextRange.getStartOffset();

                int methodStartLineNumber = document.getLineNumber(methodStartOffset);
                int methodEndLineNumber = document.getLineNumber(methodTextRange.getEndOffset());

                int linesNumber = methodEndLineNumber + 1 - methodStartLineNumber;

                int lineStartOffset = document.getLineStartOffset(methodStartLineNumber);

                MyCurrentMethodDialog myModalDialog = new MyCurrentMethodDialog(method.getName(),
                        linesNumber,
                        openProject,
                        document,
                        lineStartOffset,
                        methodStartOffset - lineStartOffset);
                myModalDialog.showAndGet();
            }
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        if (!ArrayUtils.contains(KotlinFileTypeFactoryUtils.KOTLIN_EXTENSIONS,
                FileEditorManager.getInstance(ProjectManager.getInstance().getOpenProjects()[0])
                        .getSelectedFiles()[0]
                        .getExtension())) {
            e.getPresentation().setEnabledAndVisible(false);
        }
    }
}
