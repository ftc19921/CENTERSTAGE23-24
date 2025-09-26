package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Stilts {
    DcMotor StiltMotor;
    public void init(HardwareMap hardwareMap){
        StiltMotor =hardwareMap.get(DcMotor.class,"3");

    }
    public void SpinUp(){

        StiltMotor.setPower(0.3);
    }
    public void SpinDown(){

        StiltMotor.setPower(-0.3);
    }
    public void StopMotor(){
        StiltMotor.setPower(0);
    }

}
