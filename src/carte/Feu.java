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

	private Integer couleurNORDSUD;
	private Integer couleurOUESTEST;
	public Feu(int posx, int posy, List<PointCardinal> directions) {
		super(posx, posy, directions);
		cycle = 0;
		feux = new HashMap<>();

	}

	public void initFeu(){
		if (directions.size()>=3){
			double a = Math.random();
			if(a<0.5){
				couleurNORDSUD = 0;
				couleurOUESTEST = 2;
			}else{
				couleurNORDSUD = 2;
				couleurOUESTEST = 0;
			}
			for (PointCardinal pc:directions){

				if(pc==PointCardinal.NORD){
					feux.put(PointCardinal.NORD, couleurNORDSUD);
				}else if(pc==PointCardinal.EST){
					feux.put(PointCardinal.EST, couleurOUESTEST);
				}else if(pc==PointCardinal.SUD){
					feux.put(PointCardinal.SUD, couleurNORDSUD);
				}else{
					feux.put(PointCardinal.OUEST, couleurOUESTEST);
				}
			}
		}
	}

	public static void nextCycle(){
		cycle = (cycle+1)%6;
	}

	public void setFeux(int NORDSUD, int OUESTEST){
		feux.put(PointCardinal.NORD, NORDSUD);
		feux.put(PointCardinal.EST, OUESTEST);
		feux.put(PointCardinal.SUD, NORDSUD);
		feux.put(PointCardinal.OUEST, OUESTEST);

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if("feu".equals(arg)){
			switch(cycle){
			case 0:
				setFeux((couleurNORDSUD)%3 , (couleurOUESTEST)%3);
				break;

			case 1:
				setFeux((couleurNORDSUD)%3 , (couleurOUESTEST)%3);
				break;

			case 2:
				if(couleurNORDSUD==2){
					setFeux((couleurNORDSUD)%3 , (couleurOUESTEST+1)%3);
				}else{
					setFeux((couleurNORDSUD+1)%3 , (couleurOUESTEST)%3);
				}
				break;

			case 3:
				if(couleurNORDSUD==2){
					setFeux((couleurNORDSUD+1)%3 , (couleurOUESTEST+2)%3);
				}else{
					setFeux((couleurNORDSUD+2)%3 , (couleurOUESTEST+1)%3);
				}
				break;

			case 4:
				if(couleurNORDSUD==2){
					setFeux((couleurNORDSUD+1)%3 , (couleurOUESTEST+2)%3);
				}else{
					setFeux((couleurNORDSUD+2)%3 , (couleurOUESTEST+1)%3);
				}
				break;

			case 5:
				setFeux((couleurNORDSUD+2)%3, (couleurOUESTEST+2)%3);
				break;
			}
		}
	}
	
	public boolean getCouleurFeu(PointCardinal direction){
		return feux.get(direction)==2;
	}


	public String toString(){
		String desc = "F";
		int i;
        for (PointCardinal pc:directions){

			if(pc==PointCardinal.NORD){
				desc=desc+"n=";
                i=feux.get(pc);
			}else if(pc==PointCardinal.EST){
                desc=desc+"e=";
                i=feux.get(pc);
			}else if(pc==PointCardinal.SUD){
                desc=desc+"s=";
                i=feux.get(pc);
			}else{
                desc=desc+"o=";
                i=feux.get(pc);
			}
            if(i==0){
                desc=desc+"v";
            }else if(i==1){
                desc=desc+"o";
            }else if(i==2){
                desc=desc+"r";
            }
		}
		return desc;
	}
}
