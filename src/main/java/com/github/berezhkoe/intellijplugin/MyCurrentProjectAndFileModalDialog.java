package com.github.berezhkoe.intellijplugin;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class MyCurrentProjectAndFileModalDialog extends DialogWrapper {
    private final String projectName;
    private final String fileName;

    protected MyCurrentProjectAndFileModalDialog(String projectName, String fileName) {
        super(true);
        this.projectName = projectName;
        this.fileName = fileName;
        setTitle("Current Project and File Info");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

        JLabel projectLabel = new JLabel("Current project name: " + projectName);
        JLabel fileLabel = new JLabel("Current file name: " + fileName);

        projectLabel.setPreferredSize(new Dimension(200, 10));
        fileLabel.setPreferredSize(new Dimension(200, 10));

        dialogPanel.add(projectLabel);
        dialogPanel.add(fileLabel);

        return dialogPanel;
    }
}
