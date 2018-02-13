
public class Moteur { //Classe moteur (dépend de la tension)
double tension;

public Moteur(){
	
}

public void start (){
	setVitesse(1); //setVitesse = défini la vitesse du moteur 
}

public void Stop () {
	setVitesse(0);
}

//setVitesse à définir => dépend de la tension
public void setVitesse (int v) {
	tension = 250*v;
}

public void setTension (int t){
	tension = t;
}

}
