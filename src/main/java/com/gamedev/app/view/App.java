package com.gamedev.app.view;

import com.gamedev.app.parser.excel.ExcelParser;
import com.gamedev.app.parser.json.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by MainUser on 28/02/2015.
 */
public class App {

    private JFrame mainFrame;
    private JPanel controlPanel;

    public App() {
        createGUI();
    }

    public static void main(String[] args) {
        App app = new App();
        app.showEventDemo();
    }

    private void showEventDemo() {
        JButton okButton = new JButton("OK");
        okButton.setActionCommand("CompieJson");
        okButton.addActionListener(new ButtonClickListener());
        controlPanel.add(okButton);
        mainFrame.setVisible(true);
    }

    private void createGUI() {
        mainFrame = new JFrame("Gamedev is awesome!");
        mainFrame.setSize(500, 600);
        mainFrame.setLayout(new GridLayout(3, 6));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(controlPanel);
        mainFrame.add(createSuggestor(mainFrame));
        mainFrame.setVisible(true);
    }

    public JTextField createSuggestor(final JFrame frame) {
        JTextField f = new JTextField(1);
        AutoSuggestor autoSuggestor = new AutoSuggestor(f, frame, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f) {
            @Override
            boolean wordTyped(String typedWord) {
                ArrayList<String> words = new ArrayList<String>();
                ExcelParser excelParser = new ExcelParser();
                Map<String, String> fromExcel = excelParser.parseExcel("C:\\Users\\MainUser\\Desktop\\table.xlsx");
                Iterator<Map.Entry<String, String>> itr = fromExcel.entrySet().iterator();
                while (itr.hasNext()){
                    Map.Entry<String, String> entry = itr.next();
                    words.add(entry.getKey() + " - " + entry.getValue());
                }
                setDictionary(words);

                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
            }
        };
        return f;
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("CompileJson")) {
                JsonParser jParser = new JsonParser();
//                jParser.encodeJson(new GameEvent());

                System.out.println("Ok ;)");
            }
        }
    }
}