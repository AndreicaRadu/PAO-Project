public class Normal extends Client {
    Normal(String n){
        super(n);
        this.type = "Normal";
    }
    public void addticket(int s , int r , int c , int h , String m , int p , int scrNr){
        Ticket t = new Ticket(s , r , c , h , m , p , scrNr);
        this.tickets.add(t);
        this.cash -= p;
    }
    public void addticket(Ticket t){
        this.tickets.add(t);
        this.cash -= t.getPrice();
    }
    public void removeticket(int s , int r , int c , int h , String m , int p , int scrNr){
        Ticket t = new Ticket(s , r , c , h , m , p , scrNr);
        this.tickets.removeIf(i -> i.equals(t));
        this.cash += p;
    }
    public void removeticket(Ticket t){
        this.tickets.removeIf(i -> i.equals(t));
        this.cash += t.getPrice();
    }
}
