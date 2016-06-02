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

    public List<Evenement> getEvenement(int posX, int posY, PointCardinal direction, int posXObjectif, int posYObjectif){
        List<PointCardinal> directionCase = new ArrayList<>();
        List<Evenement> evenements = new ArrayList<>();
        directionCase = ((List<PointCardinal>)((Route)tableau[posY][posX]).getDirections());

        //System.out.println(direction);
        //System.out.println(directionCase);

        if(tableau[posY][posX] instanceof Feu){
        	evenements.add(Evenement.FEU);
        }
        
        if(tableau[posY][posX] instanceof Stop){
        	evenements.add(Evenement.STOP);
        }

        if(posXObjectif == posX && posY == posYObjectif){
            evenements.add(Evenement.OBJECTIF);
        }

        if (directionCase.size()==2 && !directionCase.contains(direction)){
            evenements.add(Evenement.COURBE);
        }

        if (directionCase.size()>2) {
            switch (direction){
                case NORTH:
                    if(directionCase.contains(PointCardinal.EAST)){
                        evenements.add(Evenement.PRIORITE);
                    }
                    break;
                case EAST:
                    if(directionCase.contains(PointCardinal.SOUTH)){
                        evenements.add(Evenement.PRIORITE);
                    }
                    break;
                case SOUTH:
                    if(directionCase.contains(PointCardinal.WEST)){
                        evenements.add(Evenement.PRIORITE);
                    }
                    break;
                case WEST:
                    if(directionCase.contains(PointCardinal.NORTH)){
                        evenements.add(Evenement.PRIORITE);
                    }
                    break;
            }
        }
        return evenements;
    }

    // Prend en paramètre : la vitesse du joueur (pour savoir sur combien de case appliquer la direction,
    // la position en X et en Y du joueur
    // la direction du joueur
    public void gestionDeplacements(Joueur j){

        if(j.getDirection().equals(null)){
            System.out.println("Pas de direction, ça a aucun sens mais bon...");
            return;
        }

        List<PointCardinal> directionCases = new ArrayList<>();
        directionCases = ((List<PointCardinal>)((Route)tableau[j.getPosY()][j.getPosX()]).getDirections());
        if(j.getVitesse()==0){
        	if (directionCases.size()>2){
        		System.out.println("heyo");
            	setChanged();
        		notifyObservers(j);
            }
        }
        
        
        //on va effectuer l'algorithme x fois avec x la vitesse
        for (int i = 0; i < j.getVitesse() ; i++) {
            // La carte est codée de façon : tableau[ligne][colonne] donc on inverse posX et posY
            if (tableau[j.getPosY()][j.getPosX()] instanceof Route){
                //Si c'est une case route on récupère les points cardinaux
                directionCases = ((List<PointCardinal>)((Route)tableau[j.getPosY()][j.getPosX()]).getDirections());
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
                        j.setEtatMarche(false);
                        return;
                    }
                    //Parcours des deux points cardinaux de la case
                    for (PointCardinal dir:directionCases) {
                        //Si le joueur va vers l'est
                        if(j.getDirection().equals(PointCardinal.EAST)){
                            //Si la direction qu'on regarde n'est pas celle d'où l'on vient
                            if (dir!=PointCardinal.WEST){
                                j.setDirection(dir);
                            }
                        }
                        //Sinon, de même avec autres directions
                        else if(j.getDirection().equals(PointCardinal.NORTH)){
                            if (dir!=PointCardinal.SOUTH){
                                j.setDirection(dir);
                            }
                        }
                        if(j.getDirection().equals(PointCardinal.SOUTH)){
                            if (dir!=PointCardinal.NORTH){
                                j.setDirection(dir);
                            }
                        }
                        if(j.getDirection().equals(PointCardinal.WEST)){
                            if (dir!=PointCardinal.EAST){
                                j.setDirection(dir);
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
            if (j.getDirection().equals(PointCardinal.SOUTH)) {
                if (directionCases.contains(PointCardinal.SOUTH)){
                    System.out.println("Déplacement vers le sud effectué.");
                    j.setPosY(j.getPosY()+1);

                } else {
                    System.out.println("Impossible d'aller dans cette direction.");
                    j.setNbPoints(0);
                    return;
                }
            }
            else if (j.getDirection().equals(PointCardinal.NORTH)){
                if (directionCases.contains(PointCardinal.NORTH)){
                    System.out.println("Déplacement vers le nord effectué.");
                    j.setPosY(j.getPosY()-1);

                } else {
                    System.out.println("Impossible d'aller dans cette direction.");
                    j.setNbPoints(0);
                    return;
                }
            }
            else if (j.getDirection().equals(PointCardinal.EAST)){
                if (directionCases.contains(PointCardinal.EAST)){
                    System.out.println("Déplacement vers l'est effectué.");
                    j.setPosX(j.getPosX()+1);

                } else {
                    System.out.println("Impossible d'aller dans cette direction.");
                    j.setNbPoints(0);
                    return;
                }
            }
            else if (j.getDirection().equals(PointCardinal.WEST)){
                if (directionCases.contains(PointCardinal.WEST)){
                    System.out.println("Déplacement vers l'ouest effectué.");
                    j.setPosX(j.getPosX()-1);

                } else {
                    System.out.println("Impossible d'aller dans cette direction.");
                    j.setNbPoints(0);
                    return;
                }
            }
            else {
                System.out.println("\n ERREUR DIRECTION DU JOUEUR " + j.getNom() +" = NULL \n");
            }
            System.out.println("Position : "+j.getPosX()+","+j.getPosY());
        }
    }
    
    public Case[][] GetVision(int posX, int posY, Joueur player){
    	Case[][] vision;
    	int rayonVision = Constante.DISTANCEVISION;
    	int diametreVision = rayonVision*2+1;
    	int i, j, posXRelative, posYRelative;
    	vision = new Case[diametreVision][diametreVision];
    	
    	for(i=0;i<diametreVision;i++){
    		for(j=0;j<diametreVision;j++){
    			posXRelative=(posX+(i-rayonVision));
    			posYRelative=(posY+(j-rayonVision));
    			if((posXRelative>=0) && (posYRelative>= 0) && (posXRelative< this.largeur) && (posYRelative< this.longueur)){
    				vision[i][j]=tableau[posXRelative][posYRelative];
    			}else{
    				vision[i][j]=new CaseNeutre();
    			}
    		}
    	}
    	
    	return vision;
    }
    
    public void affichageCarte(Case[][] map, Joueur player){
    	affichage = new JFrame();
    	affichage.setLayout(new GridLayout(map.length, map[0].length));
    	int i, j, k=0, somme;
    	Case current;
    	JLabel imageJoueur;
    	
    	if(player.getDirection().equals(PointCardinal.NORTH)){
    		ImageIcon joueur = new ImageIcon("./src/images/voitureBasHaut.png");
    		imageJoueur = new JLabel(joueur);
		}else if(player.getDirection().equals(PointCardinal.EAST)){
			ImageIcon joueur = new ImageIcon("./src/images/voitureGaucheDroite.png");
			imageJoueur = new JLabel(joueur);
		}else if(player.getDirection().equals(PointCardinal.SOUTH)){
			ImageIcon joueur = new ImageIcon("./src/images/voitureHautBas.png");
			imageJoueur = new JLabel(joueur);
		}else{
			ImageIcon joueur = new ImageIcon("./src/images/voitureDroiteGauche.png");
			imageJoueur = new JLabel(joueur);
		}
    	
    	HashMap<String, JLabel> images = new HashMap<String, JLabel>();
    	HashMap<String, JPanel> panels = new HashMap<String, JPanel>();
    	 
    	for (i=0;i<map.length;i++){
    		for(j=0;j<map[0].length;j++){
    			panels.put("instance"+k, new JPanel());
    			somme=0;
    			current = map[i][j];
    			if(current instanceof Route){
    				if(((Route) current).getDirections().contains(PointCardinal.NORTH)){
    					somme += 1;
    				}
    				if(((Route) current).getDirections().contains(PointCardinal.EAST)){
    					somme += 2;
    				}
    				if(((Route) current).getDirections().contains(PointCardinal.SOUTH)){
    					somme += 4;
    				}
    				if(((Route) current).getDirections().contains(PointCardinal.WEST)){
    					somme += 8;
    				}
    			}
    			
    			switch(somme){
    				//0000 neutre
    			case 0:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/neutre.png")));
    				break;
    				
    				//0001 nord
    			case 1:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/routeN.png")));
    				break;
    				
    				//0010 est
    			case 2:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/routeE.png")));
    				break;
    				
    				//0011 est+nord
    			case 3:

        			images.put("instance"+k, new JLabel(new ImageIcon("./src/images/routeHautDroite.png"))); 
    				break;
    				
    				//0100 sud
    			case 4:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/routeS.png")));
    				break;
    				
    				//0101 sud+nord
    			case 5:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/routeHautBas.png")));
    				break;
    				
    				//0110 sud+est
    			case 6:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/routeBasDroite.png")));
    				break;
    				
    				//0111 sud+est+nord
    			case 7:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/enTDroite.png")));
    				break;
    				
    				//1000 ouest
    			case 8:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/routeW.png")));
    				break;
    				
    				//1001 ouest+nord
    			case 9:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/routeHautGauche.png")));
    				break;
    				
    				//1010 ouest+est
    			case 10:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/routeGaucheDroite.png")));
    				break;
    				
    				//1011 ouest+est+nord
    			case 11:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/enTHaut.png")));
    				break;
    				
    				//1100 ouest+sud
    			case 12:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/routeBasGauche.png")));
    				break;
    				
    				//1101 ouest+sud+nord
    			case 13:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/enTGauche.png")));
    				break;
    				
    				//1110 ouest+sud+est
    			case 14:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/enTBas.png")));
    				break;
    				
    				//1111 ouest+sud+est+nord
    			case 15:
    				images.put("instance"+k, new JLabel(new ImageIcon("./src/images/croisement.png")));
    				break;
    			}
    			
    			
    			
    			panels.get("instance"+k).setLayout(new BorderLayout());
    			panels.get("instance"+k).add(images.get("instance"+k), BorderLayout.CENTER);
    			if(i==map.length/2 && j == map[0].length/2){
    				panels.get("instance"+k).add(imageJoueur, BorderLayout.CENTER);
    			}
    			affichage.add(panels.get("instance"+k));
    			k++;
    		}
    		affichage.setVisible(true);
    		affichage.pack();
    	}
    }
}
