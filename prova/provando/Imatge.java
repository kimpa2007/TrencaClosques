package prova.provando;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;
import java.util.Random;

import javax.activation.MailcapCommandMap;

import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class Imatge implements MouseListener{
	
	static GImage imatgePuzzle;
	static ArrayList<Casella> puzzle = new ArrayList<Casella>(); 
	int buida; 
	int moviments=0;
	int pieceBuidaY, pieceBuidaX;
	int midaLlargada;
	int midaAmplada;
	
	public Imatge(String imgOriginal){
		imatgePuzzle = new GImage(imgOriginal);
		midaLlargada = (int) imatgePuzzle.getWidth();
		midaAmplada = (int) imatgePuzzle.getHeight();
	}
	
	public GImage tallaImatge(GImage src, int x, int y, int w, int h) {
		Image imatge = src.getImage();
		imatge = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(imatge.getSource(),
		new CropImageFilter(x, y, w, h)));
		return new GImage(imatge);
	}
	
	public void generaPuzzle(int fila, int columna){
		int w = (int)imatgePuzzle.getWidth()/columna;
		int h = (int)imatgePuzzle.getHeight()/fila;
		
		for(int i=0; i<fila; i++){
			
			for(int j=0; j<columna; j++){
				GImage piece = tallaImatge(imatgePuzzle, w*j, h*i, w, h);
				puzzle.add(new Casella(this,piece, j*w, i*h));
			}
		}
		buida = puzzle.size()-1;
	}
	
	public void intercanviaPeces(){
		int mida = puzzle.size()-1;
		Random r = new Random();
		
		for(int i=0; i<30; i++){
			int piece1 = r.nextInt(mida);
			int piece2 = r.nextInt(mida);
			
			int xPiece2 = puzzle.get(piece1).getPosicioX();
			int yPiece2 = puzzle.get(piece1).getPosicioY();

			puzzle.get(piece1).setPosicio(puzzle.get(piece2).getPosicioX(), puzzle.get(piece2).getPosicioY());
			puzzle.get(piece2).setPosicio(xPiece2,yPiece2);			
		}
	}
	
	public void pinta(Principal principal) {		
		for(Casella c: puzzle) {			
			principal.add(c.imatge);		
		}		
		amagaImatge(false);
	}
	
	public void amagaImatge(boolean estat){
		puzzle.get(puzzle.size()-1).imatge.setVisible(estat);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		boolean trobada = false;
		int i = 0;
		
		while(trobada != true){
			trobada = puzzle.get(i).tocada(arg0.getX(),arg0.getY());
			i++;
		}
		System.out.println("trobada " + i);
		moure(i-1);
	}

	private void moure(int posicio){

		pieceBuidaX = puzzle.get(buida).getPosicioX();
		pieceBuidaY = puzzle.get(buida).getPosicioY();

		//mirar si es pot moure i fer un comptador de moviments
		System.out.println(esPodenMoure(posicio));
		if(esPodenMoure(posicio)){
			puzzle.get(buida).setPosicio(puzzle.get(posicio).getPosicioX(),puzzle.get(posicio).getPosicioY());
			puzzle.get(posicio).setPosicio(pieceBuidaX,pieceBuidaY);
			moviments++;
		}

		
	}
	
	private boolean esPodenMoure(int posicio){
		Casella pieceaMoure = puzzle.get(posicio);
		
		//Controlar que no es puguis fer canvis de per ex linia3,columna 3 a linia9,casella2
		if(pieceaMoure.getPosicioX() == pieceBuidaX){
			//Controlar que no es puguin cambiar per ex una posicio 1 amb una posicio3
			 if (Math.abs(pieceBuidaY - pieceaMoure.getPosicioY())  <= pieceaMoure.getLlarg() + 1) {
	                return true;
	            }
		}
		
		else if (pieceaMoure.getPosicioY() == pieceBuidaY){
			 if (Math.abs(pieceBuidaX- pieceaMoure.getPosicioX())  <= pieceaMoure.getAmple() + 1) 
				return true;
		}
		
		return false;
	}
	@Override
	public void mouseEntered(MouseEvent arg0) 	{}
	@Override
	public void mouseExited(MouseEvent arg0) 	{}
	@Override
	public void mousePressed(MouseEvent arg0) 	{}
	@Override
	public void mouseReleased(MouseEvent arg0) 	{}
}
