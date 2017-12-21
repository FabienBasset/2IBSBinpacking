package Projet;

import java.util.ArrayList;

public class Skyline {
	private static ArrayList<Point> Skyline = new ArrayList<Point>();
	private static Volume V = new Volume(0,0,0,2,2,0);
	private static Volume V2 = new Volume(2,0,0,1,3,0);
	private static Volume V3 = new Volume(3,0,0,2,1,0);
	private static Volume V4 = new Volume(5,0,0,1,2,0);
	private static Volume V5 = new Volume(6,0,0,2,4,0);
	private static Volume Conteneur = new Volume(0,0,0,10,5,0);
	private static Volume[] Objet_Plac� = new Volume[4];

	public static void main(String[] args) {
		Objet_Plac�[0]=V;
		Objet_Plac�[1]=V2;
		Objet_Plac�[2]=V3;
		Objet_Plac�[3]=V4;
		Objet_Plac�[4]=V5;
		Skyline = Trouver_SkyLine(Objet_Plac�, Conteneur);
		int i = 0;
		for (Point pt : Skyline) {
			System.out.println("P" + i + " : (" + pt.getX() + ";" + pt.getY() + ")");
			i++;
		}
	}
	
	@SuppressWarnings("null")
	public static ArrayList<Point> Trouver_SkyLine(Volume[] Objet_Plac�, Volume Conteneur ) {
		  //Point[] Skyline;
		  int i = 0 ;
		  Point tmp = null;
		  tmp.setX(0);
		  tmp.setY(0);
		  //Skyline[i]=tmp;
		  Skyline.add(tmp);
		  double Unit = 0.001;
		  Volume Extremit�;

		  for (double c=0 ; c <= Conteneur.getLargeur() ; c = c + Unit) {
			  double HauteurMax = 0;
			  Extremit� = null;
			  for (Volume Objet : Objet_Plac�) {
				  if (c >= Objet.getX() && c <= Objet.getX() + Objet.getLargeur()) {
					  if (Objet.getHauteur() > HauteurMax) {
						  HauteurMax = Objet.getHauteur();
						  if (c == Objet.getX() + Objet.getLargeur()) {
							  Extremit� = Objet;
						  }
					  }
				  }
			  }

			  if (i == 0) {
				  tmp.setX(0);
				  tmp.setY(0);
				  //Skyline[i] = tmp;
				  Skyline.add(tmp);
				  i++;
			  } else if (Skyline.get(i-1).getY() < HauteurMax) {
				  tmp.setX(c);
				  tmp.setY(Skyline.get(i-1).getX());
				  //Skyline[i] = tmp;
				  Skyline.add(tmp);
				  i++;
				  tmp.setX(c);
				  tmp.setY(HauteurMax);
				//Skyline[i] = tmp;
				  Skyline.add(tmp);
				  i++;
			  }
			
			  if (Extremit� != null) {
				  tmp.setX(c);
				  tmp.setY(0);
				  Extremit� = null ;
				  //Skyline[i] =tmp ;
				  Skyline.add(tmp);
				  i++ ;
			  }
		  }
		  return Skyline;
		}
}
