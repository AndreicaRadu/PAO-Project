import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Service s = new Service();
        s.configCinema();
        s.menu();
    }
}
