package reseau;

import java.io.IOException;
import java.net.*;

public class Serveur {

    public static void main(String[] args){

        ServerSocket listeningSocket;
        try {
            listeningSocket = new ServerSocket(2009);
            Thread t = new Thread(new Accepter_clients(listeningSocket));
            t.start();
            System.out.println("serveur à l'écoute sur le port " + listeningSocket.getLocalPort());

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

class Accepter_clients implements Runnable {

    private ServerSocket listeningSocket;
    private Socket socket;
    private int nbrclient = 1;
    public Accepter_clients(ServerSocket s){
        listeningSocket = s;
    }

    public void run() {

        try {
            while(true){
                socket = listeningSocket.accept(); // Un client se connecte on l'accepte
                System.out.println("Le client numéro "+nbrclient+ " est connecté !");
                nbrclient++;
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
