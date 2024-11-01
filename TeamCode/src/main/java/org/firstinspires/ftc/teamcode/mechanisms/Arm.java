package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    Servo ArmServo;
    public void init(HardwareMap hardwareMap){ArmServo = hardwareMap.get(Servo.class,"HangingMotor");}
    public void Extend(){ArmServo.setPosition(1);}
    public void Retract(){ArmServo.setPosition(0);}
}
