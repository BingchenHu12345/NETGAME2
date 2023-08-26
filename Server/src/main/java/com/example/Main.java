package com.example;

import com.example.netgame2.packets.Packet;
import com.example.netgame2.packets.Player;
import com.example.netgame2.packets.PlayerPacket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static int count=0;

    public static ArrayList<Player> playerlist =new ArrayList<>();

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

                        //taking output stream and making it a printable object
                        Packet packet;
                        if(playerlist.size()==1){
                            packet=new Packet(playerlist.get(0).x, playerlist.get(0).y,ID, playerlist.get(0).ps);
                        } else if (playerlist.size()==2) {
                            packet=new Packet(playerlist.get(0).x, playerlist.get(0).y, playerlist.get(1).x, playerlist.get(1).y,ID, playerlist.get(1).ps);
                        } else{
                            packet=new Packet();
                        }

                        System.out.println(packet);
                        System.out.println(playerlist.size());


                        try {
                            oos.writeObject(packet);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        PlayerPacket recPacket = null;
                        try {
                            recPacket = (PlayerPacket) ois.readObject();
                            if(playerlist.size()<=ID)
                            {
                                playerlist.add(new Player(recPacket.x,recPacket.y,recPacket.ps));
                            }
                            else{
                                playerlist.set(ID,new Player(recPacket.x,recPacket.y,recPacket.ps));}

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