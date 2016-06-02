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
 * Created by lange_000 on 02/06/2016.
 */
public class CarteTest {

    @Test
    public void testGetLargeur1(){
        Carte carte = new Carte(30, 30);
        Assert.assertEquals(30, carte.getLargeur());
    }
    @Test
    public void testGetLongueur1(){
        Carte carte = new Carte(30, 30);
        Assert.assertEquals(30, carte.getLongueur());
    }
    @Test
    public void testGetLargeur2(){
        Carte carte = new Carte();
        Assert.assertEquals(0, carte.getLargeur());
    }
    @Test
    public void testGetLongueur2(){
        Carte carte = new Carte();
        Assert.assertEquals(0, carte.getLongueur());
    }


    //Tests de gestionDeplacement() à  l'est dans un cul de sac
    @Test
    public void testDeplacementEast1() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EAST);
        j.setVitesse(0);

        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }
    @Test
    public void testDeplacementEast2() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EAST);
        j.setVitesse(1);

        carte.gestionDeplacements(j);
        Assert.assertEquals(1, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }

    @Test
    public void testDeplacementEast3() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EAST);
        j.setVitesse(2);

        carte.gestionDeplacements(j);
        Assert.assertEquals(1, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(0, j.getNbPoints());
    }

}
