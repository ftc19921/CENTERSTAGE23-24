package org.firstinspires.ftc.teamcode.Mechinisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive {
    DcMotor frontRightMotor;
    DcMotor frontLeftMotor;
    DcMotor backRightMotor;
    DcMotor backLeftMotor;
    DcMotor odometryPodX;
    DcMotor odometryPodY;
    public double odometryX;
    public double odometryY;


    public void init(HardwareMap hardwareMap) {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "0");
        frontRightMotor = hardwareMap.get(DcMotor.class, "1");
        backLeftMotor = hardwareMap.get(DcMotor.class, "2");
        backRightMotor = hardwareMap.get(DcMotor.class, "3");
        odometryPodX = hardwareMap.get(DcMotor.class, "ex1");
        odometryPodY = hardwareMap.get(DcMotor.class, "ex0");


        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        odometryPodY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odometryPodX.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odometryPodX.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        odometryPodY.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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


        frontRightMotor.setPower(-frontRightPower);
        frontLeftMotor.setPower(-frontLeftPower);
        backRightMotor.setPower(-backRightPower);
        backLeftMotor.setPower(-backLeftPower);
    }
    public void Drive(double forwardPower,double rightPower,double turnPower){
        double frontLeftPower = forwardPower+rightPower+turnPower;
        double backLeftPower = forwardPower-rightPower+turnPower;
        double frontRightPower = forwardPower-rightPower-turnPower;
        double backRightPower = forwardPower+rightPower-turnPower;

        setPowers(frontRightPower, frontLeftPower, backLeftPower, backRightPower);
    }

    public void updateOdometry(){
        odometryX=odometryPodX.getCurrentPosition();
        odometryY=odometryPodY.getCurrentPosition();
    }
    public void resetOdometry(){
       odometryPodX.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odometryPodY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odometryPodX.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        odometryPodY.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


}
