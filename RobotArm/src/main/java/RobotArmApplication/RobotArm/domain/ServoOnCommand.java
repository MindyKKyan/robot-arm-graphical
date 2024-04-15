package RobotArmApplication.RobotArm.domain;

public class ServoOnCommand {
    private byte[] hexData;

    public ServoOnCommand() {
        // 初始化 hexData
        // 00 00 00 00 00 06 02 10 00 06 00 02 04 01 01 01 01
        this.hexData = new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x02, 0x10, 0x00, 0x06, 0x00, 0x02, 0x04, 0x01, 0x01, 0x01, 0x01};
    }

    public byte[] getHexData() {
        return hexData;
    }
//    // Set other servoOn Commands
//    public void setHexData(byte[] hexData) {
//        this.hexData = hexData;
//    }
}
