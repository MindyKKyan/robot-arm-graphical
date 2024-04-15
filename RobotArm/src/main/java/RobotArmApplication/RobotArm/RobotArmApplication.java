package RobotArmApplication.RobotArm;

import RobotArmApplication.RobotArm.network.RobotArmClient;
import RobotArmApplication.RobotArm.service.RobotArmService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RobotArmApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotArmApplication.class, args);
    }

    // Bean for RobotArmClient
    @Bean
    public RobotArmClient robotArmClient() {
        return new RobotArmClient();
    }

    // Bean for RobotArmService
    @Bean
    public RobotArmService robotArmService(RobotArmClient robotArmClient) {
        return new RobotArmService(robotArmClient);
    }
}
