package org.firstinspires.ftc.robotcontroller.matthew;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.matthew.mechanisms.MacunumDrive;

public class Robot {
//    public TankDrive tankDrive = new TankDrive();
    public MacunumDrive macunumDrive = new MacunumDrive();
//    public Drone drone = new Drone();

    public void init(HardwareMap hardwareMap){
//        tankDrive.init(hardwareMap);
        macunumDrive.init(hardwareMap);
//        drone.init(hardwareMap);
    }
}
