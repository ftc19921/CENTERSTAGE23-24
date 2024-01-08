package org.firstinspires.ftc.teamcode.Mechinisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    float degreesToPos = 150;
    DcMotor HangingMotor;

    int target;

    public void init(HardwareMap hardwareMap) {
        HangingMotor = hardwareMap.get(DcMotor.class, "HangingMotor");
        HangingMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HangingMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        target = 0;
    }

    public void flat() {
        target = (int) (0 * degreesToPos);
    }

    public void hang() {
        target = (int) (-90 * degreesToPos);
    }

    public void place() {
        target = (int) (-110 * degreesToPos);
    }

    public void setPower(float power) {
        HangingMotor.setPower(power);
        if (power != 0) {
            target = getPos();
        }
    }

    public int getPos() {
        return HangingMotor.getCurrentPosition();
    }

    public int getTarget() {
        return target;
    }

    public boolean moving() {
        return Math.abs(getPos()-target) > 10;
    }

    public void update() {
        if (moving()) {
            HangingMotor.setPower(getPos()-target > 0 ? -1 : 1);
        }
    }
}
