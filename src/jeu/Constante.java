package jeu;

/**
 * Created by cokral on 27/05/16.
 */
public class Constante {

    /* Nombre de points perdus sur une infraction mineure */
    public final static int MINORINF=2;

    /* Nombre de points perdus sur une infraction majeure */
    public final static int MAJORINF=5;

    /* Délai avant que le serveur ne se lance une fois que deux joueurs sont connectés*/
    public final static int STARTDELAY=10;

    /* Nombre de points avec lesquels les joueurs commencent la partie */
    public final static int STARTPOINTS=30;

    /* Nombre de points gagnés lorsque l'on atteint l'objectif */
    public final static int OBJPOINTS=5;

    /* Nombre de points à atteindre pour remporter la partie */
    public final static int MAXPOINTS=40;

    /* Distance (en cases) à laquelle les joueurs peuvent voir */
    public final static int FOV=3;

    /* Position sur laquelle l'objectif apparait en début de partie */
    public final static int[] OBJECTIFCELL = {2, 0};

    /* Rayon de la vision des joueurs */
    public final static int DISTANCEVISION=6;

    /*TimeOut lancé quand 2 joueurs sont connectés*/
    public final static int TIMEOUTCONNECTION=3000;

    /*Temps de tour*/
    public final static int TOURTIME=5000;

}
