import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class Client {
    private String name;
    protected int cash = 100;
    protected Set<Ticket> tickets;
    protected String type;

    Client(String n){
        this.name = n;
        this.tickets = new HashSet<Ticket>();
    }
//=====================================================
    public int getCash() {
        return cash;
    }
    public Set<Ticket> getTickets() {
        return tickets;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    //=====================================================
    void addCash(int k){
        this.cash += k;
    }
    void chargeAccount(int k) {
        this.cash -= k;
    }

    public abstract void addticket(int s , int r , int c , int h , String m , int p , int scrNr);
    public abstract void addticket(Ticket t);
    public abstract void removeticket(int s , int r , int c , int h , String m , int p , int scrNr);
    public abstract void removeticket(Ticket t);
    public void printTickets(){
        System.out.println(this.name + " your tickets are:");
        for(Ticket t:this.tickets)
            System.out.println("Screen:" + t.getScreenNumber() + " Row:" + t.getRow() + " Column:" + t.getColumn() + " Hour:" + t.getHour() + " Movie:" + t.getMovie() + " Price:" + t.getPrice() + "Screening number:" + t.getScreeningNumber());
    }
}
