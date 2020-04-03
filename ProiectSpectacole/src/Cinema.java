import javafx.util.Pair;
import java.util.*;

public class Cinema {
    private ArrayList<Screen> screenList;
    private ArrayList<Client> clientList;
    private HashMap<String , Integer> clientMap;

    Cinema(){
        screenList = new ArrayList<Screen>();
        clientList = new ArrayList<Client>();
        clientMap = new HashMap<String , Integer>();
    }
//===================================================
    public List<Client> getClientList() {
        return clientList;
    }
    public List<Screen> getScreenList() {
        return screenList;
    }
    public HashMap<String, Integer> getClientMap() {
        return clientMap;
    }
//===================================================
    public void addScreen(int n , int r , int c , int p) {
        Screen scr = new Screen(n , r , c , p);
        this.screenList.add(scr);
    }
    public void addClient(String n, boolean t) {
        Client aux;
        if(t)
            aux = new Student(n);
        else
            aux = new Normal(n);
        clientList.add(aux);
        clientMap.put(n , this.clientList.size()-1);
    }
    public boolean checkClient(String n) {
        return this.clientMap.containsKey(n);
    }
    public void sellTicket(String name) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a screen:");
        int scr = 0;
        scr = sc.nextInt();
        this.getScreenList().get(scr).printScreenings();
        System.out.println("Choose a screening:");
        int screening = sc.nextInt();
        this.getScreenList().get(scr).printSeats(screening);
        System.out.println("Choose an available seat(row and column):");
        int ro = sc.nextInt() , co = sc.nextInt();
        while(ro >= this.screenList.get(scr).getRows() || co >= this.screenList.get(scr).getColumns() || this.screenList.get(scr).getScreenings().get(screening).getValue()[ro][co])
        {
            System.out.println("Choose an available seat(row and column):");
            ro = sc.nextInt();
            co = sc.nextInt();
        }
        this.screenList.get(scr).occupySeat(screening , ro , co);
        this.clientList.get(clientMap.get(name)).addticket(scr , ro , co , this.screenList.get(scr).getScreenings().get(screening).getKey().getHour() , this.screenList.get(scr).getScreenings().get(screening).getKey().getName() , this.screenList.get(scr).getPrice() , screening);
    }
    public void refundTicket(String name){
        clientList.get(clientMap.get(name)).printTickets();
        System.out.println("Choose a ticket to refund by printing its data with the movie name on a separate line:");
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt() , r = sc.nextInt() , c = sc.nextInt() , h = sc.nextInt();
        String aux = sc.nextLine();
        String m = sc.nextLine();
        int p = sc.nextInt() , scrNr = sc.nextInt();
        Ticket t = new Ticket(s , r , c , h , m , p , scrNr);
        this.screenList.get(s).freeSeat(t);
        this.clientList.get(clientMap.get(name)).removeticket(t);
    }
    public void addCash(String name , int x){
        Scanner sc = new Scanner(System.in);
        this.clientList.get(clientMap.get(name)).addCash(x);
    }
    public void checkAccountCash(String name){
        System.out.println("You have " + this.clientList.get(clientMap.get(name)).getCash() + "in your account");
    }
    public void seeTickets(String name){
        this.clientList.get(clientMap.get(name)).printTickets();
    }
    public void transferTicket(Ticket t , String give , String receive){
        this.clientList.get(clientMap.get(give)).removeticket(t);
        this.clientList.get(clientMap.get(receive)).addticket(t);
    }
}
