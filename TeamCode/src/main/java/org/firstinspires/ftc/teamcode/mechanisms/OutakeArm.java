package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OutakeArm {
    DcMotor wenchMotor;
    public void init(HardwareMap hardwareMap){
        wenchMotor = hardwareMap.get(DcMotor.class,"wenchMotor");
    }
    public void hoist(){
        wenchMotor.setPower(1);
    }
    public void release(){
        wenchMotor.setPower(-1);
    }
}
