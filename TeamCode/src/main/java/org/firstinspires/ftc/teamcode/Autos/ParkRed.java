package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class ParkRed extends OpMode{
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
        if (posY < 5) {
            forward += 0.1;
        }
        if (posY > 6){
            forward -= 0.1;
        }
        if (posX < 145 & posY > 5){
            right -= 1;
        }
        if (posX > 150){
            right += 1;
        }




        robot.mecanumDrive.Drive(forward, right, 0, false);

    }
    }

