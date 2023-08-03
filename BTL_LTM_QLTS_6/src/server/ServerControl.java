/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import client.Phong;
import client.TaiSan;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.*;
public class ServerControl {

    private Connection con;
    private DatagramSocket myServer;
    private int serverPort = 5555;
    private DatagramPacket receivePacket = null;

    public ServerControl() {
        getDBConnection("btl_ltm_qltaisan", "kiet123", "kietvb21");
        openServer(serverPort);
        while (true) {
            listenning();
        }
    }

    private void getDBConnection(String dbName, String username, String password) {
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName;
        String dbClass = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(dbClass);
            con = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("DB connect success...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openServer(int portNumber) {
        try {
            myServer = new DatagramSocket(portNumber);
            System.out.println("Open Server at port " + portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenning() {
        Object receivedData = receiveData();
        String result = "";
        if (receivedData instanceof TaiSan) {
            if (AddTS(receivedData)) {
                result = "ok";
                sendData(result);
            }
        } else if (receivedData instanceof Phong) {
            result = "ok";
            sendData(result);
        } else if (receivedData instanceof String) {
            String key = (String) receivedData;
            if (SearchTS(key)) {
                result = "ok";
                sendData(result);
            }
            else if (SearchP(key)){
                result = "ok";
                sendData(result);
            }
        }
    }

    private void sendData(String result) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(result);
            oos.flush();

            InetAddress IPAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, clientPort);
            myServer.send(sendPacket);
            System.out.println("Send Data Success..");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object receiveData() {
        Object object = null;
        try {
            byte[] receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            myServer.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            object = ois.readObject();
            System.out.println("Received Success..");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    private boolean AddTS(Object object) {
        if (object instanceof TaiSan) {
            String query = "INSERT INTO taisan (ten, gia, viTriHienTai, loaiTaiSan, maPhong) VALUES (?,?,?,?,?)";
            TaiSan ts = (TaiSan) object;
            try {
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, ts.getTen());
                stmt.setFloat(2, ts.getGia());
                stmt.setString(3, ts.getViTriHienTai());
                stmt.setString(4, ts.getLoaiTaiSan());
                stmt.setInt(5, ts.getMaPhong());
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Add Success..");
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean SearchTS(String keyword) {
        String query = "SELECT *FROM taisan INNER JOIN phong ON taisan.maPhong = phong.ma WHERE taisan.gia = "
                + keyword
                + " OR phong.ten = "
                + keyword;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                System.out.println("Search TS found: " + keyword);
                return true;
            }
            else{
                System.out.println("Khong ton tai TS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Khong ton tai TS");
        }
        return false;
    }
    
    private boolean SearchP(String keyword) {
        String query = "SELECT *FROM phong WHERE name = "
                + keyword;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                System.out.println("Search Phong found: " + keyword);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
