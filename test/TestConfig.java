import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    public static void main(String[] args) {
        Properties pro = new Properties();
        try {
            pro.load(TestConfig.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
         String str = (String)pro.get("initTankCount");
        System.out.println(str);
    }
}
