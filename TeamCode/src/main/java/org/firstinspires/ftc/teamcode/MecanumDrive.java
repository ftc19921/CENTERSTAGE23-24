package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive implements Mechanism {
    public static final double TEST_POWER = 0.2;
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    @Override
    public void init(HardwareMap hardwareMap) {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void setPowers(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
        double maximumSpeed = 1.0;
        maximumSpeed = Math.max(maximumSpeed, Math.abs(frontLeftPower));
        maximumSpeed = Math.max(maximumSpeed, Math.abs(frontRightPower));
        maximumSpeed = Math.max(maximumSpeed, Math.abs(backLeftPower));
        maximumSpeed = Math.max(maximumSpeed, Math.abs(backRightPower));

        frontLeftPower /= maximumSpeed;
        frontRightPower /= maximumSpeed;
        backLeftPower /= maximumSpeed;
        backRightPower /= maximumSpeed;

        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backLeftMotor.setPower(backLeftPower);
        backRightMotor.setPower(backRightPower);
    }

    public void drive(double forwardSpeed, double rightSpeed, double rotateSpeed) {
        double frontLeftPower = forwardSpeed + rightSpeed + rotateSpeed;
        double frontRightPower = forwardSpeed - rightSpeed - rotateSpeed;
        double backLeftPower = forwardSpeed - rightSpeed + rotateSpeed;
        double backRightPower = forwardSpeed + rightSpeed - rotateSpeed;

        setPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
    }
    public void test(boolean frontLeft, boolean frontRight, boolean backLeft, boolean backRight){
        double frontLeftPower =0, frontRightPower =0, backLeftPower =0, backRightPower=0;
        if(frontLeft){
            frontLeftPower = TEST_POWER;
        }
        if(frontRight){
            frontRightPower = TEST_POWER;
        }
        if(backLeft){
            backLeftPower = TEST_POWER;
        }
        if(backRight){
            backRightPower = TEST_POWER;
        }
        setPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
    }
}
