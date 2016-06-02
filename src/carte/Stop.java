package carte;

import java.util.ArrayList;
import java.util.List;

public class Stop extends Route {
	
	List<PointCardinal> listeStops;

	public Stop(int posx, int posy, List<PointCardinal> directions) {
		super(posx, posy, directions);
		listeStops = new ArrayList<>();
	}
	
	public void initStop(){
		if (directions.size()>3){
			double a = Math.random();
			if(a<0.5){
				listeStops.add(PointCardinal.SUD);
				listeStops.add(PointCardinal.NORD);
			}else{
				listeStops.add(PointCardinal.OUEST);
				listeStops.add(PointCardinal.EST);
			}
		}else if(directions.size()==3){
			if(!directions.contains(PointCardinal.NORD)){
				listeStops.add(PointCardinal.SUD);
			}else if(!directions.contains(PointCardinal.EST)){
				listeStops.add(PointCardinal.OUEST);
			}else if(!directions.contains(PointCardinal.SUD)){
				listeStops.add(PointCardinal.NORD);
			}else{
				listeStops.add(PointCardinal.EST);
			}
		}
		/*for (PointCardinal pc : listeStops){
			System.out.println("pc = "+pc);
		}*/
	}
	
	public boolean isAStopDirection(PointCardinal direction){
		if(directions.size()==4){
			return listeStops.contains(direction);
		}else{
			if(PointCardinal.NORD.equals(direction)){
				return listeStops.contains(PointCardinal.SUD);
			}else if(PointCardinal.EST.equals(direction)){
				return listeStops.contains(PointCardinal.OUEST);
			}else if(PointCardinal.SUD.equals(direction)){
				return listeStops.contains(PointCardinal.NORD);
			}else{
				return listeStops.contains(PointCardinal.EST);
			}
		}
	}
}
