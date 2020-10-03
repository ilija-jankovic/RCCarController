package com.example.rccarcontroller;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientThread extends Thread {
    final int PORT = 4210;
    final String LOCAL_IP = "192.168.4.1";
    InetAddress ip;
    DatagramSocket socket;

    @Override
    public void run(){
        try {
            ip = InetAddress.getByName(LOCAL_IP);
            socket = new DatagramSocket();
            /*Log.i("Client", "Working");

            status.setText("Connecting...");
            //test connection
            sendMsg("rc connection test");

            byte[] receivingDataBuffer = new byte[256];
            DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            socket.receive(receivingPacket);
            String receivedData = new String(receivingPacket.getData());

            if(!receivedData.equals("rc connected")){
                status.setText("Not Connected");
                return;
            }

            status.setText("Connected");

            //remove later
            socket.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFloats(){
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
            DataOutputStream dos = new DataOutputStream(bos);

            //send steering and thrust data
            dos.writeFloat(MainActivity.steering.getProgress()-50);
            dos.writeFloat(MainActivity.thrust.getProgress()-50);

            byte[] data = bos.toByteArray();

            DatagramPacket packet = new DatagramPacket(data, data.length, ip, PORT);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
