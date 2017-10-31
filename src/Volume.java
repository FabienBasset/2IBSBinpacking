import java.math.*;
import java.util.ArrayList;
import java.util.List;

public class Volume {
		private double x;
		private double y;
		private double z;
		private double hauteur;
		private double largeur;
		private double profondeur;
		
		public Volume (double x, double y, double z, double hauteur, double largeur, double profondeur)
		{
			this.x = x;
			this.y = y;
			this.hauteur = hauteur;
			this.largeur = largeur;
			this.profondeur = profondeur;
			this.z = z;
		}
		
		public double getAireMax(){ 
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
		
		private static void Tri_dimension_n( Volume[] volumes, char n, boolean croissant)
		{
			double check;
			Volume tmp;
			
			if(n=='x') {
				if(croissant)
				{
					int j,i=0;
					while(i<volumes.length)
					{
						j=i+1;
						check = volumes[i].getX();
						while(j<volumes.length && volumes[j].getX()>=check ) j++;
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
						check = volumes[i].getX();
						while(j<volumes.length && volumes[j].getX()<=check ) j++;
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
			} else if(n=='y') {
				if(croissant)
				{
					int j,i=0;
					while(i<volumes.length)
					{
						j=i+1;
						check = volumes[i].getY();
						while(j<volumes.length && volumes[j].getY()>=check ) j++;
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
						check = volumes[i].getY();
						while(j<volumes.length && volumes[j].getY()<=check ) j++;
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
			} else if(n=='z') {
				if(croissant)
				{
					int j,i=0;
					while(i<volumes.length)
					{
						j=i+1;
						check = volumes[i].getZ();
						while(j<volumes.length && volumes[j].getZ()>=check ) j++;
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
						check = volumes[i].getZ();
						while(j<volumes.length && volumes[j].getZ()<=check ) j++;
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
			} else if(n=='l') {
				if(croissant)
				{
					int j,i=0;
					while(i<volumes.length)
					{
						j=i+1;
						check = volumes[i].getLargeur();
						while(j<volumes.length && volumes[j].getLargeur()>=check ) j++;
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
						check = volumes[i].getLargeur();
						while(j<volumes.length && volumes[j].getLargeur()<=check ) j++;
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
			} else if(n=='h') {
				if(croissant)
				{
					int j,i=0;
					while(i<volumes.length)
					{
						j=i+1;
						check = volumes[i].getHauteur();
						while(j<volumes.length && volumes[j].getHauteur()>=check ) j++;
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
						check = volumes[i].getHauteur();
						while(j<volumes.length && volumes[j].getHauteur()<=check ) j++;
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
			} else if(n=='p') {
				if(croissant)
				{
					int j,i=0;
					while(i<volumes.length)
					{
						j=i+1;
						check = volumes[i].getProfondeur();
						while(j<volumes.length && volumes[j].getProfondeur()>=check ) j++;
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
						check = volumes[i].getProfondeur();
						while(j<volumes.length && volumes[j].getProfondeur()<=check ) j++;
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
			} else if(n=='v') {
				if(croissant)
				{
					int j,i=0;
					while(i<volumes.length)
					{
						j=i+1;
						check = volumes[i].getVolume();
						while(j<volumes.length && volumes[j].getVolume()>=check ) j++;
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
						check = volumes[i].getVolume();
						while(j<volumes.length && volumes[j].getVolume()<=check ) j++;
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
			} else if(n=='s') {
				if(croissant)
				{
					int j,i=0;
					while(i<volumes.length)
					{
						j=i+1;
						check = volumes[i].getAireMax();
						while(j<volumes.length && volumes[j].getAireMax()>=check ) j++;
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
						check = volumes[i].getAireMax();
						while(j<volumes.length && volumes[j].getAireMax()<=check ) j++;
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
			
		}
//		Trier un tableau de volumes suivant ses différentes dimensions.
//		Passer en paramètres les combinaisons suivantes:
//		x: position X du volume
//		y: position Y du volume
//		z: position Z du volume
//		s: surface maximum trouvée dans un volume (face la plus grande)
//		v: volume du volume
		public static Volume[] Tri_dimension( Volume[] volumes, String ordre_dim, boolean croissant )
		{
			if(ordre_dim.length()==0)
				return(volumes);
			
			Volume[] v = volumes.clone();
			Tri_dimension_n(v, ordre_dim.charAt(0), croissant);
			
			List<Double> values = new ArrayList<Double>();
			List<List<Volume>> tmp_v = new ArrayList<List<Volume>>();

			if(ordre_dim.charAt(0)=='l')
			{
				for(Volume i:v)
					if(!values.contains(i.getLargeur())) 
						values.add(i.getLargeur());
				for(Double x:values)
				{
					List<Volume> l = new ArrayList<Volume>();
					for(Volume i:v)
						if(i.getLargeur()==x)
							l.add(i);
					tmp_v.add(l);
				}
			} else if(ordre_dim.charAt(0)=='h')
			{
				for(Volume i:v)
					if(!values.contains(i.getHauteur())) 
						values.add(i.getHauteur());
				for(Double x:values)
				{
					List<Volume> l = new ArrayList<Volume>();
					for(Volume i:v)
						if(i.getHauteur()==x)
							l.add(i);
					tmp_v.add(l);
				}
			} else if(ordre_dim.charAt(0)=='p')
			{
				for(Volume i:v)
					if(!values.contains(i.getProfondeur())) 
						values.add(i.getProfondeur());
				for(Double x:values)
				{
					List<Volume> l = new ArrayList<Volume>();
					for(Volume i:v)
						if(i.getProfondeur()==x)
							l.add(i);
					tmp_v.add(l);
				}
			} else if(ordre_dim.charAt(0)=='x')
			{
				for(Volume i:v)
					if(!values.contains(i.getX())) 
						values.add(i.getX());
				for(Double x:values)
				{
					List<Volume> l = new ArrayList<Volume>();
					for(Volume i:v)
						if(i.getX()==x)
							l.add(i);
					tmp_v.add(l);
				}
			} else if(ordre_dim.charAt(0)=='y')
			{
				for(Volume i:v)
					if(!values.contains(i.getY())) 
						values.add(i.getY());
				for(Double x:values)
				{
					List<Volume> l = new ArrayList<Volume>();
					for(Volume i:v)
						if(i.getY()==x)
							l.add(i);
					tmp_v.add(l);
				}
			} else if(ordre_dim.charAt(0)=='z')
			{
				for(Volume i:v)
					if(!values.contains(i.getZ())) 
						values.add(i.getZ());
				for(Double x:values)
				{
					List<Volume> l = new ArrayList<Volume>();
					for(Volume i:v)
						if(i.getZ()==x)
							l.add(i);
					tmp_v.add(l);
				}
			} else if(ordre_dim.charAt(0)=='v')
			{
				for(Volume i:v)
					if(!values.contains(i.getVolume())) 
						values.add(i.getVolume());
				for(Double x:values)
				{
					List<Volume> l = new ArrayList<Volume>();
					for(Volume i:v)
						if(i.getVolume()==x)
							l.add(i);
					tmp_v.add(l);
				}
			} else if(ordre_dim.charAt(0)=='s')
			{
				for(Volume i:v)
					if(!values.contains(i.getAireMax())) 
						values.add(i.getAireMax());
				for(Double x:values)
				{
					List<Volume> l = new ArrayList<Volume>();
					for(Volume i:v)
						if(i.getAireMax()==x)
							l.add(i);
					tmp_v.add(l);
				}
			} else
				return(v);
			
			v = new Volume[volumes.length];
			int j=0;
			for(List<Volume> l:tmp_v)
				for(Volume i: Tri_dimension((Volume[])l.toArray(new Volume[l.size()]) , ordre_dim.substring(1), croissant))
					v[j++]=i;
				
			
			return(v);
		}
}		
