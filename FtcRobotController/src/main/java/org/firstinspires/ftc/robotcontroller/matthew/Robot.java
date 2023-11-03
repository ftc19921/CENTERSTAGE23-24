package org.firstinspires.ftc.robotcontroller.matthew;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.ftccommon.internal.manualcontrol.responses.ImuAngularVelocityResponse;
import org.firstinspires.ftc.robotcontroller.matthew.mechanisms.Drone;
import org.firstinspires.ftc.robotcontroller.matthew.mechanisms.MacunumDrive;

public class Robot {
    public MacunumDrive macunumDrive = new MacunumDrive();
    public Drone drone = new Drone();

    public void init(HardwareMap hardwareMap){
        macunumDrive.init(hardwareMap);
        drone.init(hardwareMap);
    }
}
