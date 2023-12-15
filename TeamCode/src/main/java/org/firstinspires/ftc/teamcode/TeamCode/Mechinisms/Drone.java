package org.firstinspires.ftc.teamcode.TeamCode.Mechinisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drone {
    CRServo droneServo;

    public void init(HardwareMap hardwareMap){
        droneServo = hardwareMap.get(CRServo.class,"droneServo");

    }
    public void Launch(){

        droneServo.setPower(-1);
    }
    public void Stop(){
        droneServo.setPower(0);
    }

}
