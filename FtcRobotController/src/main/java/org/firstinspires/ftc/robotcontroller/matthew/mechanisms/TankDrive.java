package org.firstinspires.ftc.robotcontroller.matthew.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TankDrive implements Mechanism{
    public DcMotor leftMotor;
    public DcMotor rightMotor;

    @Override
    public void init(HardwareMap hardwareMap) {
        leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right_motor");

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void setPowers(double leftPower, double rightPower) {
        double maxSpeed = 1.0;

        maxSpeed = Math.max(maxSpeed, Math.abs(leftPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(rightPower));

        leftPower /= maxSpeed;
        rightPower /= maxSpeed;

        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }
    public void drive(double forward, double turn) {
        double leftPower = forward + turn;
        double rightPower = forward - turn;
        setPowers(leftPower, rightPower);
    }
}
