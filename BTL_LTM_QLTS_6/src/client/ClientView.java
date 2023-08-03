/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientView extends JFrame implements ActionListener {

    private JTextField txtSearch;
    private JButton btnSearch, btnUpdateTS, btnUpdateP, btnAddTS;

    public ClientView() {
        super("ClientView");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        txtSearch = new JTextField(15);
        
        btnSearch = new JButton("Search");
        btnSearch.addActionListener(this);
        
        btnAddTS = new JButton("AddTS");
        btnAddTS.addActionListener(this);
        
        btnUpdateTS = new JButton("UpdateTS");
        btnUpdateTS.addActionListener(this);
        
        btnUpdateP = new JButton("UpdateP");
        btnUpdateP.addActionListener(this);
        
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(txtSearch);
        content.add(btnSearch);
        content.add(btnAddTS);
        content.add(btnUpdateTS);
        content.add(btnUpdateP);
        
        this.setContentPane(content);
        this.pack();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnSearch)) {
            System.out.println("Ban vua bam btnSearch");
            ClientControl cc = new ClientControl();
            cc.openConnection();
            String keyword = txtSearch.getText();
            
            cc.sendDataTS(keyword);

            String result = cc.receiveData();
            
            if (result.equals("ok")) {
                showMessage("Da tim thay TS");
            } else {
                showMessage("Khong co TS trong CSDL");
            }
            
            cc.closeConnection();
            this.setVisible(false);
            ClientView view = new ClientView();
            view.setVisible(true);
        
        }
        else if(e.getSource().equals(btnAddTS)){
            System.out.println("Ban vua bam btnAddTS");
            this.setVisible(false);
            AddTSView view = new AddTSView();
            view.setVisible(true);
        }
        else if(e.getSource().equals(btnUpdateTS)){
            System.out.println("Ban vua bam btnUpdateTS");
            this.setVisible(false);
            UpdateTSView view = new UpdateTSView();
            view.setVisible(true);
        }
        else if(e.getSource().equals(btnUpdateP)){
            System.out.println("Ban vua bam btnUpdateP");
            this.setVisible(false);
            UpdatePView view = new UpdatePView();
            view.setVisible(true);
        }
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
