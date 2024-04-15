package RobotArmApplication.RobotArm.domain;

public class SafePointCommand {
    // DTO for Safe Point Command
    private Integer safeHeight;
    private Integer safePointNumber;

    // Getters and setters
    public Integer getSafeHeight() {
        return safeHeight;
    }

    public void setSafeHeight(Integer safeHeight) {
        this.safeHeight = safeHeight;
    }

    public Integer getSafePointNumber() {
        return safePointNumber;
    }

    public void setSafePointNumber(Integer safePointNumber) {
        this.safePointNumber = safePointNumber;
    }

}
