package org.firstinspires.ftc.teamcode.Mechinisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    float degreesToPos = -150; // Counter-clockwise: Positive; Clockwise: Negative;
    int tolerance = 25;

    DcMotor HangingMotor;

    int target;

    public void init(HardwareMap hardwareMap) {
        HangingMotor = hardwareMap.get(DcMotor.class, "HangingMotor");
        HangingMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HangingMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        target = 0;
    }

    public void flat() {
        target = (int) (0 * degreesToPos);
    }

    public void hang() {
        target = (int) (-90 * degreesToPos);
    }

    public void place() {
        target = (int) (-110 * degreesToPos);
    }

    public void setPower(float power) {
        HangingMotor.setPower(power);
        if (power != 0) {
            target = getPos();
        }
    }

    public int getPos() {
        return HangingMotor.getCurrentPosition();
    }

    public int getTarget() {
        return target;
    }

    public boolean moving() {
        return Math.abs(getPos()-target) > tolerance;
    }

    public void update() {
        if (moving()) {
            HangingMotor.setPower(getPos()-target > 0 ? -1 : 1);
        } else {
            HangingMotor.setPower(0);
        }
    }

    public static class Claw {

        Servo clawServo;
        public float pos = 0f;

        public void init(HardwareMap hardwareMap) {
            clawServo = hardwareMap.get(Servo.class, "clawServo");

            clawServo.setPosition(0);
        }

        public void move(float change) {
            pos = (float) Math.max(Math.min(pos+(change*0.1), 1), 0);
            clawServo.setPosition((double)pos);

        }
    }

    public static class ClawArm {
        Servo armServo;
        public float currentPos = 0;

        public void init(HardwareMap hardwareMap) {
            armServo = hardwareMap.get(Servo.class, "clawArm");
        }

        public void move(float change) {
            currentPos = (float) Math.max(Math.min(currentPos+(change*0.01), 1), 0);
            armServo.setPosition((double) currentPos);
        }
    }

    public static class MicroPlacer {
        CRServo placeServo;

        public void init(HardwareMap hardwareMap) {
            placeServo = hardwareMap.get(CRServo.class, "microPlaceServo");
        }

        public void place(double power, boolean canPlace) {
            if (canPlace) {
                placeServo.setPower(power);
            }
        }

    }

    public static class Placer {
        CRServo clawServo;
        CRServo microArm;
        public void init(HardwareMap hardwareMap) {
            clawServo = hardwareMap.get(CRServo.class, "claw");
            microArm = hardwareMap.get(CRServo.class, "microArm");
        }

        public void setClawPower(double power) {
            clawServo.setPower(power);
        }

        public void setMicroArmPower(double power) {
            microArm.setPower(power);
            if (power == 0) {
                microArm.setPower(0.00001);
            }
        }
    }
}
