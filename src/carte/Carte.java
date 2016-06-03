package carte;

import jeu.Constante;
import jeu.Joueur;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by cokral on 02/05/16.
 */
public class Carte extends Observable  {

    private int longueur; //nb de cases en abcisse
    private int largeur; // nb de cases en ordonnée
    private Case[][] tableau;
    public int posXO;
    public int posYO;

    public Carte (int largeur, int longueur)
    {
        this.largeur = largeur;
        this.longueur = longueur;
        this.tableau = new Case[this.largeur][this.longueur];
    }
    public Carte (){
        this.largeur = 0;
        this.longueur = 0;
        this.tableau = null;
    }

    public int getLargeur()
    {
        return largeur;
    }

    public int getLongueur()
    {
        return longueur;
    }

    public Case[][] getTableau()
    {
        return tableau;
    }

    public void setTableau(Case[][] tableau)
    {
        this.tableau = tableau;
    }

    @Override
    public String toString()
    {
        return "Carte{" +
                "tab=" + Arrays.toString(tableau) +
                '}';
    }

    public Carte(Case [][] tableau)
    {
        this.tableau = tableau;
    }

    public void initFeux(){
    	for(Case[] colonne:tableau){
    		for(Case ligne:colonne){
            	if(ligne instanceof Feu){
            		((Feu)ligne).initFeu();
            		addObserver((Feu)ligne);
            	}
            }
        }
    }

    public void updateFeux(){
    	Feu.nextCycle();
    	setChanged();
    	notifyObservers("feu");
    }

    public void initStops(){
    	for(Case[] colonne:tableau){
    		for(Case ligne:colonne){
            	if(ligne instanceof Stop){
            		((Stop)ligne).initStop();
            	}
            }
        }
    }

    //Fonction utilisée pour récupérer le type d'événement : soit on a atteint l'objectif, soit il y a une priorité à droite, soit il y a une courbe.
    public List<Evenement> getEvenement(int posX, int posY, PointCardinal direction, int posXObjectif, int posYObjectif){
        List<PointCardinal> directionCase;
        List<Evenement> evenements = new ArrayList<>();
        directionCase = ((Route)tableau[posY][posX]).getDirections();


        if(tableau[posY][posX] instanceof Feu){
        	evenements.add(Evenement.FEU);
        }

        if(tableau[posY][posX] instanceof Stop){
        	evenements.add(Evenement.STOP);
        }

        //Si on est sur l'objectif
        System.out.println("posX="+posX+"posY="+posY+"objeX="+posXObjectif+"objeY="+posYObjectif);
        if(posYObjectif == posX && posY == posXObjectif){
            evenements.add(Evenement.OBJECTIF);
        }

        //Si il y a une courbe
        if (directionCase.size()==2 && !directionCase.contains(direction)){
            evenements.add(Evenement.COURBE);
        }

        //Si il y a une priorité
        if (directionCase.size()>2) {
            switch (direction){
                case NORD:
                    if(directionCase.contains(PointCardinal.EST)){
                        evenements.add(Evenement.PRIORITE);
                    }
                    break;
                case EST:
                    if(directionCase.contains(PointCardinal.SUD)){
                        evenements.add(Evenement.PRIORITE);
                    }
                    break;
                case SUD:
                    if(directionCase.contains(PointCardinal.OUEST)){
                        evenements.add(Evenement.PRIORITE);
                    }
                    break;
                case OUEST:
                    if(directionCase.contains(PointCardinal.NORD)){
                        evenements.add(Evenement.PRIORITE);
                    }
                    break;
            }
        }
        return evenements;
    }

    //Fonction qui gère le déplacement d'un joueur j
    public void gestionDeplacements(Joueur j){

        List<PointCardinal> directionCases;


        if (tableau[j.getPosY()][j.getPosX()] instanceof Route) {
            directionCases = ((Route) tableau[j.getPosY()][j.getPosX()]).getDirections();
            if(j.getVitesse()==0){
                if (directionCases.size()>2){
                    setChanged();
                    notifyObservers(j);
                }
            }
        }


        //on va effectuer l'algorithme x fois avec x la vitesse
        for (int i = 0; i < j.getVitesse() ; i++) {
            // La carte est codée de façon : tableau[ligne][colonne] donc on inverse posX et posY
            if (tableau[j.getPosY()][j.getPosX()] instanceof Route){
                //Si c'est une case route on récupère les points cardinaux
                directionCases = ((Route)tableau[j.getPosY()][j.getPosX()]).getDirections();
            }
            else {
                //Sinon ce n'est pas une route, on a un accident, retourne null
                System.out.println("Pas une case route => accident.");
                j.setNbPoints(0);
                return;
            }

            //Si la route n'a que deux points cardinaux
            if(directionCases.size()==2){
                //Si ce n'est pas une route droite
                if(!directionCases.contains(j.getDirection())){
                    //Si la vitesse est supérieure à 2
                    if(j.getVitesse()>2){
                        System.out.println("Le joueur : " + j.getNom() + " roule trop vite dans un virage et a un accident.");
                        j.setNbPoints(0);
                        return;
                    }
                    //Changement de direction du joueur si c'est une courbe et qu'il va a une vitesse acceptée.
                    //Parcours des deux points cardinaux de la case
                    for (PointCardinal dir:directionCases) {
                        //Si le joueur va vers l'est
                        if(PointCardinal.EST.equals(j.getDirection())){
                            //Si la direction qu'on regarde n'est pas celle d'où l'on vient
                            if (directionCases.contains(PointCardinal.OUEST) && !PointCardinal.OUEST.equals(dir)){
                                j.setDirection(dir);
                                break;
                            }
                        }
                        //Sinon, de même avec autres directions
                        else if(PointCardinal.NORD.equals(j.getDirection())){
                            if (directionCases.contains(PointCardinal.SUD) && !PointCardinal.SUD.equals(dir)){
                                j.setDirection(dir);
                                break;
                            }
                        }
                        else if(PointCardinal.SUD.equals(j.getDirection())){
                            if (directionCases.contains(PointCardinal.NORD) && !PointCardinal.NORD.equals(dir)){
                                j.setDirection(dir);
                                break;
                            }
                        }
                        else if(PointCardinal.OUEST.equals(j.getDirection())){
                            if (directionCases.contains(PointCardinal.EST) && !PointCardinal.EST.equals(dir)){
                                j.setDirection(dir);
                                break;
                            }
                        }
                    }
                }
            }
            else if (directionCases.size()>2){
                setChanged();
        		notifyObservers(j);
            }

            //Ensuite on effectue les tests pour chaque direction du joueur.
            if (PointCardinal.SUD.equals(j.getDirection())) {
                if (directionCases.contains(PointCardinal.SUD)){
                    System.out.println("Déplacement vers le sud effectué.");
                    j.setPosY(j.getPosY()+1);

                } else {
                    System.out.println("Le joueur : " + j.getNom() + " essaye d'aller sur une case neutre et a un accident.");
                    j.setNbPoints(0);
                    return;
                }
            }
            else if (PointCardinal.NORD.equals(j.getDirection())){
                if (directionCases.contains(PointCardinal.NORD)){
                    System.out.println("Déplacement vers le nord effectué.");
                    j.setPosY(j.getPosY()-1);

                } else {
                    System.out.println("Le joueur : " + j.getNom() + " essaye d'aller sur une case neutre et a un accident.");
                    j.setNbPoints(0);
                    return;
                }
            }
            else if (PointCardinal.EST.equals(j.getDirection())){
                if (directionCases.contains(PointCardinal.EST)){
                    System.out.println("Déplacement vers l'est effectué.");
                    j.setPosX(j.getPosX()+1);

                } else {
                    System.out.println("Le joueur : " + j.getNom() + " essaye d'aller sur une case neutre et a un accident.");
                    j.setNbPoints(0);
                    return;
                }
            }
            else if (PointCardinal.OUEST.equals(j.getDirection())){
                if (directionCases.contains(PointCardinal.OUEST)){
                    System.out.println("Déplacement vers l'ouest effectué.");
                    j.setPosX(j.getPosX()-1);

                } else {
                    System.out.println("Le joueur : " + j.getNom() + " essaye d'aller sur une case neutre et a un accident.");
                    j.setNbPoints(0);
                    return;
                }
            }
            else {
                System.out.println("\n ERREUR DIRECTION DU JOUEUR " + j.getNom() +" = NULL \n");
            }
            System.out.println("Position : "+j.getPosX()+","+j.getPosY());
            System.out.println("joueur ="+j.getPosX()+", "+j.getPosY()+", obj="+posXO+","+posYO);
            if(j.getPosY()==posXO && j.getPosX()==posYO){
                setChanged();
                notifyObservers(j);
            }
        }

    }
    
    public String getVision(Joueur player, ArrayList<Joueur> liste){
    	Case[][] vision;
    	int rayonVision = Constante.DISTANCEVISION;
    	int diametreVision = rayonVision*2+1;
    	int i, j, posXRelative, posYRelative;
    	vision = new Case[largeur][longueur];
        String carteDesc;
        carteDesc="";

        for(i=0;i<largeur;i++){
    		for(j=0;j<longueur;j++){

    			//posXRelative=(player.getPosX()+(i-rayonVision));
                //System.out.println("POSX relative ="+posXRelative);
                //posYRelative=(player.getPosY()+(j-rayonVision));
                //System.out.println("POSY relative ="+posYRelative);
                if(i==posXO && j==posYO){
                    if (tableau[i][j] instanceof Route) {
                        //vision[i][j]=new Objectif(posXO,posYO,(((Route)tableau[i][j]).getDirections()));
                        carteDesc=carteDesc+"X";
                    }
                }
                if(((int)Math.sqrt((Math.pow(j-player.getPosX(), 2))+(Math.pow(i-player.getPosY(), 2))))<=rayonVision){

                    for (Joueur joueur: liste){
                        if(i==joueur.getPosY() && j==joueur.getPosX() && !(joueur.equals(player))){
                            carteDesc=carteDesc+"V";
                        }
                    }
                    //vision[i][j] = tableau[j][i];
                    carteDesc=carteDesc+tableau[i][j];

                    if(j==player.getPosX() && i==player.getPosY()){
                        carteDesc=carteDesc+"Y";
                    }

    			}else{
                    carteDesc = carteDesc + "B";

    				vision[i][j]=new Brouillard();
    			}
                if(j<longueur-1) {
                    carteDesc = carteDesc + ",";
                }


    		}
            carteDesc=carteDesc+";";
    	}
    	return carteDesc;
    }
    

}
