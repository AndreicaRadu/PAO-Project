package com.CinemaService;
import com.CinemaPack.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Service extends Cinema {
    private Cinema c;
    private boolean configured = false;
    private ReadWriteService rw;

    public Service(){
        this.c = new Cinema();
    }
    public Cinema getC() {
        return c;
    }

    public void configCinema() throws IOException {
        ReadWriteService.getInstance();
        ArrayList<Integer> s = ReadWriteService.getInstance().readScreens("screens.csv");
        for(int i=0 ; i<s.size()/3 ; i++)
            this.c.addScreen(i , s.get(3 * i), s.get(3 * i + 1), s.get(3 * i + 2));
        ArrayList<Pair<String , Boolean>> cl = ReadWriteService.getInstance().readClients("clients.csv");
        for(Pair<String, Boolean> i : cl)
            c.addClient(i.first , i.second);
        ArrayList<Pair<Integer , Movie>> mv = ReadWriteService.getInstance().readScreenings("screenings.csv");
        for(Pair<Integer , Movie> i : mv)
            this.c.getScreenList().get(i.first).addMovie(i.second);
        ArrayList<Pair<String , Ticket>> tk = ReadWriteService.getInstance().readTickets("tickets.csv");
        for(Pair<String , Ticket> i : tk){
            this.c.getClientList().get(this.c.getClientMap().get(i.first)).addticket(i.second);
            this.c.getScreenList().get(i.second.getScreenNumber()).occupySeat(i.second.getScreeningNumber() , i.second.getRow() , i.second.getColumn());
        }
    }
    public String interogateName(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Name of customer:");
        String name = sc.nextLine();
        while(!this.c.checkClient(name)){
            System.out.println("Name of customer:");
            name = sc.nextLine();
        }
        return name;
    }
    public void newScreen() throws IOException {
        System.out.println("Input number of rows, columns, price:");
        int nr = this.c.getScreenList().size();
        Scanner sc = new Scanner(System.in);
        int ro = sc.nextInt() , co = sc.nextInt() , pr = sc.nextInt();
        this.c.addScreen(nr , ro , co , pr);
        ReadWriteService.getInstance().writeScreen("screens.csv" , ro , co , pr);
        String aux = sc.nextLine();
    }           //1
    public void newMovie() throws IOException {
        int nr = -1;
        Scanner sc = new Scanner(System.in);
        while(nr<0 || nr>=this.c.getScreenList().size()){
            System.out.println("Input screen number you want to add movie to:");
            nr = sc.nextInt();
        }
        String aux = sc.nextLine();
        System.out.println("Input movie followed by hour on the next line");
        aux = sc.nextLine();
        int h = sc.nextInt();
        this.c.getScreenList().get(nr).addMovie(aux , h);
        ReadWriteService.getInstance().writeScreening("screenings.csv" , nr , aux , h);
        aux = sc.nextLine();
    }            //2
    public void newClient() throws IOException {
        System.out.println("Print your name and on the next line 1 if you're a student and 0 otherwise:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int k = sc.nextInt();
        boolean isStud = (k == 1);
        String aux = sc.nextLine();
        if(!this.c.checkClient(name)) {
            this.c.addClient(name , isStud);
            ReadWriteService.getInstance().writeClient("clients.csv" , name , k);
        }
        else
            System.out.println("Already a client");
    }           //3
    public void newCash(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert name followed by the sum on the next line:");
        String name = sc.nextLine();
        int cas = sc.nextInt();
        String aux = sc.nextLine();
        if(this.c.checkClient(name))
            this.c.addCash(name , cas);
        else
            System.out.println("Target client not found");
    }             //4
    public void seatAvailability(){
        int nr = -1;
        Scanner sc = new Scanner(System.in);
        while(nr<0 || nr>=this.c.getScreenList().size()){
            System.out.println("Input screen number:");
            nr = sc.nextInt();
        }
        this.c.getScreenList().get(nr).printScreenings();
        int nr2 = -1;
        while(nr2<0 || nr2>=this.c.getScreenList().get(nr).getScreenings().size()){
            System.out.println("Input screening number:");
            nr2 = sc.nextInt();
        }
        String aux = sc.nextLine();
        this.c.getScreenList().get(nr).printSeats(nr2);
    }    //5
    public void newTicketSale() throws IOException {
        String name = this.interogateName();
        Ticket t = this.c.sellTicket(name);
        ReadWriteService.getInstance().writeTicket("tickets.csv" , name , t);
    }       //6
    public void newTicketRefund(){
        String name = this.interogateName();
        this.c.refundTicket(name);
    }     //7
    public void newTicketPrint(){
        String name = this.interogateName();
        this.c.seeTickets(name);
    }      //8
    public void numberSold(){
        int ans = 0;
        for(int i=0 ; i<this.c.getScreenList().size() ; i++)
            ans += this.c.getScreenList().get(i).getOccupied();
        System.out.println(ans + " tickets sold");
    }          //9
    public void newBalance(){
        String name = this.interogateName();
        this.c.checkAccountCash(name);
    }          //10
    public void menu() throws IOException {
        Audit audit = new Audit("audit.csv");
        int c=-1;
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("1. Add a new screen \n2. Add a movie \n3. Add a new client \n4. Add cash to an account \n5. See seat availability \n6. Buy a ticket \n7. Refund ticket \n8. See tickets \n9. See number of tickets sold \n10. Check account balance \n11. Print audit");
            System.out.println("Input a valid operation number:");
            c = sc.nextInt();
            while(c < 0 || c > 11){
                System.out.println("Input a valid operation number:");
                c = sc.nextInt();
                String aux = sc.nextLine();
            }
            if(c == 0) return;
            switch (c){
                case 1:
                    audit.addRecord("New screen");
                    this.newScreen();
                    break;
                case 2:
                    audit.addRecord("New movie");
                    this.newMovie();
                    break;
                case 3:
                    audit.addRecord("New client");
                    this.newClient();
                    break;
                case 4:
                    audit.addRecord("New cash added");
                    this.newCash();
                    break;
                case 5:
                    audit.addRecord("New seat availability request");
                    this.seatAvailability();
                    break;
                case 6:
                    audit.addRecord("New ticket sale");
                    this.newTicketSale();
                    break;
                case 7:
                    audit.addRecord("New ticket refund");
                    this.newTicketRefund();
                    break;
                case 8:
                    audit.addRecord("New ticket print");
                    this.newTicketPrint();
                    break;
                case 9:
                    audit.addRecord("New tickets sold request");
                    this.numberSold();
                    break;
                case 10:
                    audit.addRecord("New balance check");
                    this.newBalance();
                    break;
                case 11:
                    audit.printRecords();
                    break;
            }

        }
    }
}
