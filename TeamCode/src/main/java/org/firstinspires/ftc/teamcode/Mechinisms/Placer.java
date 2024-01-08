package org.firstinspires.ftc.teamcode.Mechinisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Placer {
    CRServo clawServo;
    CRServo microArm;
    public void init(HardwareMap hardwareMap) {
        clawServo = hardwareMap.get(CRServo.class, "claw");
        microArm = hardwareMap.get(CRServo.class, "microArm");
    }

    public void setClawPower(double power) {
        clawServo.setPower(power);
    }

    public void setMicroArmPower(double power) {
        microArm.setPower(power);
        if (power == 0) {
            microArm.setPower(0.00001);
        }
    }
}
