Graphical Software for SCARA Robot Arm (VUE + Spingboot)

Blockly: Frontend part of the software (details in README.md of File Blockly)

RobotArm: Backend part of the software

**Backend Details**
IntelliJ IDEA is used
Detailed configurations see 'pom.xml'
├─.idea
├─.mvn
│  └─wrapper
├─src
│  ├─main
│  │  ├─java
│  │  │  └─RobotArmApplication
│  │  │      └─RobotArm
│  │  │          ├─config
│  │  │          ├─controller
│  │  │          ├─domain
│  │  │          ├─network
│  │  │          └─service
│  │  └─resources
│  │      ├─static
│  │      └─templates
│  └─test
│      └─java
│          └─RobotArmApplication
│              └─RobotArm
└─target
    ├─classes
    │  └─RobotArmApplication
    │      └─RobotArm
    │          ├─config
    │          ├─controller
    │          ├─domain
    │          ├─network
    │          └─service
    ├─generated-sources
    │  └─annotations
    ├─generated-test-sources
    │  └─test-annotations
    └─test-classes
        └─RobotArmApplication
            └─RobotArm
