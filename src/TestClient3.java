import java.util.Scanner;

public class TestClient3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String msg = "";
        TestClientClassTwo client = new TestClientClassTwo(4444);

        while (!msg.equals("end")) {
            msg = scan.nextLine();
            client.sendEcho(msg);
        }
    }
}
