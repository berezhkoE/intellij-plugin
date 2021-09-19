package com.github.berezhkoe.intellijplugin;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.EditorNotificationPanel;
import com.intellij.ui.EditorNotifications;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyExtensionNotificationProvider extends EditorNotifications.Provider<EditorNotificationPanel> implements DumbAware {
    public static final Key<EditorNotificationPanel> KEY = Key.create("MyExtensionNotification");

    @Override
    public @NotNull Key<EditorNotificationPanel> getKey() {
        return KEY;
    }

    @Override
    public @Nullable EditorNotificationPanel createNotificationPanel(@NotNull VirtualFile file, @NotNull FileEditor fileEditor, @NotNull Project project) {
        EditorNotificationPanel panel = new EditorNotificationPanel();
        panel.createActionLabel(MyBundle.message("labelClose"), () ->
                FileEditorManager.getInstance(project).removeTopComponent(fileEditor, panel));

        panel.setText("Extension of current file is ." +
                FileEditorManager.getInstance(project).getSelectedFiles()[0].getExtension());
        return panel;
    }
}
