import com.CinemaService.*;
import com.CinemaPack.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Service s = new Service();
        s.configCinema();
        s.menu();
    }
}
