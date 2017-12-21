package Projet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MaxRects {

	
	public MaxRects ()
	{
		
	}	
	
	public static Volume[] MaxRectsRun(Volume container, Volume[] volumes, String pref_placement)
	{
		List<Volume> dispo = new LinkedList<Volume>();
		List<Volume> place = new LinkedList<Volume>();
		dispo.add(container);
		for(Volume v:volumes)
		{
			if(dispo.size()==0)
			{
				System.out.println("Plus de place. Arrêt.");
				return(place.toArray(new Volume[place.size()]));
			}
			
			dispo = new LinkedList<Volume>(Arrays.asList(Volume.Tri_dimension(dispo.toArray(new Volume[dispo.size()]), "v", "0")));
			
			boolean rentre = false;
			rentre = v.rentre_dans(dispo.get(0), pref_placement);
			if(rentre) 
			{
				v.setPlace(true);
				Volume container_choisi = dispo.get(0).clone();
				v.setX((int) container_choisi.getX());
				v.setY((int) container_choisi.getY());
				v.setZ((int) container_choisi.getZ());
				place.add(v.clone());
				Volume[] new_libres = Volume.Decouper_Volume_libre(container_choisi, v);
				dispo.remove(0);
				for(Volume v2:new_libres)
					dispo.add(v2.clone());
				
				
//				for(Volume v2:new_libres)
//				{
//					for(Volume v3:dispo)
//					{
//						if(v)
//						
//					}
//					
//				}
			}
			else
			{
				System.out.println("Impossible de rentrer le volume : " + v.toString());
			}
		}
		return(place.toArray(new Volume[place.size()]));
	}
}
