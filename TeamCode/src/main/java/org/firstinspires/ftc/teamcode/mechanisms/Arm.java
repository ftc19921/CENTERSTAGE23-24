package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    Servo ArmServo;
    Servo Arm2ndServo;

    public void init(HardwareMap hardwareMap){
        ArmServo = hardwareMap.get(Servo.class,"serv0");
        Arm2ndServo = hardwareMap.get(Servo.class,"serv4");
    }
    public void Extend(){

       ArmServo.setPosition(0.5);

    }
    public void Retract(){

        ArmServo.setPosition(0);
    }

}
