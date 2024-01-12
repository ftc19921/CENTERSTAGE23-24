package org.firstinspires.ftc.teamcode.Autos.EthanAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class ParkBlue extends OpMode{
    double framesRan = 0;
    double forward;
    double right;
    double posX;
    double posY;
    Robot robot = new Robot();
    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void loop(){
        forward = 0;
        right = 0;
        robot.mecanumDrive.updateOdometry();
        posX = robot.mecanumDrive.odometryX;
        posY = robot.mecanumDrive.odometryY;
        telemetry.addData("positon x:", posX);
        telemetry.addData("positon y:", posY);
        framesRan ++;
        if (posX < 802) {
            forward += 0.1;
        }
        if (posX > 812){
            forward -= 0.1;
        }
        if (posY < 30100 & posY > 802){
            right -= 0.2;
        }
        if (posY > 31100){
            right += 0.2;
        }





        robot.mecanumDrive.Drive(forward, right, 0);

    }
    }

