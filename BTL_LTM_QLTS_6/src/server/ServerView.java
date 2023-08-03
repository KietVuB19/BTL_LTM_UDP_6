/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

public class ServerView {

    public ServerView() {
        showMessage("UDP server is running...");
        new ServerControl();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
