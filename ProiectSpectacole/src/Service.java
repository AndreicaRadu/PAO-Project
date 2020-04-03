import java.sql.SQLOutput;
import java.util.Scanner;

public class Service extends Cinema {
    private Cinema c;
    private boolean configured = false;

    Service(){
        this.c = new Cinema();
    }
    public Cinema getC() {
        return c;
    }

    public void configCinema(){
        if(this.configured)
            return;
        this.configured = true; //this way I ensure it will only be configured once
        System.out.println("How many screens will it initially have");
        Scanner sc = new Scanner(System.in);
        int nrscreens = sc.nextInt();
        for(int i=0 ; i<nrscreens ; i++){
            System.out.println("For screen " + i + " give the number of rows , columns and price");
            int ro = sc.nextInt() , co = sc.nextInt() , pr = sc.nextInt();
            this.c.addScreen(i , ro , co , pr);
        }
        String aux = sc.nextLine();
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
    public void newScreen(){
        System.out.println("Input number of rows, columns, price:");
        int nr = this.c.getScreenList().size();
        Scanner sc = new Scanner(System.in);
        int ro = sc.nextInt() , co = sc.nextInt() , pr = sc.nextInt();
        this.c.addScreen(nr , ro , co , pr);
        String aux = sc.nextLine();
    }           //1
    public void newMovie(){
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
        aux = sc.nextLine();
    }            //2
    public void newClient(){
        System.out.println("Print your name and on the next line 1 if you're a student and 0 otherwise:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int k = sc.nextInt();
        boolean isStud = (k == 1);
        String aux = sc.nextLine();
        if(!this.c.checkClient(name))
            this.c.addClient(name , isStud);
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
    public void newTicketSale(){
        String name = this.interogateName();
        this.c.sellTicket(name);
    }       //6
    public void newTicketRefund(){
        String name = this.interogateName();
        this.c.refundTicket(name);
    }     //7  Singurul bug pe care il am este aici; faptul ca nu il sterge din lista clientului (dar ii returneaza banii elibereaza locul din matricea salii) se rezolva intr-o versiune viitoare
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
    public void menu(){
        int c=-1;
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("1. Add a new screen \n2. Add a movie \n3. Add a new client \n4. Add cash to an account \n5. See seat availability \n6. Buy a ticket \n7. Refund ticket \n8. See tickets \n9. See number of tickets sold \n10. Check account balance");
            System.out.println("Input a valid operation number:");
            c = sc.nextInt();
            while(c < 0 || c > 10){
                System.out.println("Input a valid operation number:");
                c = sc.nextInt();
                String aux = sc.nextLine();
            }
            if(c == 0) return;
            switch (c){
                case 1:
                    this.newScreen();
                    break;
                case 2:
                    this.newMovie();
                    break;
                case 3:
                    this.newClient();
                    break;
                case 4:
                    this.newCash();
                    break;
                case 5:
                    this.seatAvailability();
                    break;
                case 6:
                    this.newTicketSale();
                    break;
                case 7:
                    this.newTicketRefund();
                    break;
                case 8:
                    this.newTicketPrint();
                    break;
                case 9:
                    this.numberSold();
                    break;
                case 10:
                    this.newBalance();
                    break;
            }

        }
    }
}
