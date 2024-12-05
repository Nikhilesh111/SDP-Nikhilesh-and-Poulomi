package Airline_Management;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ListAllView extends JFrame {

    private JList<String> allAccountList;

    public ListAllView() {
        setSize(300, 600);
        setTitle("All Airlines");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addComponents();
        setVisible(true);
        pack();
    }

    private void addComponents() {
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        allAccountList = new JList<>();
        scrollPane.setViewportView(allAccountList);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setAllAccountListDefaultModel(DefaultListModel<String> defaultModel) {
        allAccountList.setModel(defaultModel);
    }
}