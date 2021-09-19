package com.github.berezhkoe.intellijplugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.NotNull;

public class MyCurrentProjectAndFileAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project openProject = ProjectManager.getInstance().getOpenProjects()[0];
        MyCurrentProjectAndFileModalDialog myModalDialog = new MyCurrentProjectAndFileModalDialog(openProject.getName(),
                FileEditorManager.getInstance(openProject).getSelectedFiles()[0].getPresentableName());
        myModalDialog.showAndGet();
    }
}
