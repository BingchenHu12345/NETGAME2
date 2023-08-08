package com.example;

import com.example.netgame2.packets.Packet;
import com.example.netgame2.packets.Player;
import com.example.netgame2.packets.PlayerPacket;

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
                    Main.PLayerList.add(new Player(0, 0));
                    count++;

                    InputStream is = null;
                    ObjectInputStream ois = null;

                    OutputStream os = null;
                    ObjectOutputStream oos = null;
//            Scanner sc = new Scanner(System.in);
//            System.out.println("whats ur name");
//            String name = sc.nextLine();
                    int ID = count;
                    // ^^^intializing the players cooridinates
                    while (true) {


                        //accepting client and storing

                        System.out.println("YOU CONNECTED");
                        //get ability to send data thru socket
                        //taking output stream and making it a printable object

                        if (os == null && oos == null) {
                            try {
                                os = socket.getOutputStream();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                oos = new ObjectOutputStream(os);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }

//                counter += 1;
//                System.out.println("Type somthing");
//                String ask = sc.nextLine();
                        Packet packet = new Packet(Main.PLayerList);


                        try {
                            oos.writeObject(packet);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
//                if (Objects.equals(ask, "bye") || Objects.equals(ask, "Bye") || Objects.equals(ask, "Goodbye") || Objects.equals(ask, "goodbye")) {
//                    socket.close();
//                    System.exit(0);


                        if (is == null && ois == null) {
                            try {
                                is = socket.getInputStream();
                                ois = new ObjectInputStream(is);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                        PlayerPacket recPacket = null;
                        try {
                            recPacket = (PlayerPacket) ois.readObject();
                            PLayerList.set(ID,new Player(recPacket.x,recPacket.y));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }



                        System.out.println(recPacket);

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