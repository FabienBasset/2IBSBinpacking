package Projet;

import java.awt.Color;
import Projet.StdDraw3D.Shape;

public class Affichage {

	private static Volume Conteneur = new Volume(0,0,0,500,500,500);
	private static int ScaleXmin = (int) -Conteneur.getLargeur();
	private static int ScaleXmax = (int) Conteneur.getLargeur();

	public static void main(String[] args) {
		
		// Définition de l'echelle de la fenetre
        StdDraw3D.setScale(ScaleXmin,ScaleXmax);
        
        // Chargement des limites du conteneur 
		Shape Sol = Afficher_Volume(new Volume(0,0,0,Conteneur.getLargeur(),0,Conteneur.getProfondeur()), 255);
		Sol.setColor(Color.WHITE, 255);
		Shape MurG = Afficher_Volume(new Volume(0,0,0,0,Conteneur.getHauteur(),Conteneur.getProfondeur()), 255);
		MurG.setColor(Color.WHITE, 255);
		Shape MurD = Afficher_Volume(new Volume(Conteneur.getLargeur(),0,0,0,Conteneur.getHauteur(),Conteneur.getProfondeur()), 255);
		MurD.setColor(Color.WHITE, 255);  
		
        // Génération des volumes à placer
        Volume[] v = Volume.generateurVolume(100, 50, 50, 50, 50, 0, 0);
        
        // Récupération des volumes qui ont été placé avec succés
		Volume[] Place = MaxRects.MaxRectsRun(Conteneur, v, "hpl");
		
		// Chargement des volumes placés
        for (Volume v_place : Place) {
        	Shape S = Afficher_Volume(v_place, 200);	
        	System.out.println(v_place.toString());
        }
        
        
        System.out.println(Volume.Compter_places(v));
        
        // Affichage du résultat obtenu
        StdDraw3D.finished();
	}
	
	public static Shape Afficher_Volume (Volume v, int alpha) {
		// Changement de repére et chargement du volume
		Shape S = StdDraw3D.box(	v.getX()+v.getLargeur()/2-Conteneur.getLargeur()/2, 
									v.getY()+v.getHauteur()/2-Conteneur.getHauteur()/2, 
									v.getZ()+v.getProfondeur()/2-Conteneur.getProfondeur()/2, 
									v.getLargeur()/2, 
									v.getHauteur()/2, 
									v.getProfondeur()/2);
		
		// Selection d'une couleur aléatoire pour le volume actuel
		S.setColor(StdDraw3D.randomColor(), alpha);
		
		return S;
	}
}
