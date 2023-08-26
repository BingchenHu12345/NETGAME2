package com.example;

import com.example.netgame2.packets.Packet;
import com.example.netgame2.packets.Player;
import com.example.netgame2.packets.PlayerPacket;
import com.example.netgame2.packets.PlayerState;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static int count=0;

    public static ArrayList<Player> PLayerList=new ArrayList<>();

    public static void main(String[] args) {



        //socket to host server
        try (ServerSocket serverSocket = new ServerSocket(8111)) {
            System.out.println("You are on port 8111");
//            int counter = 0;
            while (true) {


                Socket socket = serverSocket.accept();
                new Thread(() -> {

                    //connection threads start here
                    Socket ThreadSocket= socket;
//                    Main.PLayerList.add(new Player(0, 0));
                    int ID = count;
                    count++;

                    InputStream is = null;
                    ObjectInputStream ois = null;

                    OutputStream os = null;
                    ObjectOutputStream oos = null;

                    try {
                        os = ThreadSocket.getOutputStream();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        oos = new ObjectOutputStream(os);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        is = ThreadSocket.getInputStream();
                        ois = new ObjectInputStream(is);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

//            Scanner sc = new Scanner(System.in);
//            System.out.println("whats ur name");
//            String name = sc.nextLine();

                    // ^^^intializing the players cooridinates
                    System.out.println("YOU CONNECTED");
                    while (true) {

                        //accepting client and storing


                        //get ability to send data thru socket
                        // TODO add playerstate to player.java and send it using server packets
                        //taking output stream and making it a printable object
                        Packet packet;
                        if(PLayerList.size()==1){
                            packet=new Packet(PLayerList.get(0).x, PLayerList.get(0).y,ID,PLayerList.get(0).ps);
                        } else if (PLayerList.size()==2) {
                            packet=new Packet(PLayerList.get(0).x, PLayerList.get(0).y,PLayerList.get(1).x, PLayerList.get(1).y,ID,PLayerList.get(1).ps);
                        } else{
                            packet=new Packet();
                        }

                        System.out.println(packet);
                        System.out.println(PLayerList.size());


                        try {
                            oos.writeObject(packet);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        PlayerPacket recPacket = null;
                        try {
                            recPacket = (PlayerPacket) ois.readObject();
                            if(PLayerList.size()<=ID)
                            {
                                PLayerList.add(new Player(recPacket.x,recPacket.y));
                            }
                            else{PLayerList.set(ID,new Player(recPacket.x,recPacket.y));}

                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(recPacket.x + " " + recPacket.y);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
//                BufferedReader r=new BufferedReader(new InputStreamReader(is));

                    }
                }).start();

            }


            } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
//    } catch (IOException e) {
//            System.out.println("User left");
//        }

    }
}