package RobotArmApplication.RobotArm.service;

import RobotArmApplication.RobotArm.domain.RobotCommand;
import RobotArmApplication.RobotArm.domain.SafePointCommand;
import RobotArmApplication.RobotArm.domain.SpeedCommand;
import RobotArmApplication.RobotArm.network.RobotArmClient;
import org.springframework.stereotype.Service;

@Service
public class RobotArmService {

    private final RobotArmClient robotArmClient;

    public RobotArmService(RobotArmClient robotArmClient) {
        this.robotArmClient = robotArmClient;
    }

    // Method to connect to the robot arm
    public String connect() {
        robotArmClient.connectToRobotArm();
        return "Connected to Robot Arm";
    }

    // Method to process and send move commands
    public String move(RobotCommand command) {
        // Convert command to DRL command string
        String drlCommand = convertToDrlCommand(command, "MovP");
        // Send command and get response
        return robotArmClient.sendDrlCommand(drlCommand);
    }

    // Method to process and send servo commands
    public String servo(ServoOnCommand command) {
        // Convert command to DRL command string
        String drlCommand = convertToDrlCommand(command, "ServoOn");
        // Send command and get response
        return robotArmClient.sendDrlCommand(drlCommand);
    }

    // Method to send custom DRL commands
    public String sendCustomCommand(String customCommand) {
        // Directly send custom command
        return robotArmClient.sendDrlCommand(customCommand);
    }

    // Method to disconnect from the robot arm
    public String disconnect() {
        robotArmClient.disconnect();
        return "Disconnected from Robot Arm";
    }

    // Helper method to convert RobotCommand to DRL command string
    private String convertToDrlCommand(RobotCommand command, String commandType) {
        StringBuilder drlCommand = new StringBuilder();

        switch (commandType) {
            case "MovP":
                // For a MovP command, construct a command string with the necessary parameters.
                // Example: MovP(Point BMode Spd Acc Dec Jerk)
                drlCommand.append("MovP(")
                        .append(command.getPointNumber()).append(", ")
                        .append(command.getMode()).append(", ")
                        .append(command.getSpeed()).append(", ")
                        .append(command.getAcceleration()).append(", ")
                        .append(command.getDeceleration()).append(", ")
                        .append(command.getJerk())
                        .append(")");
                break;

            case "ServoOn":
                // For a ServoOn command, construct a command string.
                // Example: ServoOn(ax_idx)
                drlCommand.append("ServoOn(")
                        .append(command.getPointNumber()) // Assuming point number is used as ax_idx
                        .append(")");
                break;

            case "ServOff":
                // To be added
            // Add cases for other command types as necessary.

            default:
                // Handle unknown command types.
                drlCommand.append("UnknownCommand");
                break;
        }

        return drlCommand.toString();
    }

    public String setSpeed(SpeedCommand speedCommand) {
        String speedCommandString = convertSpeedCommandToDrl(speedCommand);
        return robotArmClient.sendSpeedCommand(speedCommandString);
    }

    public String moveToSafePoint(SafePointCommand safePointCommand) {
        String safePointCommandString = convertSafePointCommandToDrl(safePointCommand);
        return robotArmClient.sendSafePointCommand(safePointCommandString);
    }

    private String convertSpeedCommandToDrl(SpeedCommand command) {
        // Constructing DRL Command String
        return String.format("SetSpeed(%f, %f, %f, %f, %f)",
                command.getMaxSpeedJ(), command.getMaxAccelerationJ(),
                command.getSpeedJ(), command.getAccelerationJ(), command.getDecelerationJ());
    }

    private String convertSafePointCommandToDrl(SafePointCommand command) {
        // Constructing DRL Command String
        return String.format("MoveToSafePoint(%d, %d)",
                command.getSafeHeight(), command.getSafePointNumber());
    }
}
