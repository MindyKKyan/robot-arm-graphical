package RobotArmApplication.RobotArm.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RobotArmClient {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    // IP address and port of Robot Arm
//    private final String robotArmIp = "192.168.240.1";
    private final String robotArmIp = "192.168.1.1";
    private final int robotArmPort = 502;

    // Constructing Connection
    public void connectToRobotArm() {
        try {
            socket = new Socket("192.168.1.1", 502);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to Robot Arm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sending DRL commands to Robot Arm
    public String sendDrlCommand(String drlCommand) {
        try {
            out.println(drlCommand);
            System.out.println("DRL Command sent: " + drlCommand);
            return receiveResponse(); // receive response message
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Receive responds from Robot Arm
    private String receiveResponse() {
        try {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }
            System.out.println("Response received: " + response);
            return response.toString(); // receive response message
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Sending Speed Commands
    public String sendSpeedCommand(String speedCommand) {
        try {
            out.println(speedCommand);
            System.out.println("Speed Command sent: " + speedCommand);
            return receiveResponse(); // receive response message
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending speed command: " + e.getMessage();
        }
    }

    // Sending Safe Point Command
    public String sendSafePointCommand(String safePointCommand) {
        try {
            out.println(safePointCommand);
            System.out.println("Safe Point Command sent: " + safePointCommand);
            return receiveResponse(); // receive response message
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending safe point command: " + e.getMessage();
        }
    }



    // Disconnect from Robot Arm
    public void disconnect() {
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("Disconnected from Robot Arm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
