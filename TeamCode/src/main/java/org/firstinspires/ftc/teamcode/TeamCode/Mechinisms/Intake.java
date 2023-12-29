package org.firstinspires.ftc.teamcode.TeamCode.Mechinisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    CRServo placeServo;
    public void init(HardwareMap hardwareMap){
        placeServo = hardwareMap.get(CRServo.class,"microPlaceServo");
    }
    public void place(double power,boolean canPlace){if(canPlace){placeServo.setPower(power);}}

}
