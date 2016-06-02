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

    //Fonction utilisée pour récupérer le type d'événement : soit on a atteint l'objectif, soit il y a une priorité à droite, soit il y a une courbe.
    public List<Evenement> getEvenement(int posX, int posY, PointCardinal direction, int posXObjectif, int posYObjectif){
        List<PointCardinal> directionCase = new ArrayList<>();
        List<Evenement> evenements = new ArrayList<>();
        directionCase = ((List<PointCardinal>)((Route)tableau[posY][posX]).getDirections());

        //Si on est sur l'objectif
        if(posXObjectif == posX && posY == posYObjectif){
            evenements.add(Evenement.OBJECTIF);
        }

        //Si il y a une courbe
        if (directionCase.size()==2 && !directionCase.contains(direction)){
            evenements.add(Evenement.COURBE);
        }

        //Si il y a une priorité
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

    //Fonction qui gère le déplacement d'un joueur j
    public void gestionDeplacements(Joueur j){

        //Si la direction du joueur est nulle, on quitte directement la fonction
        if(j.getDirection().equals(null)){
            System.out.println("Pas de direction, ça a aucun sens mais bon...");
            return;
        }

        List<PointCardinal> directionCases = new ArrayList<>();

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
                        System.out.println("Le joueur : " + j.getNom() + " roule trop vite dans un virage et a un accident.");
                        j.setNbPoints(0);
                        return;
                    }
                    //Changement de direction du joueur si c'est une courbe et qu'il va a une vitesse acceptée.
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

            //Ensuite on effectue les tests pour chaque direction du joueur.
            if (PointCardinal.SOUTH.equals(j.getDirection())) {
                if (directionCases.contains(PointCardinal.SOUTH)){
                    System.out.println("Déplacement vers le sud effectué.");
                    j.setPosY(j.getPosY()+1);

                } else {
                    System.out.println("Le joueur : " + j.getNom() + " essaye d'aller sur une case neutre et a un accident.");
                    j.setNbPoints(0);
                    return;
                }
            }
            else if (PointCardinal.NORTH.equals(j.getDirection())){
                if (directionCases.contains(PointCardinal.NORTH)){
                    System.out.println("Déplacement vers le nord effectué.");
                    j.setPosY(j.getPosY()-1);

                } else {
                    System.out.println("Le joueur : " + j.getNom() + " essaye d'aller sur une case neutre et a un accident.");
                    j.setNbPoints(0);
                    return;
                }
            }
            else if (PointCardinal.EAST.equals(j.getDirection())){
                if (directionCases.contains(PointCardinal.EAST)){
                    System.out.println("Déplacement vers l'est effectué.");
                    j.setPosX(j.getPosX()+1);

                } else {
                    System.out.println("Le joueur : " + j.getNom() + " essaye d'aller sur une case neutre et a un accident.");
                    j.setNbPoints(0);
                    return;
                }
            }
            else if (PointCardinal.WEST.equals(j.getDirection())){
                if (directionCases.contains(PointCardinal.WEST)){
                    System.out.println("Déplacement vers l'ouest effectué.");
                    j.setPosX(j.getPosX()-1);

                } else {
                    System.out.println("Le joueur : " + j.getNom() + " essaye d'aller sur une case neutre et a un accident.");
                    j.setNbPoints(0);
                    return;
                }
            }

            //On appelle le controleur qui va vérifier la présence ou non d'événements sur cette case et agir en conséquence.
            setChanged();
            notifyObservers(j);
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
    	JFrame frame = new JFrame();
    	frame.setLayout(new GridLayout(map.length, map[0].length));
    	int i, j, k=0, somme;
    	Case current;
    	JLabel imageJoueur;
    	
    	if(PointCardinal.NORTH.equals(player.getDirection())){
    		ImageIcon joueur = new ImageIcon("E:/Cours/projet java/projet voiture/voitureBasHaut.png");
    		imageJoueur = new JLabel(joueur);
		}else if(PointCardinal.EAST.equals(player.getDirection())){
			ImageIcon joueur = new ImageIcon("E:/Cours/projet java/projet voiture/voitureGaucheDroite.png");
			imageJoueur = new JLabel(joueur);
		}else if(PointCardinal.SOUTH.equals(player.getDirection())){
			ImageIcon joueur = new ImageIcon("E:/Cours/projet java/projet voiture/voitureHautBas.png");
			imageJoueur = new JLabel(joueur);
		}else{
			ImageIcon joueur = new ImageIcon("E:/Cours/projet java/projet voiture/voitureDroiteGauche.png");
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
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/neutre.png")));
    				break;
    				
    				//0001 nord
    			case 1:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/routeN.png")));
    				break;
    				
    				//0010 est
    			case 2:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/routeE.png")));
    				break;
    				
    				//0011 est+nord
    			case 3:

        			images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/routeHautDroite.png"))); 
    				break;
    				
    				//0100 sud
    			case 4:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/routeS.png")));
    				break;
    				
    				//0101 sud+nord
    			case 5:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/routeHautBas.png")));
    				break;
    				
    				//0110 sud+est
    			case 6:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/routeBasDroite.png")));
    				break;
    				
    				//0111 sud+est+nord
    			case 7:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/enTDroite.png")));
    				break;
    				
    				//1000 ouest
    			case 8:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/routeW.png")));
    				break;
    				
    				//1001 ouest+nord
    			case 9:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/routeHautGauche.png")));
    				break;
    				
    				//1010 ouest+est
    			case 10:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/routeGaucheDroite.png")));
    				break;
    				
    				//1011 ouest+est+nord
    			case 11:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/enTHaut.png")));
    				break;
    				
    				//1100 ouest+sud
    			case 12:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/routeBasGauche.png")));
    				break;
    				
    				//1101 ouest+sud+nord
    			case 13:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/enTGauche.png")));
    				break;
    				
    				//1110 ouest+sud+est
    			case 14:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/enTBas.png")));
    				break;
    				
    				//1111 ouest+sud+est+nord
    			case 15:
    				images.put("instance"+k, new JLabel(new ImageIcon("E:/Cours/projet java/projet voiture/croisement.png")));
    				break;
    			}
    			
    			
    			
    			panels.get("instance"+k).setLayout(new BorderLayout());
    			panels.get("instance"+k).add(images.get("instance"+k), BorderLayout.CENTER);
    			if(i==map.length/2 && j == map[0].length/2){
    				panels.get("instance"+k).add(imageJoueur, BorderLayout.CENTER);
    			}
    			frame.add(panels.get("instance"+k));
    			k++;
    		}
    		frame.getContentPane().setBackground(Color.green);
    		frame.setVisible(true);
    		frame.pack();
    	}
    	
    }
}
