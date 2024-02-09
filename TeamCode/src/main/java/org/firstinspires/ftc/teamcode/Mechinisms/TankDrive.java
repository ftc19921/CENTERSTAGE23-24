package org.firstinspires.ftc.teamcode.Mechinisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TankDrive {
    DcMotor frontRightMotor;
    DcMotor frontLeftMotor;
    DcMotor backRightMotor;
    DcMotor backLeftMotor;
    public void init (HardwareMap hardwareMap){
        frontLeftMotor = hardwareMap.get(DcMotor.class,"0");
        frontRightMotor = hardwareMap.get(DcMotor.class,"1");
        backLeftMotor = hardwareMap.get(DcMotor.class,"2");
        backRightMotor = hardwareMap.get(DcMotor.class,"3");
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void setPowers(double forward,double turn){
       double FLPower = forward - turn;
       double FRPower = forward +turn;
       double BLPower = forward - turn;
       double BRPower = forward + turn;

       double maxPower = Math.max(Math.max(FLPower,FRPower),Math.max(BLPower,BRPower));

       if(maxPower>1){
           FLPower=FLPower/maxPower;
           FRPower=FRPower/maxPower;
           BLPower=BLPower/maxPower;
           BRPower=BRPower/maxPower;
       }
       frontLeftMotor.setPower(FLPower);
       frontRightMotor.setPower(FRPower);
       backLeftMotor.setPower(BLPower);
       backRightMotor.setPower(BRPower);
    }
}
