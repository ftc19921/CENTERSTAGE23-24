package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

import java.sql.Driver;


public class AutoDrive {

    public MecanumDrive mecanumDrive = new MecanumDrive();



    public void init(HardwareMap hardwareMap){
        mecanumDrive.init(hardwareMap);
    }
    public void Auto(double Distance,double forwardPower,double sidewaysPower,double rotationalPower,int Stage,double odometryX, double odometryY){

        if(Math.abs(odometryX)+Math.abs(odometryY)<Distance){
            mecanumDrive.Drive(forwardPower, sidewaysPower,rotationalPower);
        }else{
            Stage++;

        }


    }

}
