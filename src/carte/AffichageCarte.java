package carte;

import jeu.Joueur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AffichageCarte {
    private static JFrame affichage;

    public static JFrame getJFrame(){
        if(affichage==null){
            return new JFrame();
        }else{
            return affichage;
        }
    }

    public static void affichageCarte(Case[][] map, int posX, int posY, PointCardinal p){
        affichage = getJFrame();
        affichage.dispose();
        affichage = new JFrame();
        affichage.setLayout(new GridLayout(map.length, map[0].length));
        int i, j, k=0, somme;
        Case current;
        JLabel imageJoueur;

        if(PointCardinal.NORD.equals(p)){
            ImageIcon joueur = new ImageIcon("./src/images/voitureBasHaut.png");
            imageJoueur = new JLabel(joueur);
        }else if(PointCardinal.EST.equals(p)){
            ImageIcon joueur = new ImageIcon("./src/images/voitureGaucheDroite.png");
            imageJoueur = new JLabel(joueur);
        }else if(PointCardinal.SUD.equals(p)){
            ImageIcon joueur = new ImageIcon("./src/images/voitureHautBas.png");
            imageJoueur = new JLabel(joueur);
        }else{
            ImageIcon joueur = new ImageIcon("./src/images/voitureDroiteGauche.png");
            imageJoueur = new JLabel(joueur);
        }

        HashMap<String, JLabel> images = new HashMap<>();
        HashMap<String, JPanel> panels = new HashMap<>();

        for (i=0;i<map.length;i++){
            for(j=0;j<map[0].length;j++){
                panels.put("instance"+k, new JPanel());
                somme=0;
                current = map[i][j];
                if(current instanceof Brouillard){
                    somme=-1;
                }else if(current instanceof Objectif){
                    somme = -2;
                }else if(current instanceof Route){
                    if(((Route) current).getDirections().contains(PointCardinal.NORD)){
                        somme += 1;
                    }
                    if(((Route) current).getDirections().contains(PointCardinal.EST)){
                        somme += 2;
                    }
                    if(((Route) current).getDirections().contains(PointCardinal.SUD)){
                        somme += 4;
                    }
                    if(((Route) current).getDirections().contains(PointCardinal.OUEST)){
                        somme += 8;
                    }
                }

                switch(somme){
                    case -2:
                        images.put("instance"+k, new JLabel(new ImageIcon("./src/images/objectif.png")));
                        break;
                    case -1:
                        images.put("instance"+k, new JLabel(new ImageIcon("./src/images/brouillard.png")));
                        break;
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

    public static void affichageCarte(Case[][] map, ArrayList<Joueur> liste, int posX, int posY){
        affichage = getJFrame();
        affichage.dispose();
        affichage = new JFrame();
        affichage.setLayout(new GridLayout(map.length, map[0].length));
        int i, j, k=0, somme;
        Case current;

        HashMap<String, JLabel> images = new HashMap<>();
        HashMap<String, JPanel> panels = new HashMap<>();

        for (i=0;i<map.length;i++){
            for(j=0;j<map[0].length;j++){
                panels.put("instance"+k, new JPanel());
                somme=0;
                current = map[i][j];

                if(current instanceof Brouillard){
                    somme=-1;
                }else if(current instanceof Objectif||(i==posX&&j==posY)){
                    somme = -2;
                }else if(current instanceof Route){
                    if(((Route) current).getDirections().contains(PointCardinal.NORD)){
                        somme += 1;
                    }
                    if(((Route) current).getDirections().contains(PointCardinal.EST)){
                        somme += 2;
                    }
                    if(((Route) current).getDirections().contains(PointCardinal.SUD)){
                        somme += 4;
                    }
                    if(((Route) current).getDirections().contains(PointCardinal.OUEST)){
                        somme += 8;
                    }
                }

                for (Joueur joueur:liste){
                    if(joueur.getPosY()==i && joueur.getPosX()==j){
                        ImageIcon ima;
                        if(PointCardinal.NORD.equals(joueur.getDirection())){
                            ima = new ImageIcon("./src/images/voitureBasHaut.png");
                            //imageJoueur = new JLabel(joueur);
                        }else if(PointCardinal.EST.equals(joueur.getDirection())){
                            ima = new ImageIcon("./src/images/voitureGaucheDroite.png");
                            //imageJoueur = new JLabel(joueur);
                        }else if(PointCardinal.SUD.equals(joueur.getDirection())){
                            ima = new ImageIcon("./src/images/voitureHautBas.png");
                            //imageJoueur = new JLabel(joueur);
                        }else{
                            ima = new ImageIcon("./src/images/voitureDroiteGauche.png");
                            //imageJoueur = new JLabel(joueur);
                        }
                        images.put("instance"+k, new JLabel(ima));
                        somme = -4;
                    }

                }

                switch(somme){
                    case -2:
                        images.put("instance"+k, new JLabel(new ImageIcon("./src/images/objectif.png")));
                        break;
                    case -1:
                        images.put("instance"+k, new JLabel(new ImageIcon("./src/images/brouillard.png")));
                        break;
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
                /*if(i==map.length/2 && j == map[0].length/2){
                    panels.get("instance"+k).add(imageJoueur, BorderLayout.CENTER);
                }*/
                affichage.add(panels.get("instance"+k));
                k++;
            }
            affichage.setVisible(true);
            affichage.pack();
        }
    }
    /*public void afficherCarteAutourJoueur(Carte carte, Joueur j, ArrayList<Joueur> liste){
        if("test".equals(j.getNom())) {
            AffichageCarte.affichageCarte(carte.GetVision(j, liste));
        }
    }*/

    public static void fermerFenetre(){
        getJFrame().dispose();
    }
}
