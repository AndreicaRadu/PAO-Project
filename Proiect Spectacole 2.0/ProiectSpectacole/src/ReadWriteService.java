import java.io.*;
import java.util.*;

public class ReadWriteService {
    private static ReadWriteService instance = null;
    //private static final String screensPath = "screens.csv";
    //private static final String clientsPath = "clients.csv";
    //private static final String screeningsPath = "screenings.csv";
    //private static final String ticketsPath = "tickets.csv";

    private ReadWriteService() {}

    public static ReadWriteService getInstance() {
        if (instance == null) {
            instance = new ReadWriteService();
        }
        return instance;
    }

    public ArrayList<Integer> readScreens(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        ArrayList<Integer> L = new ArrayList<Integer>();
        while((line = br.readLine()) != null) {
            String[] str = line.split(",");
            int aux1 = Integer.parseInt(str[0]) , aux2 = Integer.parseInt(str[1]) , aux3 = Integer.parseInt(str[2]);
            L.add(aux1);
            L.add(aux2);
            L.add(aux3);
        }
        br.close();
        return L;
    }
    public ArrayList<Pair<String , Boolean>> readClients(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        ArrayList<Pair<String , Boolean>> L = new ArrayList<Pair<String , Boolean>>();
        while((line = br.readLine()) != null) {
            String[] str = line.split(",");
            int isstud = Integer.parseInt(str[1]);
            boolean stud = false;
            if(isstud == 1)
                stud = true;
            Pair<String , Boolean> p = new Pair<String , Boolean>(str[0] , stud);
            L.add(p);
        }
        br.close();
        return L;
    }
    public ArrayList<Pair<Integer , Movie>> readScreenings(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        ArrayList<Pair<Integer , Movie>> L = new ArrayList<Pair<Integer , Movie>>();
        while((line = br.readLine()) != null) {
            String[] str = line.split(",");
            int h = Integer.parseInt(str[2]) , scr = Integer.parseInt(str[0]);
            Movie movie = new Movie(str[1] , h);
            Pair<Integer , Movie> p = new Pair<Integer , Movie>(scr , movie);
            L.add(p);
        }
        br.close();
        return L;
    }
    public ArrayList<Pair<String , Ticket>> readTickets(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        ArrayList<Pair<String , Ticket>> L = new ArrayList<Pair<String , Ticket>>();
        while((line = br.readLine()) != null) {
            String[] str = line.split(",");
            String name = str[0];
            int s = Integer.parseInt(str[1]) , r = Integer.parseInt(str[2]) , c = Integer.parseInt(str[3]) , h = Integer.parseInt(str[4]); //screen row column hour
            String m = str[5];                                                     //movie name
            int p = Integer.parseInt(str[6]) , scrNr = Integer.parseInt(str[7]);                                   //price , screening number
            Ticket t = new Ticket(s , r , c , h , m , p , scrNr);
            Pair<String , Ticket> tic = new Pair<String , Ticket>(name , t);
            L.add(tic);
        }
        br.close();
        return L;
    }

    public void writeScreen(String path , int ro , int co , int pr) throws IOException {
        FileWriter fw = new FileWriter(path , true);
        fw.write(ro + "," + co + "," + pr + "\n");
        fw.close();
    }
    public void writeClient(String path , String name , int stud) throws IOException {
        FileWriter fw = new FileWriter(path , true);
        fw.write(name + "," + stud + "\n");
        fw.close();
    }
    public void writeScreening(String path , int scr , String mov , int h) throws IOException {
        FileWriter fw = new FileWriter(path , true);
        fw.write(scr + "," + mov + "," + h + "\n");
        fw.close();
    }
    public void writeTicket(String path , String name , Ticket t) throws IOException {
        FileWriter fw = new FileWriter(path , true);
        fw.write(name + "," + t.getScreenNumber() + "," + t.getRow() + "," + t.getColumn() + "," + t.getHour() + "," + t.getMovie() + "," + t.getPrice() + "," + t.getScreeningNumber() + "\n");
        fw.close();
    }
}
