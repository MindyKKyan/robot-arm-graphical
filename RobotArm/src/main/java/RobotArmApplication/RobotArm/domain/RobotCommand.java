package RobotArmApplication.RobotArm.domain;

public class RobotCommand {

    private Integer pointNumber;
    private String mode;
    private Double speed;
    private Double acceleration;
    private Double deceleration;
    private Double jerk;

    // Getters and setters
    public Integer getPointNumber() {
        return pointNumber;
    }

    public void setPointNumber(Integer pointNumber) {
        this.pointNumber = pointNumber;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    public Double getDeceleration() {
        return deceleration;
    }

    public void setDeceleration(Double deceleration) {
        this.deceleration = deceleration;
    }

    public Double getJerk() {
        return jerk;
    }

    public void setJerk(Double jerk) {
        this.jerk = jerk;
    }
}
