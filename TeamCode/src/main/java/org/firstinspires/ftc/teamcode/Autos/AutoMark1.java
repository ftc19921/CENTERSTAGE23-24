package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class AutoMark1 extends OpMode {
    Robot robot = new Robot();
    int Stage;
    int framesRan;
    int Location;
    double distance;
    //in Inches
    double Forward;
    double Sideways;
    double Rotation;
    int CameraOn;
    int Pause;


    @Override
    public void init() {
        robot.init(hardwareMap);
        CameraOn = 20;
        Pause = 0;
        framesRan = 0;
        int Stage = 0;

    }


    public void loop() {
        telemetry.addData("Location", robot.camera.getDebugString());
        framesRan++;
        if(framesRan<4) {
            Location = robot.camera.getLocation();
        }
        telemetry.addData("location",Location);
        if (Pause < 1) {
            if (Location == -1) {
                switch (Stage) {
                    case 0:

                        break;
                }
            } else if (Location == 0) {
                switch (Stage) {
                    case 0:


                        break;
                }
            } else if (Location == 1) {
                switch (Stage) {
                    case 0:


                        break;

                }
            }
        } else {
            Pause--;
            distance = 10000;
            Forward = 0;
            Sideways = 0;
            Rotation = 0;
        }
        double odometryX = robot.mecanumDrive.odometryX;
        double odometryY = robot.mecanumDrive.odometryY;

        auto(distance, Forward, Sideways, Rotation);
        telemetry.addData("Distance", Math.abs(odometryX) + Math.abs(odometryY));
    }


    public void auto(double Distance, double forwardPower, double sidewaysPower, double rotationalPower) {
        robot.mecanumDrive.updateOdometry();
        double odometryX = robot.mecanumDrive.odometryX;
        double odometryY = robot.mecanumDrive.odometryY;
        if (Math.abs(odometryX) + Math.abs(odometryY) < Distance*1104) {
            robot.mecanumDrive.updateOdometry();
            robot.mecanumDrive.Drive(forwardPower, sidewaysPower, rotationalPower, false);
        } else {
            Stage++;

            Pause = 20;
        }


    }


}
