package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SUSspend implements Mechanism{
    static DcMotor hangMotor;
    @Override
    public void init(HardwareMap hardwareMap) {
        hangMotor = hardwareMap.get(DcMotor.class, "hangMotor");
    }

    public void move(boolean upOrDown){ //value of true moves the arm up and false moves it down
        float distanceMoved = 0;
        if (upOrDown) {

            while (distanceMoved<100) {
                hangMotor.setPower(1);
                distanceMoved += 1;
            }

        }else {
            while (distanceMoved>0) {
                hangMotor.setPower(-1);
                distanceMoved -= 1;
        }
        }
    }
}
