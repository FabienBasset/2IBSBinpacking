import java.util.ArrayList;
import java.util.List;


public class Volume {
		private double x;
		private double y;
		private double z;
		private double hauteur;
		private double largeur;
		private double profondeur;
		private static int id_next = 0;
		private int id;
		
		public Volume (double x, double y, double z, double hauteur, double largeur, double profondeur)
		{
			this.x = x;
			this.y = y;
			this.hauteur = hauteur;
			this.largeur = largeur;
			this.profondeur = profondeur;
			this.z = z;
			this.id = this.id_next++;
		}
		
		public double getSurfaceMax(){ 
			double a1 = this.hauteur * this.largeur;
			double a2 = this.hauteur * this.profondeur;
			double a3 = this.largeur * this.profondeur;
			if(a1>a2 && a1>a3)
				return a1;	
			else if(a2>a1 && a2>a3)
				return a2;
			else
				return a3;
		}
		public double getVolume(){  return this.hauteur * this.largeur * this.profondeur;		}
		
		public double getX() 			{ return x; }
		public void setX(int x) 	{ this.x = x; }
	
		public double getY() 			{ return y; }
		public void setY(int y) 	{ this.y = y; }
	
		public double getZ()			{ return z; }
		public double setZ()			{ return z; }
		
		public double getHauteur() 	{ return hauteur; }
		public double getLargeur() 	{ return largeur; }
		public double getProfondeur() {	return profondeur; }	
		public int getID() {	return id; }

		
		public static Volume[] generateurVolume (int nombreObjet, int largeur_min, int largeur_max, int hauteur_min, int hauteur_max, int profondeur_min, int profondeur_max)
		{
			Volume[] volumes = new Volume[nombreObjet];
			Volume v;
			for (int i=0;i<nombreObjet;i++) {
				v = new Volume(0,0,0, (int)(Math.random()*(hauteur_max-hauteur_min)+hauteur_min)
						, (int)(Math.random()*(largeur_max- largeur_min)+largeur_min)
						, (int)(Math.random()*(profondeur_max- profondeur_min)+profondeur_min) );

				volumes[i] = v;
			}
			return(volumes);
		}
		
		private double getDimension(char c)
		{
			if(c=='x')
				return getX();
			else if(c=='y')
				return getY();
			else if(c=='z')
				return getZ();
			else if(c=='s')
				return getSurfaceMax();
			else if(c=='v')
				return getVolume();
			else if(c=='l')
				return getLargeur();
			else if(c=='h')
				return getHauteur();
			else if(c=='p')
				return getProfondeur();
			else 
				return id;
		}
		
		private static void Tri_dimension_n( Volume[] volumes, char n, boolean croissant) 
		{
			double check;
			Volume tmp;
			
			if(croissant)
			{
				int j,i=0;
				while(i<volumes.length)
				{
					j=i+1;
					check = volumes[i].getDimension(n);
					while(j<volumes.length && volumes[j].getDimension(n)>=check ) j++;
					if(j<volumes.length)
					{
						tmp=volumes[i];
						volumes[i] = volumes[j];
						volumes[j] = tmp;
					}
					else
						i++;
				}
			}
			else
			{
				int j,i=0;
				while(i<volumes.length)
				{
					j=i+1;
					check = volumes[i].getDimension(n);
					while(j<volumes.length && volumes[j].getDimension(n)<=check ) j++;
					if(j<volumes.length)
					{
						tmp=volumes[i];
						volumes[i] = volumes[j];
						volumes[j] = tmp;
					}
					else
						i++;
				}
			}	
			
			
		}
//		Trier un tableau de volumes suivant ses différentes dimensions.
//		Passer en paramètres les combinaisons suivantes:
//		x: position X du volume
//		y: position Y du volume
//		z: position Z du volume
//		s: surface maximum trouvée dans un volume (face la plus grande)
//		v: volume du volume
		public static Volume[] Tri_dimension( Volume[] volumes, String ordre_dim, String croissant )
		{
			if(ordre_dim.length()==0)
				return(volumes);
			String char_croissant="0";
			Volume[] v = volumes.clone();
			if(croissant.length()==0 || croissant.charAt(0)!='0')
			{
				Tri_dimension_n(v, ordre_dim.charAt(0), true);
				char_croissant = "1";
			}
			else
				Tri_dimension_n(v, ordre_dim.charAt(0), false);
			
			List<Double> values = new ArrayList<Double>();
			List<List<Volume>> tmp_v = new ArrayList<List<Volume>>();


			for(Volume i:v)
				if(!values.contains(i.getDimension(ordre_dim.charAt(0)))) 
					values.add(i.getDimension(ordre_dim.charAt(0)));
			for(Double x:values)
			{
				List<Volume> l = new ArrayList<Volume>();
				for(Volume i:v)
					if(i.getDimension(ordre_dim.charAt(0))==x)
						l.add(i);
				tmp_v.add(l);
			}
			
			
			v = new Volume[volumes.length];
			int j=0;
			for(List<Volume> l:tmp_v)
				for(Volume i: Tri_dimension((Volume[])l.toArray(new Volume[l.size()]) , ordre_dim.substring(1), char_croissant ))
					v[j++]=i;
				
			
			return(v);
		}

		@Override
		public String toString()
		{
			return("ID="+getID()+"  X="+getX()+"  Y="+getY()+"  Z="+getZ()
					+ "  Largeur="+getLargeur()+"  Hauteur="+getHauteur()+"  Profondeur="+getProfondeur()
					+ "  SurfaceMax="+getSurfaceMax()+"  Volume="+getVolume());
		}
}		
