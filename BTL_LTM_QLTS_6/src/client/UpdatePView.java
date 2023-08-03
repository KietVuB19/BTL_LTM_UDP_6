/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdatePView extends JFrame implements ActionListener {

    private JTextField txtSearch, txtTen, txtMoTa;
    private JButton btnSearchP, btnSubmit, btnDeleteP ,btnBack;

    public UpdatePView() {
        super("UpdatePView");
        JPanel content = new JPanel(new GridLayout(8,2));
        
        txtSearch = new JTextField(15);
        content.add(txtSearch);
       
        btnSearchP = new JButton("SearchP");
        btnSearchP.addActionListener(this);
        content.add(btnSearchP);
         
        content.add(new JLabel("Ten:"));
        txtTen = new JTextField(20);
        content.add(txtTen);
        
        content.add(new JLabel("Mo ta:"));
        txtMoTa = new JTextField(20);
        content.add(txtMoTa);
        
        btnSubmit = new JButton("UpdateP");
        btnSubmit.addActionListener(this);
        content.add(btnSubmit);
        
        btnDeleteP = new JButton("Delete");
        btnDeleteP.addActionListener(this);
        content.add(btnDeleteP);
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        content.add(btnBack);
        
        this.setContentPane(content);
        this.pack();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnBack)) {
            ClientControl cc = new ClientControl();
            cc.openConnection();
            this.setVisible(false);
            ClientView view = new ClientView();
            view.setVisible(true);
            cc.closeConnection();
        }
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
