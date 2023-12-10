package org.firstinspires.ftc.teamcode.Mechinisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.SerialNumber;

import org.firstinspires.ftc.teamcode.Autos.AutoMark1;

public class Intake {
    CRServo placeServo;
    public void init(HardwareMap hardwareMap){
        placeServo = hardwareMap.get(CRServo.class,"placeServo");
    }
    public void place(double power,boolean canPlace){if(canPlace){placeServo.setPower(power);}}

}
