package fr.univartois.butinfo.ihm.fourinaline.model;

import java.net.URL;

import fr.univartois.butinfo.ihm.fourinaline.controller.FourInALineController;
import javafx.scene.image.ImageView;

public class Grid {

	public int startPlayer;
	public int joueur=0;
	public boolean victoire=false;
    
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static final int TO_ALIGN = 4;
    
    private FourInALineController fialc;
    
    private ImageView[][] imageGrid= new ImageView[ROWS][COLUMNS];  
    private ImageView[][] currentColorimageGrid= new ImageView[1][1];  
    
    public URL Empty = getClass().getResource("../view/images/EMPTY.gif");
    public URL Red = getClass().getResource("../view/images/RED.gif");
    public URL Yellow = getClass().getResource("../view/images/YELLOW.gif");
    public URL R = getClass().getResource("../view/images/REDSELECTOR.png");
    public URL Y = getClass().getResource("../view/images/YELLOWSELECTOR.png");
	
	public Grid(FourInALineController fourInALineController) {
		this.fialc=fourInALineController;
	}
	
	public void currentColorDisplay() { //affiche le jeton de la couleur qui joue 
		fialc.currentColor.getChildren().clear();
		if (joueur%2==0) {
    		ImageView red = new ImageView(R.toExternalForm());
    		red.setFitHeight(50);
    		red.setFitWidth(50);
    		fialc.currentColor.add(red, 0, 0);
    		currentColorimageGrid[0][0] = red;
        }
    	else {
    		ImageView yellow = new ImageView(Y.toExternalForm());
    		yellow.setFitHeight(50);
    		yellow.setFitWidth(50);
    		fialc.currentColor.add(yellow, 0, 0);
    		currentColorimageGrid[0][0] = yellow;
    	}
    }
    
    public void vider() { //vide le plateau
    	for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                ImageView imageView = new ImageView(Empty.toExternalForm());
                imageGrid[i][j] = imageView;
                fialc.grid.add(imageView, j, i);
            }
        }
    }
    
    private boolean estRempli() { // verifie le plateau est plein
    	for (int i = 0; i < COLUMNS; i++) { // si une colomne n'est pas vide alors ce n'est pas plein
            if (returnTrueIfSelectedColor("Empty", 0, i)) {
                return false; 
            }
        }
    	fialc.log.setText("Plateau rempli !");
        return true;
    }
    
    private boolean returnTrueIfSelectedColor(String color, int i, int j) {  // permet de verifier un jeton a une position
    	 switch(color) {
    	 	case "RED" :
    	 		return imageGrid[i][j].getImage().getUrl().equals(Red.toExternalForm());
    	 	case "YELLOW" : 
    	 		return imageGrid[i][j].getImage().getUrl().equals(Yellow.toExternalForm());
    	 	default :
    	 		return imageGrid[i][j].getImage().getUrl().equals(Empty.toExternalForm());
    	 }
    }
    
    private boolean win(String joueurString) { // parcours ligne, colone et dagonale a la suite par couleur fournie en parametre
    	int somme;
    	if (joueurString.equals("RED")) {
	    	somme=0;
	        for(int i=0;i<ROWS;i++){
	            for(int j=0;j<COLUMNS;j++){
	            	if (!returnTrueIfSelectedColor("RED",i,j) || returnTrueIfSelectedColor("EMPTY",i,j)){
	                    somme=0;
	                }
	                else if (returnTrueIfSelectedColor("RED",i,j)){
	                    somme=somme+1;
	                }
	                if (somme==TO_ALIGN) {	    
	                	fialc.log.setText("R WINS"+"\n"+fialc.log.getText());
	                    return true;
	                } 
	            }
	        }
	        somme=0;
	        for(int i=0;i<COLUMNS;i++){
	            for(int j=0;j<ROWS;j++){
	                if (!returnTrueIfSelectedColor("RED",j,i) || returnTrueIfSelectedColor("EMPTY",j,i)){
	                    somme=0;
	                }
	                else if (returnTrueIfSelectedColor("RED",j,i)){
	                    somme=somme+1;
	                }
	                if (somme==TO_ALIGN) {
	                	fialc.log.setText("R WINS\n"+fialc.log.getText());
	                    return true;
	                }
	            }
	        }
	        
	        for (int i = 0; i < ROWS - 3; i++) {
	            for (int j = 0; j < COLUMNS - 3; j++) {
	                int count = 0;
	                for (int k = 0; k < 4; k++) {
	                    if (returnTrueIfSelectedColor("RED",(i+k),(j+k))) {
	                        count++;
	                    }
	                }
	                if (count == TO_ALIGN) {
	                	fialc.log.setText("R WINS\n"+fialc.log.getText());
	                	return true;
	                }
	            }
	        }
	        for (int i = 0; i < ROWS - 3; i++) {
	            for (int j = COLUMNS - 1; j >= 3; j--) {
	                int count = 0;
	                for (int k = 0; k < 4; k++) {
	                    if (returnTrueIfSelectedColor("RED",(i+k),(j-k))) {
	                        count++;
	                    }
	                }
	                if (count == TO_ALIGN) {
	                	fialc.log.setText("R WINS\n"+fialc.log.getText());
	                	return true;
	                }
	            }
	        }
    	}
    	else {
    		somme=0;
	        for(int i=0;i<ROWS;i++){
	            for(int j=0;j<COLUMNS;j++){
	            	if (!returnTrueIfSelectedColor("YELLOW",i,j) || returnTrueIfSelectedColor("EMPTY",i,j)){
	                    somme=0;
	                }
	                else if (returnTrueIfSelectedColor("YELLOW",i,j)){
	                    somme=somme+1;
	                }
	                if (somme==TO_ALIGN) {	  
	                	fialc.log.setText("Y WINS\n"+fialc.log.getText());
	                    return true;
	                } 
	            }
	        }
	        somme=0;
	        for(int i=0;i<COLUMNS;i++){
	            for(int j=0;j<ROWS;j++){
	                if (!returnTrueIfSelectedColor("YELLOW",j,i) || returnTrueIfSelectedColor("EMPTY",j,i)){
	                    somme=0;
	                }
	                else if (returnTrueIfSelectedColor("YELLOW",j,i)){
	                    somme=somme+1;
	                }
	                if (somme==TO_ALIGN) {
	                	fialc.log.setText("Y WINS\n"+fialc.log.getText());
	                    return true;
	                }
	            }
	        }
	        for (int i = 0; i < ROWS - 3; i++) {
	            for (int j = 0; j < COLUMNS - 3; j++) {
	                int count = 0;
	                for (int k = 0; k < 4; k++) {
	                    if (returnTrueIfSelectedColor("YELLOW",(i+k),(j+k))) {
	                        count++;
	                    }
	                }
	                if (count == TO_ALIGN) {
	                	fialc.log.setText("Y WINS\n"+fialc.log.getText());
	                	return true;
	                }
	            }
	        }
	        for (int i = 0; i < ROWS - 3; i++) {
	            for (int j = COLUMNS - 1; j >= 3; j--) {
	                int count = 0;
	                for (int k = 0; k < 4; k++) {
	                    if (returnTrueIfSelectedColor("YELLOW",(i+k),(j-k))) {
	                        count++;
	                    }
	                }
	                if (count == TO_ALIGN) {
	                	fialc.log.setText("Y WINS\n"+fialc.log.getText());
	                	return true;
	                }
	            }
	        }
    	}
    	return false;
    }
    
    public void addPionColumn(int column) { //ajoute un pion en bas d'une colonne si possible 
    	if (!estRempli() && !victoire) {
    		boolean placed = false;
	    	int i = 5; 
	    	while (!placed) {
	    		if (i < 0) {
	    			placed = true;
	    	    } 
	    		else if (returnTrueIfSelectedColor("Empty", i, column)) {
	    	    	if (joueur%2==0) {
	    	    		fialc.log.setText("R "+(column+1)+":"+(i+1)+"\n"+fialc.log.getText());
	    	    		fialc.grid.add(new ImageView(Red.toExternalForm()), column, i);
	        	        imageGrid[i][column] = new ImageView(Red.toExternalForm());
	    	        }
	    	        else {
	    	        	fialc.log.setText("J  "+(column+1)+":"+(i+1)+"\n"+fialc.log.getText());
	    	        	fialc.grid.add(new ImageView(Yellow.toExternalForm()), column, i);
	        	        imageGrid[i][column] = new ImageView(Yellow.toExternalForm());
	    	        }
	    	        joueur++;
	    	        placed = true;
	    	        currentColorDisplay();
	    	        victoire=win("RED");
	    	        if (!victoire) {
	    	        	victoire=win("YELLOW");
	    	        }
	    	    } 
	    		else {
	    	    	i--;
	    	    }
	    	}
    	}
    }

	public void setJoueur() {
		if (startPlayer==0) {
    		joueur=0;
    	}
    	else {
    		joueur=1;
    	}
	}

	public void setVictoire(boolean b) {
		victoire=b;
	}
	
	public void switchStartPlayer() {
		if (startPlayer==0) {
    		startPlayer++;
    	}
    	else {
    		startPlayer--;
    	}
	}

}
