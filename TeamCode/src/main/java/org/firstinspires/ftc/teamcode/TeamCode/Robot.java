package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechinisms.Arm;
import org.firstinspires.ftc.teamcode.Mechinisms.Camera;
import org.firstinspires.ftc.teamcode.Mechinisms.Drone;
import org.firstinspires.ftc.teamcode.Mechinisms.MicroPlacer;
import org.firstinspires.ftc.teamcode.Mechinisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechinisms.Placer;

public class Robot {
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public MicroPlacer microPlacer = new MicroPlacer();
    public Arm arm = new Arm();
    public Camera camera = new Camera();
    public Drone drone = new Drone();
    public Placer placer = new Placer();
    public void init(HardwareMap hardwareMap){
       mecanumDrive.init(hardwareMap);
       microPlacer.init(hardwareMap);
       arm.init(hardwareMap);
       camera.init(hardwareMap);
       drone.init(hardwareMap);
       placer.init(hardwareMap);
    }
}
