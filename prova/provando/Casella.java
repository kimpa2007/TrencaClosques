package prova.provando;

import java.awt.Image;

import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class Casella extends GraphicsProgram{
	int idCasella;
	GImage imatge; 
	int moviments = 0;

	public Casella(Imatge i, GImage imatge, int x, int y){
		this.imatge = imatge;
		this.imatge.setLocation(x,y);
		this.imatge.addMouseListener(i);
	}
	
	public void setImatge(String img){
		imatge = new GImage(img);
	}
	
	public GImage getImatge(){
		return imatge;
	}
	
	public boolean tocada(int x, int y){		
		return imatge.contains(x,y);
	}
	
	public int getPosicioY(){
		return (int) imatge.getY();
	}
	
	public int getPosicioX(){
		return (int) imatge.getX();
	}
	
	public void setPosicio(int x, int y){	
		imatge.setLocation(x,y);
	}
	
	public GPoint getPosicio(){
		return imatge.getLocation();
	}
	
	public int getAmple(){
		return (int) imatge.getWidth();
	}
	
	public int getLlarg(){
		return (int) imatge.getHeight();
	}
}
