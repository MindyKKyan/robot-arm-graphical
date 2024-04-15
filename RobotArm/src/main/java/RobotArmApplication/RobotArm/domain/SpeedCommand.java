package RobotArmApplication.RobotArm.domain;

public class SpeedCommand {

    // DTO for Speed Command
        private Double maxSpeedJ;
        private Double maxAccelerationJ;
        private Double speedJ;
        private Double accelerationJ;
        private Double decelerationJ;
        private Double maxAccelerationL;
        private Double maxDecelerationL;
        private Double speedL;
        private Double accelerationL;
        private Double decelerationL;

        // Getters and setters
        public Double getMaxSpeedJ() {
            return maxSpeedJ;
        }

        public void setMaxSpeedJ(Double maxSpeedJ) {
            this.maxSpeedJ = maxSpeedJ;
        }

        public Double getMaxAccelerationJ() {
            return maxAccelerationJ;
        }

        public void setMaxAccelerationJ(Double maxAccelerationJ) {
            this.maxAccelerationJ = maxAccelerationJ;
        }

        public Double getMaxAccelerationL() {
            return maxAccelerationL;
        }

        public void setMaxAccelerationL(Double maxAccelerationL) {
            this.maxAccelerationL = maxAccelerationL;
        }

        public Double getAccelerationJ() {
            return accelerationJ;
        }

        public void setAccelerationJ(Double accelerationJ) {
            this.accelerationJ = accelerationJ;
        }

        public Double getDecelerationJ() {
            return decelerationJ;
        }

        public void setDecelerationJ(Double decelerationJ) {
            this.decelerationJ = decelerationJ;
        }

        public Double getMaxDecelerationL() {
            return maxDecelerationL;
        }

        public void setMaxDecelerationL(Double maxDecelerationL) {
            this.maxDecelerationL = maxDecelerationL;
        }

        public Double getSpeedL() {
            return speedL;
        }

        public void getSpeedL(Double speedL) {
            this.speedL = speedL;
        }

        public Double getAccelerationL() {
            return accelerationL;
        }

        public void setAccelerationL(Double accelerationL) {
            this.accelerationL = accelerationL;
        }

        public Double getSpeedJ() {
            return speedJ;
        }

        public void setSpeedJ(Double speedJ) {
            this.speedJ = speedJ;
        }

        public Double geDecelerationL() {
            return decelerationL;
        }

        public void setDecelerationL(Double decelerationL) {
            this.decelerationL = decelerationL;
        }

        // ... continue with getters and setters for other additional fields ...

}
