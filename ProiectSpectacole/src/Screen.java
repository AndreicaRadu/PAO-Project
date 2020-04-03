import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private int number;
    private int rows=0 , columns=0;
    //private boolean[][] seats = new boolean[100][100];
    private int price;
    private int occupied;
    private ArrayList<Pair<Movie , boolean[][]>> screenings;

    Screen(int n , int r , int c , int p) {
        this.number = n;
        this.screenings = new ArrayList<Pair<Movie , boolean[][]>>();
        this.rows = r;
        this.columns = c;
        this.price = p;
        this.occupied = 0;
    }

    void addMovie(String n , int h){
        Movie m = new Movie(n , h);
        boolean[][] aux = new boolean[100][100];
        for(int i=0 ; i<this.rows ; i++)
            for(int j=0 ; j<this.columns ; j++)
                aux[i][j] = false;
        Pair<Movie , boolean[][]> p = new Pair<Movie , boolean[][]>(m , aux);
        this.screenings.add(p);
    }
    void addMovie(Movie m){
        boolean[][] aux = new boolean[100][100];
        for(int i=0 ; i<this.rows ; i++)
            for(int j=0 ; j<this.columns ; j++)
                aux[i][j] = false;
        Pair<Movie , boolean[][]> p = new Pair<Movie , boolean[][]>(m , aux);
        this.screenings.add(p);
    }
//=======================================================
    public int getNumber() {
        return number;
    }
    public int getOccupied() {
        return occupied;
    }
    public int getRows(){
        return this.rows;
    }
    public int getColumns(){
        return this.columns;
    }
    public int getPrice(){
        return this.price;
    }
    public List<Pair<Movie , boolean[][]>> getScreenings(){
        return this.screenings;
    }
//========================================================

    public void occupySeat(int scr , int row , int col){
        if(!this.screenings.get(scr).getValue()[row][col])
            occupied++;
        this.screenings.get(scr).getValue()[row][col] = true;
    }
    public void freeSeat(Ticket t){
        if(this.screenings.get(t.getScreenNumber()).getValue()[t.getRow()][t.getColumn()])
            occupied--;
        this.screenings.get(t.getScreenNumber()).getValue()[t.getRow()][t.getColumn()] = false;
    }
    void printSeats(int screenigID){
        for(int i=0 ; i<this.rows ; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < columns; j++)
            {
                if(this.screenings.get(screenigID).getValue()[i][j])
                    str.append('.');
                else str.append('O');
            }
            System.out.println(str);
        }
    }
    void printScreenings(){
        System.out.println("Screen " + this.number + ":");
        for(int i=0 ; i<this.screenings.size() ; i++)
            System.out.println(i + ": " + this.screenings.get(i).getKey().getName() + " | Starting at: " + this.screenings.get(i).getKey().getHour());
    }
}
