package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class EndEffecter {
    Servo wristServo;
    public void init(HardwareMap hardwareMap){
        wristServo=hardwareMap.get(Servo.class,"");
    }
    public void outTake(){
        wristServo.setPosition(1);
    }
    public void normalPosition(){
        wristServo.setPosition(0);
    }

}
