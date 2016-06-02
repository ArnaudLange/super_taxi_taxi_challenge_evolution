package reseau;

import java.io.*;
import java.net.*;

import static java.lang.Thread.sleep;

public class Client
{
    public static void main (String args[])
    {
        ClientEnvoie clientEnvoie = null;
        ClientReception clientReception = null;
        Socket socket = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String readLine;


        while (socket == null)
        {
            try
            {
                System.out.println("Veuillez rentrer l'adresse ip à laquelle vous souhaitez vous connecter :");
                readLine = br.readLine();
                socket = new Socket(readLine, 2009);
                clientReception = new ClientReception(socket.getInputStream());
                clientReception.start();
                clientEnvoie = new ClientEnvoie(socket.getOutputStream());
                clientEnvoie.start();
            }
            catch (IOException e)
            {
                System.err.println("Erreur de connexion avec l'adresse ip rentrée");
            }

        }
        System.out.println("Connexion avec le serveur établie");

        try
        {
            while(clientReception.isAlive())
                sleep(200);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }


        clientEnvoie.close();
        try
        {
            socket.close();
        }
        catch (IOException e)
        {}
    }
}

