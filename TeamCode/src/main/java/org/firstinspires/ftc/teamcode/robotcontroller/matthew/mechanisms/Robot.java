package org.firstinspires.ftc.teamcode.robotcontroller.matthew.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

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
