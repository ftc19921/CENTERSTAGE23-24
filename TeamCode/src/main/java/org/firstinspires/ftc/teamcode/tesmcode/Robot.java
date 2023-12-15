package org.firstinspires.ftc.teamcode.tesmcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.tesmcode.Mechinisms.Arm;
import org.firstinspires.ftc.teamcode.tesmcode.Mechinisms.Camera;
import org.firstinspires.ftc.teamcode.tesmcode.Mechinisms.Drone;
import org.firstinspires.ftc.teamcode.tesmcode.Mechinisms.Intake;
import org.firstinspires.ftc.teamcode.tesmcode.Mechinisms.MecanumDrive;

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
