package com.example;

import com.example.netgame2.packets.Packet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static int count=0;
    public static void main(String[] args) {
        //socket to host server
        try (ServerSocket serverSocket = new ServerSocket(8111)) {
            System.out.println("You are on port 8111");
//            int counter = 0;
            Socket socket = serverSocket.accept();


            InputStream is = null;
            ObjectInputStream ois = null;

            OutputStream os = null;
            ObjectOutputStream oos = null;
//            Scanner sc = new Scanner(System.in);
//            System.out.println("whats ur name");
//            String name = sc.nextLine();
            while (true) {


                //accepting client and storing

                System.out.println("YOU CONNECTED");
                //get ability to send data thru socket
                //taking output stream and making it a printable object

                if (os == null && oos == null) {
                    os = socket.getOutputStream();
                    oos = new ObjectOutputStream(os);
                }

//                counter += 1;
//                System.out.println("Type somthing");
//                String ask = sc.nextLine();
                Packet packet=new Packet(count);
                count++;

                oos.writeObject(packet);
//                if (Objects.equals(ask, "bye") || Objects.equals(ask, "Bye") || Objects.equals(ask, "Goodbye") || Objects.equals(ask, "goodbye")) {
//                    socket.close();
//                    System.exit(0);



            if (is == null && ois == null) {
                is = socket.getInputStream();
                ois = new ObjectInputStream(is);
            }
                Packet recPacket= (Packet) ois.readObject();

                System.out.println(recPacket);

                Thread.sleep(100);
//                BufferedReader r=new BufferedReader(new InputStreamReader(is));

        }
            } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//    } catch (IOException e) {
//            System.out.println("User left");
//        }

    }
}