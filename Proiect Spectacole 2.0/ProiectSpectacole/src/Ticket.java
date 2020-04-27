public class Ticket {
    private int screenNumber;
    private int row;
    private int column;
    private int hour;
    private String movie;
    private int screeningNumber;
    private int price;

    Ticket(int s , int r , int c , int h , String m , int p , int scrNr){
        this.hour = h;
        this.column = c;
        this.row = r;
        this.screenNumber = s;
        this.movie = m;
        this.screeningNumber = scrNr;
        this.price = p;
    }
//===============================================
    public int getScreenNumber(){
        return this.screenNumber;
    }
    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }
    public int getHour(){
        return this.hour;
    }
    public String getMovie(){
        return this.movie;
    }
    public int getPrice() {
        return price;
    }
    public int getScreeningNumber() {
        return screeningNumber;
    }
    //=================================================
}
