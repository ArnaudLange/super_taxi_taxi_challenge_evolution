package tests;

import carte.Carte;
import carte.InterpreteurCarte;
import carte.PointCardinal;
import jeu.Constante;
import jeu.Joueur;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created by Cokral on 03/06/2016.
 */
public class EvenementTest {

    @Test
    public void testPriorite1(){
        File fichierCarte = new File("src/carte/cartesTests/carte_test_priorite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(1);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EST);
        j.setVitesse(1);

        carte.gestionDeplacements(j);
        Assert.assertEquals(2, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }

    @Test
    public void testPriorite2(){
        File fichierCarte = new File("src/carte/cartesTests/carte_test_priorite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(2);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EST);
        j.setVitesse(1);

        carte.gestionDeplacements(j);
        Assert.assertEquals(3, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }

    @Test
    public void testPriorite3(){
        File fichierCarte = new File("src/carte/cartesTests/carte_test_priorite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EST);
        j.setVitesse(2);

        carte.gestionDeplacements(j);
        Assert.assertEquals(2, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }

    @Test
    public void testPriorite4(){
        File fichierCarte = new File("src/carte/cartesTests/carte_test_priorite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(1);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EST);
        j.setVitesse(3);

        carte.gestionDeplacements(j);
        Assert.assertEquals(4, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }

}
