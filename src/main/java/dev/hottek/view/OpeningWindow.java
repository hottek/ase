package dev.hottek.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpeningWindow extends JFrame {

    public OpeningWindow() throws HeadlessException {
        this.setTitle("Finance Manager");
        this.setSize(600,300);
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Please select a existing Finance Manager file or create a new one"), BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton createButton = new JButton("New");
        createButton.setActionCommand("new");
        JButton openButton = new JButton("Open Existing");
        openButton.setActionCommand("open");

        OpeningWindowListener actionListener = new OpeningWindowListener();
        createButton.addActionListener(actionListener);
        openButton.addActionListener(actionListener);

        buttonPanel.add(createButton, BorderLayout.WEST);
        buttonPanel.add(openButton, BorderLayout.EAST);
        this.add(buttonPanel);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private class OpeningWindowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "new":
                    break;
                case "open":
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Select a Finance Manager file");
                    fileChooser.setAcceptAllFileFilterUsed(false);
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Finance Manager file", "fm");
                    fileChooser.addChoosableFileFilter(filter);
                    int returnValue = fileChooser.showOpenDialog(null);
                    break;
                default:
                    break;
            }
        }
    }
}
