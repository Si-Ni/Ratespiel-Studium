import java.util.Random;
import java.util.Scanner;

public class Ratespiel {

    private int randomNumber;
    int zaehler = 0;
    Spieler[] alleSpieler = new Spieler[3];
    boolean zahlErraten = false;
    
    public void starteSpiel(){
        System.out.println("Spiel wird gestartet");

        generateRandomNumber();
        generatePlayers();
        getPlayerInput();
    }

    public void generateRandomNumber(){
        Random r = new Random();
        randomNumber = r.nextInt(10);
    }

    public void generatePlayers(){
        Spieler spieler1 = new Spieler();
        Spieler spieler2 = new Spieler();
        Spieler spieler3 = new Spieler();

        alleSpieler[0] = spieler1;
        alleSpieler[1] = spieler2;
        alleSpieler[2] = spieler3;
    }

    public void getPlayerInput(){
        zaehler++;
        System.out.println("Spieler " + zaehler  + " bitte gib jetzt deine Zahl ein");

        Scanner InputScanner = new Scanner(System.in);
        String input = InputScanner.nextLine();
        int inputNumber;
        try{
            inputNumber = Integer.parseInt(input);
            if(inputNumber == randomNumber){
                alleSpieler[zaehler-1].spielerZahlErraten = true;
                zahlErraten = true;
            }
        }
        catch(Exception ex){
            System.out.println("ungültige Eingabe");
            System.out.println("versuche es erneut...");
            zaehler--;
            getPlayerInput();
        }

        if(zaehler == 3){
            zaehler = 0;
            if(zahlErraten){
                checkWinner();
            }else{
                System.out.println("Keiner hat die Zahl richtig erraten");
                getPlayerInput();
            }
        }else{
            getPlayerInput();
        }
    }

    public void checkWinner(){
        for(int i = 0; i < alleSpieler.length; i++){
            if(alleSpieler[i].spielerZahlErraten){
                System.out.println("Spieler " + (i + 1) + " hat das Spiel gewonnen");
            }
            alleSpieler[i].spielerZahlErraten = false;
        }
        System.out.println("Die richtige Zahl war " + randomNumber);
        System.out.println("Neues Spiel wird gestartet");
        zahlErraten = false;
        generateRandomNumber();
        getPlayerInput();
    }
}
