package prova.provando;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class Principal extends GraphicsProgram{

	public void run(){
		Imatge img = new Imatge("wally.gif");
		img.generaPuzzle(3, 5);
		img.intercanviaPeces();
		img.pinta(this);
	}
}
