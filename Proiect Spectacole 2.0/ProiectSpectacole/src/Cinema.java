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
    public Ticket sellTicket(String name) {
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
        Ticket t = new Ticket(scr , ro , co , this.screenList.get(scr).getScreenings().get(screening).getKey().getHour() , this.screenList.get(scr).getScreenings().get(screening).getKey().getName() , this.screenList.get(scr).getPrice() , screening);
        this.clientList.get(clientMap.get(name)).addticket(t);
        return t;
    }
    public void refundTicket(String name){
        clientList.get(clientMap.get(name)).printTickets();
        System.out.println("Choose a ticket to refund by printing its data separated by , (comma) and no spaces:");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] str = line.split(",");
        int s = Integer.parseInt(str[0]) , r = Integer.parseInt(str[1]) , c = Integer.parseInt(str[2]) , h = Integer.parseInt(str[3]);
        String m = str[4];
        int p = Integer.parseInt(str[5]) , scrNr = Integer.parseInt(str[6]);
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
