package org.firstinspires.ftc.robotcontroller.matthew;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.ftccommon.internal.manualcontrol.responses.ImuAngularVelocityResponse;
import org.firstinspires.ftc.robotcontroller.matthew.mechanisms.Drone;
import org.firstinspires.ftc.robotcontroller.matthew.mechanisms.MacunumDrive;
import org.firstinspires.ftc.robotcontroller.matthew.mechanisms.TankDrive;

public class Robot {
    public TankDrive tankDrive = new TankDrive();
    public Drone drone = new Drone();

    public void init(HardwareMap hardwareMap){
        tankDrive.init(hardwareMap);
        drone.init(hardwareMap);
    }
}
