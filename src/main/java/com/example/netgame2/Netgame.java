package com.example.netgame2;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;
import com.example.netgame2.packets.Packet;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Netgame extends Application {

    public final int screenWidth = 600;
    public final int screenHeight = 600;
    int x=0;
    int y=0;

    public static Packet packet = new Packet(0);

    @Override
    public void start(Stage stage)  {

        Canvas canvas = new Canvas(screenWidth, screenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Create a timeline, with a gap between newFrame() calls of 16.66_ seconds, meaning around 60 fps.
        // Pass the newFrame() function as the function to be called each frame.
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(16.0 + (2.0 / 3.0)), e -> newFrame(gc)));


        //threading
        new Thread(() -> {
            //variables/ connection settings
            String hostname="localhost";
            int port=8111;
            //Connecting blok
            try(Socket socket = new Socket(hostname, port)) {
                //get ability to recieve the dattaaa
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);

                OutputStream os = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                while(true) {
                    Packet recPacket = (Packet) ois.readObject();
                    Packet sendPacket = new Packet(0);
                    oos.writeObject(sendPacket);
                    System.out.println(recPacket);
                    Netgame.packet = recPacket;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).start();


        tl.setCycleCount(Animation.INDEFINITE);
        canvas.setFocusTraversable(true);

        // handle mouse and key events
        canvas.setOnKeyPressed(e -> {
            // KeyCode holds what key is being referred to
            KeyCode code = e.getCode();
            System.out.println("Key was pressed: " + code);
            if (code == KeyCode.A) {
                System.out.println("The A key was specifically pressed!");
            }
            if(code==KeyCode.W){ y-=5;}
            if(code==KeyCode.S){ y+=5;}
            if(code==KeyCode.A){ x-=5;}
            if(code==KeyCode.D){ x+=5;}

        });
        canvas.setOnKeyReleased(e -> {
            KeyCode code = e.getCode();
            System.out.println("Key was released: " + code);
        });
        canvas.setOnMouseDragged(e -> {
            double x = e.getX();
            double y = e.getY();
            // these coordinates represent the constant location of the mouse.
        });
        canvas.setOnMouseClicked(e -> {
            double x = e.getX();
            double y = e.getY();
            // these coordinates represent the locations the mouse has been clicked at.
            System.out.println("Mouse clicked at: " + x + "," + y);
        });

        stage.setTitle("netgame");
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();


    }

    private void newFrame(GraphicsContext gc) {

        // draw the background as a grey rectangle
        // shapes are drawn from their top left corner.
        gc.setFill(Color.GREY);
        gc.fillRect(0,0,screenWidth,screenHeight); // starting from 0,0, which is the top left corner of the screen

        gc.setFill(Color.BLUE);
        gc.fillRect(x,y,25,25);
    }

    public static void main(String[] args) {
        launch();
    }
}