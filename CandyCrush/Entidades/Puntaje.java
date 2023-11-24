package Entidades;

public class Puntaje {
    public static int getPuntaje(int color){
		int puntaje=0;
		switch (color){
			case 1:
				puntaje=10;
				break;
			case 2:
				puntaje=15;
				break;
			case 4:
				puntaje=5;
			case 6:
				puntaje=25;
				break;
			case 3,5,7:
				puntaje=20;
		}
		return puntaje;
	}
}
