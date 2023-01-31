import java.util.Scanner;

public class TestClient2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String msg = "";
        TestClientClass client = new TestClientClass(4443);

        while (!msg.equals("end")) {
            msg = scan.nextLine();
            client.sendEcho(msg);
        }
    }
}
