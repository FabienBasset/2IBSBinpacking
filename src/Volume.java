 import java.util.ArrayList;
import java.util.List;


public class Volume implements Cloneable {
		private double x;
		private double y;
		private double z;
		private double hauteur;
		private double largeur;
		private double profondeur;
		private static int id_next = 0;
		private int id;
		
		public Volume (double x, double y, double z, double largeur, double hauteur, double profondeur)
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
			if(a1>=a2 && a1>=a3)
				return a1;	
			else if(a2>=a1 && a2>=a3)
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
		public void setZ(int Z)			{ this.z=Z; }
		
		public double getHauteur() 	{ return hauteur; }
		public double getLargeur() 	{ return largeur; }
		public double getProfondeur() {	return profondeur; }	
		public int getID() {	return id; }

		public static Volume[] Decouper_Volume_libre(Volume container, Volume object)
		{
			List<Volume> l = new ArrayList<Volume>();
			Volume tmp1, tmp2;
			if( object.x>=container.x && object.x<=container.x+container.largeur && object.x+object.largeur<=container.x+container.largeur &&
					object.y>=container.y && object.y<=container.y+container.hauteur && object.y+object.hauteur<=container.y+container.hauteur &&
					object.z>=container.z && object.z<=container.z+container.profondeur && object.z+object.profondeur<=container.z+container.profondeur
					)
			{
				/* GAUCHE */
				if(object.x-container.x != 0) 
					l.add(new Volume(container.x,container.y,container.z, object.x-container.x, container.hauteur, container.profondeur));
				
				/* DROITE */
				if(container.largeur-object.largeur-object.x+container.x != 0) 
					l.add(new Volume(object.x+object.largeur,container.y,container.z, 
							 container.largeur-object.largeur-object.x+container.x, container.hauteur, container.profondeur));
				
				/* DESSUS */
				if(container.y+container.hauteur != object.y+object.hauteur)
					l.add( new Volume(object.x,object.y+object.hauteur,container.z, 
							object.largeur, container.hauteur-object.hauteur-object.y+container.y, container.profondeur));
				
				/* DESSOUS */
				if(container.y != object.y)
					l.add( new Volume(object.x,container.y,container.z, 
							object.largeur, object.y-container.y, container.profondeur));
				
				/* DEVANT */
				if(object.z != container.z)
					l.add( new Volume(object.x,object.y,container.z, 
							object.largeur, object.hauteur, object.z - container.z));
				
				/* ARRIERE */
				if(object.z+object.profondeur != container.z+container.profondeur)
					l.add( new Volume(object.x,object.y,object.z+object.profondeur, 
							object.largeur, object.hauteur, container.profondeur-object.profondeur-object.z+container.z));
			}
			else
				l.add(container);
			
			return(l.toArray(new Volume[l.size()]));
		}
		
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
		
		/* retourne une dimension en fonction d'une lettre. 
		 * Les lettres x,y,z,l,h,p,s,v retournent respectivement X,Y,Z,Largeur,Hauteur,Profondeur,SurfaceMax,Volume.
		 * Ces lettres sont utilisées dans toutes les fonction nécéssitant un paramétre de dimensions.
		 * Si une autre lettre ou aucune apparaît, c'est l'ID du volume qui servira de dimension.
		 */
		private double getDimension(char c)
		{
			if(c=='x')
				return x;
			else if(c=='y')
				return y;
			else if(c=='z')
				return z;
			else if(c=='s')
				return getSurfaceMax();
			else if(c=='v')
				return getVolume();
			else if(c=='l')
				return largeur;
			else if(c=='h')
				return hauteur;
			else if(c=='p')
				return profondeur;
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
		/* Trier un tableau de volumes suivant ses différentes dimensions.
		 * 		Passer en paramètres les combinaisons suivantes:
		 * 		x: position X du volume
		 * 		y: position Y du volume
		 * 		z: position Z du volume
		 * 		l: Largeur du volume
		 * 		h: Hauteur du volume
		 * 		p: Profondeur du volume
		 * 		s: surface maximum trouvée dans un volume (face la plus grande)
		 * 		v: volume du volume
		 * 		default : ID
		 */
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
		public Volume clone()
		{
			return (new Volume(x,y,z, largeur,hauteur,profondeur));
		}
		
		public void basculer_90_degres_droite()
		{
			double tmp = largeur;
			largeur = hauteur;
			hauteur = tmp;
		}
		public void basculer_90_degres_avant()
		{
			double tmp = profondeur;
			profondeur = hauteur;
			hauteur = tmp;
		}
		public void pivoter_90_degres_droite()
		{
			double tmp = profondeur;
			profondeur = largeur;
			largeur = tmp;
		}
		
		/* Renvoie un boolean indiquant si le l'objet rentre dans le Volume volume. 
		 * Si oui, la fonction l'oriente afin qu'il puisse rentrer et si possible suivant les préférences d'agencement de l'objet 
		 * via ses dimensions dim_pref
		 */
		public boolean rentre_dans(Volume volume, String dim_pref)
		{
			for(char dim:dim_pref.toCharArray())
				if(dim_pref.length()!=3 || (dim!='l' && dim!='p' && dim!='h'))
					throw new IllegalArgumentException ("Dimension(s) incorrecte(s), dim_pref doit contenir 3 caractères parmi les lettres l,h et p, correspondantes respectivement aux dimensions de largeur, hauteur et profondeur du volume !");
		
			boolean res = false;
			double a,b,c;
			String[] possibilities = {"lhp", "lph", "hlp", "hpl", "phl", "plh" };
			List<Volume> v = new ArrayList<Volume>();
				
			for(String s:possibilities)
			{
				a = getDimension(s.charAt(0));
				b = getDimension(s.charAt(1));
				c = getDimension(s.charAt(2));
				if(a<=volume.largeur && b<=volume.hauteur && c<=volume.profondeur && a>0 && b>0 )
				{
					v.add(new Volume(0,0,0, a ,b ,c ));
					res = true;
				}
			}
			if(res)
			{
				Volume[] v_f = Tri_dimension((Volume[])v.toArray(new Volume[v.size()]), dim_pref, "000");
				largeur = v_f[0].largeur;
				hauteur = v_f[0].hauteur;
				profondeur = v_f[0].profondeur;
			}
			return(res);
		}
		
}		
