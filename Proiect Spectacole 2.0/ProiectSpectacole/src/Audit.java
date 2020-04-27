import java.io.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

public class Audit {
    private final String path;

    Audit(String p){
        this.path = p;
    }

    void addRecord(String operation) throws IOException {
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String tm = ts.toString();
        FileWriter fw = new FileWriter(this.path , true);
        fw.write(operation + "," + tm + "\n");
        fw.close();
    }
    void printRecords() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.path));
        String line;
        while((line = br.readLine()) != null) {
            String[] str = line.split(",");
            System.out.println(str[0] + " , " + str[1]);
        }
        br.close();
    }
}
