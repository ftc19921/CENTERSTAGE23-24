package org.firstinspires.ftc.robotcontroller.matthew.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drone {
    public DcMotor droneMotor;
    public void init(HardwareMap hardwareMap) {
        droneMotor = hardwareMap.get(DcMotor.class, "DroneMotor");
        droneMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void launch(boolean move) {
        if (move) {
            droneMotor.setPower(1);
        } else {
            droneMotor.setPower(0);
        }
    }
}
