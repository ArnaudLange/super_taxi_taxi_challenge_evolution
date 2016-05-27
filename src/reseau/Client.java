package reseau;

import java.io.IOException;
import java.net.*;

import static java.lang.Thread.sleep;

public class Client
{
    public static void main (String args[])
    {
        ClientEnvoie clientEnvoie;
        ClientReception clientReception;
        try
        {
            Socket socket = new Socket("localhost", 2009);
            clientReception = new ClientReception(socket.getInputStream());
            clientReception.start();
            clientEnvoie = new ClientEnvoie(socket.getOutputStream());
            clientEnvoie.start();
            System.out.println("2");
            while(!clientReception.isInterrupted())
            {
                sleep(200);
                System.out.println(clientReception.isInterrupted());
            }
            System.out.println("1");
            clientEnvoie.interrupt();
            System.out.println("3");
            clientReception.stop();
            System.out.println("4");
            clientEnvoie.stop();
            System.out.println("5");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("caca");



    }
}

