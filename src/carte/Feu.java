package carte;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class Feu extends Route implements Observer {
	private HashMap<PointCardinal, Integer> feux;
	private static int cycle;

	Integer couleurNorthSouth;
	Integer couleurWestEast;
	public Feu(int posx, int posy, List<PointCardinal> directions) {
		super(posx, posy, directions);
		cycle = 0;
		feux = new HashMap<PointCardinal, Integer>();
		
	}
	
	public void initFeu(){
		//System.out.println(directions.size());
		if (directions.size()>=3){
			double a = Math.random();
			if(a<0.5){
				couleurNorthSouth = 0;
				couleurWestEast = 2;
			}else{
				couleurNorthSouth = 2;
				couleurWestEast = 0;
			}
			for (PointCardinal pc:directions){
				
				if(pc==PointCardinal.NORTH){
					feux.put(PointCardinal.NORTH, couleurNorthSouth);
				}else if(pc==PointCardinal.EAST){
					feux.put(PointCardinal.EAST, couleurWestEast);
				}else if(pc==PointCardinal.SOUTH){
					feux.put(PointCardinal.SOUTH, couleurNorthSouth);
				}else{
					feux.put(PointCardinal.WEST, couleurWestEast);
				}
			}
		}
		System.out.println("feu créé : ns = "+couleurNorthSouth+", ew = "+couleurWestEast);
		System.out.println("Feu nord/sud = "+feux.get(PointCardinal.SOUTH));
		System.out.println("Feu ouest/est = "+feux.get(PointCardinal.EAST));
	}
	
	public static void nextCycle(){
		cycle = (cycle+1)%6;
		System.out.println(cycle);
	}
	
	public void setFeux(int northSouth, int westEast){
		System.out.println("update feux "+cycle);
		
		feux.replace(PointCardinal.NORTH, northSouth);
		feux.replace(PointCardinal.EAST, westEast);
		feux.replace(PointCardinal.SOUTH, northSouth);
		feux.replace(PointCardinal.WEST, westEast);
		PointCardinal current;
		Set<PointCardinal> cles = feux.keySet();
		Iterator<PointCardinal> it = cles.iterator();
		while (it.hasNext()){
			current = it.next();
			System.out.println("Feu "+current+" = "+feux.get(current));
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if("feu".equals(arg)){
			switch(cycle){
			case 0:
				setFeux((couleurNorthSouth)%3 , (couleurWestEast)%3);
				break;
			
			case 1:
				setFeux((couleurNorthSouth)%3 , (couleurWestEast)%3);
				break;
				
			case 2:
				if(couleurNorthSouth==2){
					setFeux((couleurNorthSouth)%3 , (couleurWestEast+1)%3);
				}else{
					setFeux((couleurNorthSouth+1)%3 , (couleurWestEast)%3);
				}
				break;
				
			case 3:
				if(couleurNorthSouth==2){
					setFeux((couleurNorthSouth+1)%3 , (couleurWestEast+2)%3);
				}else{
					setFeux((couleurNorthSouth+2)%3 , (couleurWestEast+1)%3);
				}
				break;
				
			case 4:
				if(couleurNorthSouth==2){
					setFeux((couleurNorthSouth+1)%3 , (couleurWestEast+2)%3);
				}else{
					setFeux((couleurNorthSouth+2)%3 , (couleurWestEast+1)%3);
				}
				break;
				
			case 5:
				setFeux((couleurNorthSouth+2)%3, (couleurWestEast+2)%3);
				break;
			}
		}
	}
	
	public boolean getCouleurFeu(PointCardinal direction){
		return feux.get(direction)==2;
	}
	
}
