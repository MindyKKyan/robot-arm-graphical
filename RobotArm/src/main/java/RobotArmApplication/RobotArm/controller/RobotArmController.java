package RobotArmApplication.RobotArm.controller;

import RobotArmApplication.RobotArm.domain.ServoOnCommand;
import RobotArmApplication.RobotArm.service.RobotArmService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/robot")
public class RobotArmController {

    private final RobotArmService robotArmService;

    public RobotArmController(RobotArmService robotArmService) {
        this.robotArmService = robotArmService;
    }

    // Connect to the robot arm
    @GetMapping("/connect")
    public String connect() {
        return robotArmService.connect();
    }

//    // Send a move command to the robot arm
//    @PostMapping("/move")
//    public String move(@RequestBody RobotCommand command) {
//        return robotArmService.move(command);
//    }

    // Send a servo command to the robot arm
    @PostMapping("/servo")
    public String servo(@RequestBody ServoOnCommand command) {
        return robotArmService.servo(command);
    }

//    // Send a custom command to the robot arm
//    @PostMapping("/custom")
//    public String customCommand(@RequestBody String customCommand) {
//        return robotArmService.sendCustomCommand(customCommand);
//    }

    // Disconnect from the robot arm
    @GetMapping("/disconnect")
    public String disconnect() {
        return robotArmService.disconnect();
    }

}


