package org.firstinspires.ftc.teamcode.robotcontroller.matthew.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drone {
    public CRServo droneMotor;
    public void init(HardwareMap hardwareMap) {
        droneMotor = hardwareMap.get(CRServo.class, "DroneMotor");
//        droneMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void launch(boolean move) {
        if (move) {
            droneMotor.setPower(1);
        } else {
            droneMotor.setPower(0);
        }
    }
}
