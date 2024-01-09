package org.firstinspires.ftc.teamcode.Mechinisms;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Claw {
    Servo clawServo;
    public float pos = 0.5f;

    public void init(HardwareMap hardwareMap) {
        clawServo = hardwareMap.get(Servo.class, "clawServo");
    }

    public void move(float change) {
        pos += change*0.01;
        clawServo.setPosition((double)pos);
    }
}
