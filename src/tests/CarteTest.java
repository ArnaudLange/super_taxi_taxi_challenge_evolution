package tests;

import carte.Carte;
import carte.Evenement;
import carte.InterpreteurCarte;
import carte.PointCardinal;
import jeu.Constante;
import jeu.Joueur;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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


    //Tests de gestionDeplacement() est
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

    //Tests de gestionDeplacement() ouest
    @Test
    public void testDeplacementOuest1() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(1);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.WEST);
        j.setVitesse(0);

        carte.gestionDeplacements(j);
        Assert.assertEquals(1, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }
    @Test
    public void testDeplacementOuest2() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(1);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.WEST);
        j.setVitesse(1);

        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }

    @Test
    public void testDeplacementOuest3() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(1);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.WEST);
        j.setVitesse(2);

        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(0, j.getNbPoints());
    }

    //Tests de gestionDeplacement() nord
    @Test
    public void testDeplacementNord1() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(3);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.NORTH);
        j.setVitesse(0);

        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(3, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }
    @Test
    public void testDeplacementNord2() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(3);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.NORTH);
        j.setVitesse(1);

        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(2, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }

    @Test
    public void testDeplacementNord3() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(3);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.NORTH);
        j.setVitesse(2);

        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(2, j.getPosY());
        Assert.assertEquals(0, j.getNbPoints());
    }

    //Tests de gestionDeplacement() sud
    @Test
    public void testDeplacementSud1() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(2);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.SOUTH);
        j.setVitesse(0);

        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(2, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }
    @Test
    public void testDeplacementSud2() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(2);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.SOUTH);
        j.setVitesse(1);

        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(3, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }

    @Test
    public void testDeplacementSud3() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(2);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.SOUTH);
        j.setVitesse(2);

        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(3, j.getPosY());
        Assert.assertEquals(0, j.getNbPoints());
    }

    @Test
    public void testEvenementRien(){
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(0, 0, PointCardinal.EAST, 0, 1);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObj(){
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(0, 0, PointCardinal.EAST, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.OBJECTIF);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementCourb(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(4, 0, PointCardinal.NORTH, 0, 1);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.COURBE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjCourb(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(4, 0, PointCardinal.NORTH, 4, 0);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.OBJECTIF);
        listeAttendue.add(Evenement.COURBE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementPrio1(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.NORTH, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementPrio2(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.SOUTH, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementPrio3(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.EAST, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementPrio4(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.WEST, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementPrio11(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(3, 4, PointCardinal.NORTH, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementPrio21(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(3, 4, PointCardinal.SOUTH, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjPrio1(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.NORTH, 1, 2);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.OBJECTIF);
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjPrio2(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.SOUTH, 1, 2);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.OBJECTIF);
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjPrio3(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.EAST, 1, 2);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.OBJECTIF);
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjPrio4(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.WEST, 1, 2);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.OBJECTIF);
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjPrio11(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(3, 4, PointCardinal.NORTH, 3, 4);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.OBJECTIF);
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjPrio21(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Evenement> liste = carte.getEvenement(3, 4, PointCardinal.SOUTH, 3, 4);
        List<Evenement> listeAttendue = new ArrayList<Evenement>();
        listeAttendue.add(Evenement.OBJECTIF);
        Assert.assertEquals(listeAttendue, liste);
    }


}
