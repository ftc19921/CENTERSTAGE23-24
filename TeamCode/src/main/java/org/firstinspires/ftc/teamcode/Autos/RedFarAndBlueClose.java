package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class RedFarAndBlueClose extends OpMode {
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


    @Override
    public void init() {
        robot.init(hardwareMap);
        CameraOn = 20;
        Pause = 0;
        framesRan = 0;
        int Stage = 0;
        placePower=0;
        canPlace=false;


    }


    public void loop() {
        telemetry.addData("location: ", Location);
        telemetry.addData("Middle: ", robot.camera.LeftMiddleCol);
        telemetry.addData("Left: ", robot.camera.LeftCol);

        framesRan++;

        if(framesRan<1000) {
            Location = robot.camera.getBlueLocation();
        }else {

            if (Pause < 1) {
                if (Location == 1) {

                    switch (Stage) {
                        case 0:
                            //move forward 21 inches
                            distance = 21;
                            forward = 0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 1:
                            //move left 17 inches
                            distance = 7;
                            forward = 0;

                            sideways = -0.5;
                            rotation = 0;
                            break;
                        case 2:
                            //move back 7 inches
                            distance = 7;
                            forward = -0.5;
                            sideways = 0;
                            rotation = 0;
                            break;

                        case 3:
                            //move forward 41 inches
                            distance = 100000;
                            forward = 0;
                            sideways = 0;
                            rotation = 0;
                            break;
                    }


                } else if (Location == 0) {
                    switch (Stage) {
                        case 0:
                            //move forward 26 inches
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
                            //move forward 30 inches
                            distance = 3000;
                            forward = 0;
                            sideways = 0;
                            rotation = 0;
                            break;
                    }
                } else if (Location == -1) {

                    switch (Stage) {
                        case 0:
                            //move forward 21 inches
                            distance = 21;
                            forward = 0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 1:
                            //turn right 14 inches
                            distance = 14;
                            forward = 0;
                            sideways = 0;
                            rotation = 0.5;
                            break;
                        case 2:
                            //move forward 7 inches
                            distance = 8;
                            forward = 0.5;
                            sideways = 0;
                            rotation = 0;
                            break;

                        case 3:
                            //move back 8 inches
                            distance = 8;
                            forward = -0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 4:

                            distance = 10000000;
                            forward = -0;
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
        double odometryX = robot.mecanumDrive.odometryX;
        double odometryY = robot.mecanumDrive.odometryY;
        if (Math.sqrt((odometryX*odometryX) + (odometryY*odometryY)) < Distance*351){
            robot.mecanumDrive.updateOdometry();
            robot.mecanumDrive.Drive(forwardPower, sidewaysPower, rotationalPower);
        } else {
            Stage++;
            robot.mecanumDrive.resetOdometry();
            Pause = 50;
        }


    }


}
