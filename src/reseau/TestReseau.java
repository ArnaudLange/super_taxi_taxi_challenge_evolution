package reseau;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by jimmy on 14/05/16.
 */
public class TestReseau {
    public static void main(String[] zero) {

        InetAddress LocaleAdresse ;
        InetAddress ServeurAdresse;

        try {

            LocaleAdresse = InetAddress.getLocalHost();
            System.out.println("L'adresse locale est : "+LocaleAdresse );

            //ServeurAdresse= InetAddress.getByName("www.siteduzero.com");
            //System.out.println("L'adresse du serveur du site du zéro est : "+ServeurAdresse);

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
    }
}
