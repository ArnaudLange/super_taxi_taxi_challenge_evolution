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

            while(clientReception.isAlive())
                sleep(200);

            clientEnvoie.interrupt();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

