package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechinisms.Arm;
import org.firstinspires.ftc.teamcode.Mechinisms.Camera;
import org.firstinspires.ftc.teamcode.Mechinisms.Drone;
import org.firstinspires.ftc.teamcode.Mechinisms.Intake;
import org.firstinspires.ftc.teamcode.Mechinisms.MecanumDrive;

public class Robot {
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public Intake intake = new Intake();
    public Arm arm = new Arm();
    public Camera camera = new Camera();
    public Drone drone = new Drone();
    public void init(HardwareMap hardwareMap){
       mecanumDrive.init(hardwareMap);
       intake.init(hardwareMap);
       arm.init(hardwareMap);
       camera.init(hardwareMap);
       drone.init(hardwareMap);
    }

}
