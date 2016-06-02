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
				listeStops.add(PointCardinal.SOUTH);
				listeStops.add(PointCardinal.NORTH);
			}else{
				listeStops.add(PointCardinal.WEST);
				listeStops.add(PointCardinal.EAST);
			}
		}else if(directions.size()==3){
			if(!directions.contains(PointCardinal.NORTH)){
				listeStops.add(PointCardinal.SOUTH);
			}else if(!directions.contains(PointCardinal.EAST)){
				listeStops.add(PointCardinal.WEST);
			}else if(!directions.contains(PointCardinal.SOUTH)){
				listeStops.add(PointCardinal.NORTH);
			}else{
				listeStops.add(PointCardinal.EAST);
			}
		}
		for (PointCardinal pc : listeStops){
			System.out.println("pc = "+pc);
		}
	}
	
	public boolean isAStopDirection(PointCardinal direction){
		if(directions.size()==4){
			return listeStops.contains(direction);
		}else{
			if(PointCardinal.NORTH.equals(direction)){
				return listeStops.contains(PointCardinal.SOUTH);
			}else if(PointCardinal.EAST.equals(direction)){
				return listeStops.contains(PointCardinal.WEST);
			}else if(PointCardinal.SOUTH.equals(direction)){
				return listeStops.contains(PointCardinal.NORTH);
			}else{
				return listeStops.contains(PointCardinal.EAST);
			}
		}
	}
}
