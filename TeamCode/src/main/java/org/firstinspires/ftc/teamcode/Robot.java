package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechinisms.Arm;
import org.firstinspires.ftc.teamcode.Mechinisms.Camera;
import org.firstinspires.ftc.teamcode.Mechinisms.Drone;
import org.firstinspires.ftc.teamcode.Mechinisms.HamsterDrive;
import org.firstinspires.ftc.teamcode.Mechinisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechinisms.TankDrive;

public class Robot {
    public HamsterDrive hamsterDrive = new HamsterDrive();
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public TankDrive tankDrive = new TankDrive();
    public Arm.MicroPlacer microPlacer = new Arm.MicroPlacer();
    public Arm arm = new Arm();
    public Camera camera = new Camera();
    public Drone drone = new Drone();
    public Arm.ClawArm clawArm = new Arm.ClawArm();
    public Arm.Claw claw = new Arm.Claw();

    public void init(HardwareMap hardwareMap){
        hamsterDrive.init(hardwareMap);
//       mecanumDrive.init(hardwareMap);
//       tankDrive.init(hardwareMap);
//       microPlacer.init(hardwareMap);
//       arm.init(hardwareMap);
//       camera.init(hardwareMap);
//       drone.init(hardwareMap);
//       clawArm.init(hardwareMap);
//       claw.init(hardwareMap);
    }
}
