package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class RedCloseAndBlueFar extends OpMode {
    Robot robot = new Robot();
    int Stage;
    int framesRan;
    int Location;
    double distance;
    //in Inches
    double forward;
    double sideways;
    double rotation;
    int CameraOn;
    int Pause;
    double placePower;
    public boolean canPlace;
    double odometryX;
    double odometryY;

    @Override
    public void init() {
        robot.init(hardwareMap);
        CameraOn = 20;
        Pause = 0;
        framesRan = 0;
        int Stage = 0;
        placePower=0;
        canPlace=false;
        Location = robot.camera.getLocation();
        telemetry.addData("Location", robot.camera.getDebugString());

    }


    public void loop() {
        telemetry.addData("Location", robot.camera.getDebugString());
        telemetry.addData("Stage", Stage);
        framesRan++;

        if(framesRan<1000) {
            Location = robot.camera.getLocation();
        }else {
            telemetry.addData("location", Location);
            if (Pause < 1) {
                if (Location == -1) {

                    switch (Stage) {
                        case 0:
                            //move forward 21 inches
                            distance = 21;
                            forward = 0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 1:
                            //turn left 17 inches
                            distance = 8;
                            forward = 0;

                            sideways = 0;
                            rotation = -0.5;
                            break;
                        case 2:

                            distance = 5;
                            forward = 0.5;

                            sideways = 0;
                            rotation = 0;
                            break;
                        case 3:
                            //move back 7 inches
                            distance = 8;
                            forward = -0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 4:
                            //move back 7 inches
                            distance = 10000;
                            forward = 0;
                            sideways = 0;
                            rotation = 0;
                            break;
                    }
                } else if (Location == 0) {
                    switch (Stage) {
                        case 0:
                            //move forward 28 inches
                            distance = 26;
                            forward = 0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 1:
                            //move back 7 inches
                            distance = 7;
                            forward = -0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 2:
                            //move back 7 inches
                            distance = 10000;
                            forward = 0;
                            sideways = 0;
                            rotation = 0;
                            break;

                    }
                } else if (Location == 1) {

                    switch (Stage) {
                        case 0:
                            //move forward 21 inches
                            distance = 21;
                            forward = 0.3;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 1:
                            //move right 5 inches
                            distance = 7;
                            forward = 0;
                            sideways = 0.3;
                            rotation = 0;
                            break;
                        case 2:
                            //move back 7 inches
                            distance = 7;
                            forward = -0.3;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 3:
                            //move back 7 inches
                            distance = 10000;
                            forward = 0;
                            sideways = 0;
                            rotation = 0;
                            break;




                    }
                }
            } else {
                Pause--;
                distance = 10000;
                forward = 0;
                sideways = 0;
                rotation = 0;

            }

            double odometryX = robot.mecanumDrive.odometryX;
            double odometryY = robot.mecanumDrive.odometryY;
            robot.mecanumDrive.updateOdometry();
            auto(distance, forward, sideways, rotation);
            telemetry.addData("Distance", Math.abs(odometryX) + Math.abs(odometryY));
            placePower = 1;
            robot.microPlacer.place(placePower, canPlace);
        }
    }


    public void auto(double Distance, double forwardPower, double sidewaysPower, double rotationalPower) {
        robot.mecanumDrive.updateOdometry();
        odometryX = robot.mecanumDrive.odometryX;
        odometryY = robot.mecanumDrive.odometryY;
        if (Math.abs(odometryX) + Math.abs(odometryY) < Distance*351){
            robot.mecanumDrive.updateOdometry();
            robot.mecanumDrive.Drive(forwardPower, sidewaysPower, rotationalPower);
        } else {
            Stage++;
            robot.mecanumDrive.resetOdometry();
            Pause = 50;
        }


    }


}
