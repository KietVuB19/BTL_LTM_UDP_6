/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.awt.FlowLayout;
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

public class AddTSView extends JFrame implements ActionListener {

    private JTextField txtTen, txtGia, txtViTri, txtLoai, txtMaPhong;
    private JButton btnAddTS, btnBack;

    public AddTSView() {
        super("AddTSView");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel content = new JPanel(new GridLayout(7, 2));

        content.add(new JLabel("Ten:"));
        txtTen = new JTextField(20);
        content.add(txtTen);
        content.add(new JLabel("Gia:"));
        txtGia = new JTextField(20);
        content.add(txtGia);
        content.add(new JLabel("Vi tri:"));
        txtViTri = new JTextField(20);
        content.add(txtViTri);
        content.add(new JLabel("Loai:"));
        txtLoai = new JTextField(20);
        content.add(txtLoai);
        content.add(new JLabel("Ma phong:"));
        txtMaPhong = new JTextField(20);
        content.add(txtMaPhong);

        btnAddTS = new JButton("AddTS");
        btnAddTS.addActionListener(this);
        content.add(btnAddTS);

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
        } else if (e.getSource().equals(btnAddTS)) {
            ClientControl cc = new ClientControl();
            cc.openConnection();          
            TaiSan ts = new TaiSan(txtTen.getText(), Float.parseFloat(txtGia.getText()), txtViTri.getText(), txtLoai.getText(), Integer.parseInt(txtMaPhong.getText()));
            cc.sendDataTS(ts);

            String result = cc.receiveData();
            if (result.equals("ok")) {
                showMessage("Them TS thanh cong");
            } else {
                showMessage("Them TS that bai");
            }
            cc.closeConnection();
        }
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
