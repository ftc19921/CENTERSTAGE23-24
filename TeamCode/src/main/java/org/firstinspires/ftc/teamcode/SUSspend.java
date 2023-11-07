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
        if (upOrDown) {
            //TODO MAKE ARM MOVE UP
        }
    }
}
