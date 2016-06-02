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
    public void testDeplacementEST1() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EST);
        j.setVitesse(0);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }
    @Test
    public void testDeplacementEST2() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EST);
        j.setVitesse(1);

        carte.gestionDeplacements(j);
        Assert.assertEquals(1, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }

    @Test
    public void testDeplacementEST3() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EST);
        j.setVitesse(2);

        assert carte != null;
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
        j.setDirection(PointCardinal.OUEST);
        j.setVitesse(0);

        assert carte != null;
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
        j.setDirection(PointCardinal.OUEST);
        j.setVitesse(1);

        assert carte != null;
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
        j.setDirection(PointCardinal.OUEST);
        j.setVitesse(2);

        assert carte != null;
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
        j.setDirection(PointCardinal.NORD);
        j.setVitesse(0);

        assert carte != null;
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
        j.setDirection(PointCardinal.NORD);
        j.setVitesse(1);

        assert carte != null;
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
        j.setDirection(PointCardinal.NORD);
        j.setVitesse(2);

        assert carte != null;
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
        j.setDirection(PointCardinal.SUD);
        j.setVitesse(0);

        assert carte != null;
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
        j.setDirection(PointCardinal.SUD);
        j.setVitesse(1);

        assert carte != null;
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
        j.setDirection(PointCardinal.SUD);
        j.setVitesse(2);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(3, j.getPosY());
        Assert.assertEquals(0, j.getNbPoints());
    }

    //test si le joueur a une direction nulle
    @Test
    public void testDeplacementNull() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setVitesse(2);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
    }

    //test si le joueur est déjà sur une case neutre
    @Test
    public void testDeplacementNeutre() {
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(1);
        j.setPosY(1);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.SUD);
        j.setVitesse(2);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(1, j.getPosX());
        Assert.assertEquals(1, j.getPosY());
        Assert.assertEquals(0, j.getNbPoints());
    }

    //test si le joueur prend une courbe => changement de direction
    @Test
    public void testDeplacementCourbeEST() {
        File fichierCarte = new File("src/carte/carte_test_courbe.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(1);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EST);
        j.setVitesse(2);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(2, j.getPosX());
        Assert.assertEquals(1, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
        Assert.assertEquals(PointCardinal.SUD, j.getDirection());
    }

    //si le joueur roule trop vite dans une courbe = accident
    @Test
    public void testDeplacementCourbeEST2() {
        File fichierCarte = new File("src/carte/carte_test_courbe.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(1);
        j.setPosY(0);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EST);
        j.setVitesse(3);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(2, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(0, j.getNbPoints());
        Assert.assertEquals(PointCardinal.EST, j.getDirection());
    }

    @Test
    public void testDeplacementCourbeEST3() {
        File fichierCarte = new File("src/carte/carte_test_courbe.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(5);
        j.setPosY(6);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EST);
        j.setVitesse(2);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(6, j.getPosX());
        Assert.assertEquals(5, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
        Assert.assertEquals(PointCardinal.NORD, j.getDirection());
    }

    //si le joueur roule trop vite dans une courbe = accident
    @Test
    public void testDeplacementCourbeEST4() {
        File fichierCarte = new File("src/carte/carte_test_courbe.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(5);
        j.setPosY(6);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.EST);
        j.setVitesse(3);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(6, j.getPosX());
        Assert.assertEquals(6, j.getPosY());
        Assert.assertEquals(0, j.getNbPoints());
        Assert.assertEquals(PointCardinal.EST, j.getDirection());
    }

    @Test
    public void testDeplacementCourbeNord() {
        File fichierCarte = new File("src/carte/carte_test_courbe.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(2);
        j.setPosY(1);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.NORD);
        j.setVitesse(2);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(1, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
        Assert.assertEquals(PointCardinal.OUEST, j.getDirection());
    }

    //si le joueur roule trop vite dans une courbe = accident
    @Test
    public void testDeplacementCourbeNord2() {
        File fichierCarte = new File("src/carte/carte_test_courbe.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(2);
        j.setPosY(1);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.NORD);
        j.setVitesse(3);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(2, j.getPosX());
        Assert.assertEquals(0, j.getPosY());
        Assert.assertEquals(0, j.getNbPoints());
        Assert.assertEquals(PointCardinal.NORD, j.getDirection());
    }

    @Test
    public void testDeplacementCourbeNord3() {
        File fichierCarte = new File("src/carte/carte_test_courbe.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(5);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.NORD);
        j.setVitesse(2);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(1, j.getPosX());
        Assert.assertEquals(4, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
        Assert.assertEquals(PointCardinal.EST, j.getDirection());
    }

    //si le joueur roule trop vite dans une courbe = accident
    @Test
    public void testDeplacementCourbeNord4() {
        File fichierCarte = new File("src/carte/carte_test_courbe.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(0);
        j.setPosY(5);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.NORD);
        j.setVitesse(3);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(4, j.getPosY());
        Assert.assertEquals(0, j.getNbPoints());
        Assert.assertEquals(PointCardinal.NORD, j.getDirection());
    }

    @Test
    public void testDeplacementCourbeSud() {
        File fichierCarte = new File("src/carte/carte_test_courbe.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(6);
        j.setPosY(1);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.SUD);
        j.setVitesse(2);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(7, j.getPosX());
        Assert.assertEquals(2, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
        Assert.assertEquals(PointCardinal.EST, j.getDirection());
    }

    //si le joueur roule trop vite dans une courbe = accident
    @Test
    public void testDeplacementCourbeSud2() {
        File fichierCarte = new File("src/carte/carte_test_courbe.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(6);
        j.setPosY(1);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.SUD);
        j.setVitesse(3);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(6, j.getPosX());
        Assert.assertEquals(2, j.getPosY());
        Assert.assertEquals(0, j.getNbPoints());
        Assert.assertEquals(PointCardinal.SUD, j.getDirection());
    }

    @Test
    public void testDeplacementCourbeOuest() {
        File fichierCarte = new File("src/carte/carte_test_courbe.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(1);
        j.setPosY(4);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.OUEST);
        j.setVitesse(2);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(5, j.getPosY());
        Assert.assertEquals(30, j.getNbPoints());
        Assert.assertEquals(PointCardinal.SUD, j.getDirection());
    }

    //si le joueur roule trop vite dans une courbe = accident
    @Test
    public void testDeplacementCourbeOuest2() {
        File fichierCarte = new File("src/carte/carte_test_courbe.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Joueur j = new Joueur();

        j.setPosX(1);
        j.setPosY(4);
        j.setNbPoints(Constante.STARTPOINTS);
        j.setDirection(PointCardinal.OUEST);
        j.setVitesse(3);

        assert carte != null;
        carte.gestionDeplacements(j);
        Assert.assertEquals(0, j.getPosX());
        Assert.assertEquals(4, j.getPosY());
        Assert.assertEquals(0, j.getNbPoints());
        Assert.assertEquals(PointCardinal.OUEST, j.getDirection());
    }

    @Test
    public void testEvenementRien(){
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(0, 0, PointCardinal.EST, 0, 1);
        List<Evenement> listeAttendue = new ArrayList<>();
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObj(){
        File fichierCarte = new File("src/carte/carte_test_ligne_droite.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(0, 0, PointCardinal.EST, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.OBJECTIF);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementCourb(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(4, 0, PointCardinal.NORD, 0, 1);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.COURBE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjCourb(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(4, 0, PointCardinal.NORD, 4, 0);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.OBJECTIF);
        listeAttendue.add(Evenement.COURBE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementPrio1(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.NORD, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementPrio2(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.SUD, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementPrio3(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.EST, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementPrio4(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.OUEST, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementPrio11(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(3, 4, PointCardinal.NORD, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementPrio21(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(3, 4, PointCardinal.SUD, 0, 0);
        List<Evenement> listeAttendue = new ArrayList<>();
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjPrio1(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.NORD, 1, 2);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.OBJECTIF);
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjPrio2(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.SUD, 1, 2);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.OBJECTIF);
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjPrio3(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.EST, 1, 2);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.OBJECTIF);
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjPrio4(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(1, 2, PointCardinal.OUEST, 1, 2);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.OBJECTIF);
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjPrio11(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(3, 4, PointCardinal.NORD, 3, 4);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.OBJECTIF);
        listeAttendue.add(Evenement.PRIORITE);
        Assert.assertEquals(listeAttendue, liste);
    }

    @Test
    public void testEvenementObjPrio21(){
        File fichierCarte = new File("src/carte/cartetestevent01.txt");
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        List<Evenement> liste = carte.getEvenement(3, 4, PointCardinal.SUD, 3, 4);
        List<Evenement> listeAttendue = new ArrayList<>();
        listeAttendue.add(Evenement.OBJECTIF);
        Assert.assertEquals(listeAttendue, liste);
    }

}
