package reseau;

import java.io.IOException;
import java.net.*;

public class Client {

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
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

