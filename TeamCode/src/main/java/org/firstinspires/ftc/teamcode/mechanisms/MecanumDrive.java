package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumDrive {
    DcMotor frontRightMotor;
    DcMotor frontLeftMotor;
    DcMotor backRightMotor;
    DcMotor backLeftMotor;
    DcMotor OdometryX;
    DcMotor OdometryY;
    public double odometryX;
    public double odometryY;


    public void init(HardwareMap hardwareMap) {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        OdometryX= hardwareMap.get(DcMotor.class, "OdometryX");
        OdometryY= hardwareMap.get(DcMotor.class, "OdometryY");


        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        OdometryX.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        OdometryY.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
    private void setPowers(double frontRightPower,double frontLeftPower,double backLeftPower,double backRightPower){
        double maxPower=1.0;
        maxPower=Math.max(maxPower,frontRightPower);
        maxPower=Math.max(maxPower,frontLeftPower);
        maxPower=Math.max(maxPower,backRightPower);
        maxPower=Math.max(maxPower,backLeftPower);

        frontRightPower /=maxPower;
        frontLeftPower /=maxPower;
        backRightPower /=maxPower;
        backLeftPower /=maxPower;

    }
    public void Drive(double forwardPower,double rightPower,double turnPower){

        double frontLeftPower = forwardPower+rightPower+turnPower;
        double backLeftPower = forwardPower-rightPower+turnPower;
        double frontRightPower = forwardPower-rightPower-turnPower;
        double backRightPower = forwardPower+rightPower-turnPower;

        odometryX=OdometryX.getCurrentPosition();
        odometryY=OdometryY.getCurrentPosition();


        setPowers(frontRightPower,frontLeftPower,backLeftPower,backRightPower);


        frontRightMotor.setPower(frontRightPower);
        frontLeftMotor.setPower(frontLeftPower);
        backRightMotor.setPower(backRightPower);
        backLeftMotor.setPower(backLeftPower);
    }
    public void Reset(){
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);


    }


}
