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
        if (posX < 1500) {
            forward += 0.1;
        }
        if (posX > 2000){
            forward -= 0.1;
        }
        if (posY > -145000 & posX > 1500){
            right += 0.1;
        }
        if (posY < -150000){
            right -= 0.1;
        }




        robot.mecanumDrive.Drive(forward, right, 0, false);

    }
    }

