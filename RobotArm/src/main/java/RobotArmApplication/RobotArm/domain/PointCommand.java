package RobotArmApplication.RobotArm.domain;

import java.util.List;

/**
 * Represents a command containing a list of points to be sent to the robot.
 */
public class PointCommand {

    // List of byte arrays representing individual points in the command
    private List<byte[]> points;


    // Default constructor.
    public PointCommand() {
    }

    /**
     * Parameterized constructor to initialize with a list of points.
     *
     * @param points List of byte arrays representing individual points in the command
     */
    public PointCommand(List<byte[]> points) {
        this.points = points;
    }

    /**
     * Get the list of points.
     *
     * @return List of byte arrays representing individual points in the command
     */
    public List<byte[]> getPoints() {
        return points;
    }

    /**
     * Set the list of points.
     *
     * @param points List of byte arrays representing individual points in the command
     */
    public void setPoints(List<byte[]> points) {
        this.points = points;
    }
}
