package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Roboto;
@Autonomous
public class LowBarAuto extends OpMode {
    Roboto robot = new Roboto();
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        while(robot.mecanumDrive.odometryX<2){
        robot.mecanumDrive.Drive(-0.5,0,0);
        }
        while(true){
        robot.mecanumDrive.Drive(0,0,0);
        }
    }
}
