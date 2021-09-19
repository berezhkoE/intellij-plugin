package com.github.berezhkoe.intellijplugin;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MyCurrentMethodDialog extends DialogWrapper {
    private final Project project;
    private final Document document;
    private final int offset;
    private final String methodNameInfo;
    private final String linesNumberInfo;

    private final Action myInsertInfoAction;

    private final String INDENT_STRING;

    protected MyCurrentMethodDialog(String methodName, int linesNumber, Project project, Document document,
                                    int offset, int indent) {
        super(true);
        this.project = project;
        methodNameInfo  = "Current method name: " + methodName;
        linesNumberInfo = "Method's number of lines: " + linesNumber;
        this.document = document;
        this.offset = offset;
        INDENT_STRING = " ".repeat(indent);
        myInsertInfoAction = new InsertInfoAction("Insert Method Info");
        setTitle("Current Method Info");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

        JLabel methodLabel = new JLabel(methodNameInfo);
        JLabel linesNumberLabel = new JLabel(linesNumberInfo);

        methodLabel.setPreferredSize(new Dimension(200, 10));
        linesNumberLabel.setPreferredSize(new Dimension(200, 10));

        dialogPanel.add(methodLabel);
        dialogPanel.add(linesNumberLabel);

        return dialogPanel;
    }

    @Override
    protected Action @NotNull [] createActions() {
        return ArrayUtil.append(super.createActions(), myInsertInfoAction);
    }

    private class InsertInfoAction extends DialogWrapperAction {

        /**
         * The constructor
         *
         * @param name the action name (see {@link Action#NAME})
         */
        protected InsertInfoAction(@NotNull @NlsContexts.Button String name) {
            super(name);
        }

        @Override
        protected void doAction(ActionEvent e) {
            WriteCommandAction.runWriteCommandAction(project, () -> {
                String COMMENT_FIRST_LINE = INDENT_STRING + "/**\n";
                String COMMENT_NEXT_LINE = INDENT_STRING + " * ";
                String COMMENT_LAST_LINE = INDENT_STRING + " */\n";
                document.replaceString(offset, offset, COMMENT_FIRST_LINE +
                        COMMENT_NEXT_LINE + methodNameInfo + "\n" +
                        COMMENT_NEXT_LINE + linesNumberInfo + "\n" +
                        COMMENT_LAST_LINE);
            });
        }
    }
}
