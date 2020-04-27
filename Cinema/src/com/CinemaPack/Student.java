package com.CinemaPack;
import java.util.Iterator;
import java.util.Set;

public class Student extends Client {
    Student(String n){
        super(n);
        this.type = "Student";
    }
    public void addticket(int s , int r , int c , int h , String m , int p , int scrNr) {
        Ticket t = new Ticket(s , r , c , h , m , p , scrNr);
        this.tickets.add(t);
        this.cash -= p/2;
    }
    public void addticket(Ticket t){
        this.tickets.add(t);
        this.cash -= t.getPrice()/2;
    }
    public void removeticket(int s , int r , int c , int h , String m , int p , int scrNr) {
        Ticket t = new Ticket(s , r , c , h , m , p , scrNr);
        this.tickets.removeIf(i -> i.equals(t));
        this.cash += p/2;
    }
    public void removeticket(Ticket t) {
        this.tickets.removeIf(i -> i.equals(t));
        this.cash += t.getPrice()/2;
    }
}
