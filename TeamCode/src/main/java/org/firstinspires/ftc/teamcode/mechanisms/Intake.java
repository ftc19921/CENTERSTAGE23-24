package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
public class Intake {
    CRServo intake;
    Servo intakeWrist;
    public void init(HardwareMap hardwareMap){
        intake = hardwareMap.get(CRServo.class,"IntakeServo");
        intakeWrist = hardwareMap.get(Servo.class,"IntakeWrist");
    }
    public void in(){intake.setPower(1);}
    public void out(){intake.setPower(-1);}
    public void stop(){intake.setPower(0);}
    public void deposit(){intakeWrist.setPosition(1);}
    public void normalPosition(){intakeWrist.setPosition(0);}
}
