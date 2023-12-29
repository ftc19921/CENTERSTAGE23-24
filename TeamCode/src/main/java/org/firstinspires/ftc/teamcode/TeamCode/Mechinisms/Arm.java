package org.firstinspires.ftc.teamcode.TeamCode.Mechinisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    DcMotor HangingMotor;
    public void init(HardwareMap hardwareMap){
        HangingMotor = hardwareMap.get(DcMotor.class,"HangingMotor");
        HangingMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void Hang(){
        HangingMotor.setPower(-1);
    }
    public void DeHang(){HangingMotor.setPower(1);}
    public void Stop(){
        HangingMotor.setPower(0);
    }
}
