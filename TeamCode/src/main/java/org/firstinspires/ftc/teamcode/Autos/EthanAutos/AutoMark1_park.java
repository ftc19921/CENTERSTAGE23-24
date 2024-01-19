package org.firstinspires.ftc.teamcode.Autos.EthanAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class AutoMark1_park extends OpMode {
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
        Location = robot.camera.getLocation();
        telemetry.addData("Location", robot.camera.getDebugString());

    }


    public void loop() {
        telemetry.addData("Location", robot.camera.getDebugString());
        telemetry.addData("Stage", Stage);
        framesRan++;

        if(framesRan<10) {
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
                            //move left 17 inches
                            distance = 17;
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
                        /*case 10:
                            //turn right 11 inches
                            distance = 11;
                            forward = 0;
                            sideways = 0;
                            rotation = 0.5;
                            break;
                        case 4:
                            //move forward 41 inches
                            distance = 41;
                            forward = 0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 5:
                            //move left 10 inches
                            distance = 7;
                            forward = 0;
                            sideways = 0.5;
                            rotation = 0;
                            break;
                        case 6:
                            //move left 3 inches and place pixal
                            distance = 3;
                            forward = 0;
                            sideways = 0.1;
                            rotation = 0;
                            canPlace = true;
                            break;
                        case 7:
                            //move back 5 inches
                            canPlace = false;
                            distance = 5;
                            forward = -0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 8:
                            //move left 15 inches
                            distance = 10;
                            forward = 0;
                            sideways = -0.5;
                            rotation = 0;
                            break;

                         */
                    }


                } else if (Location == 0) {
                    switch (Stage) {
                        case 0:
                            //move forward 28 inches
                            distance = 28;
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
                       /* case 10:
                            //turn right 11 inches
                            distance = 11;
                            forward = 0;
                            sideways = 0;
                            rotation = 0.5;
                            break;
                        case 4:
                            //move forward 30 inches
                            distance = 30;
                            forward = 0.5;
                            sideways = 0;
                            rotation = 0;
                            break;

                        case 5:
                            //move left 3 inches and place pixal
                            distance = 3;
                            forward = 0;
                            sideways = 0.1;
                            rotation = 0;
                            canPlace = true;
                            break;
                        case 6:
                            //move back 5 inches
                            canPlace = false;
                            distance = 5;
                            forward = -0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 7:
                            //move right 15 inches
                            distance = 15;
                            forward = 0;
                            sideways = 0.5;
                            rotation = 0;
                            break;

                        */
                    }
                } else if (Location == 1) {

                    switch (Stage) {
                        case 0:
                            //move forward 21 inches
                            distance = 21;
                            forward = 0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 1:
                            //move right 5 inches
                            distance = 8;
                            forward = 0;
                            sideways = 0.5;
                            rotation = 0;
                            break;
                        case 2:
                            //move back 7 inches
                            distance = 7;
                            forward = -0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                            /*
                        case 3:
                            //turn right 11 inches
                            distance = 11;
                            forward = 0;
                            sideways = 0;
                            rotation = 0.5;
                            break;
                        case 4:
                            //move forward 30 inches
                            distance = 30;
                            forward = 0.5;
                            sideways = 0;
                            rotation = 0;

                            break;
                        case 5:
                            //move left 3 inches and place pixal
                            distance = 3;
                            forward = 0;
                            sideways = 0.1;
                            rotation = 0;
                            canPlace = true;
                            break;

                        case 6:
                            //move back 5 inches
                            canPlace = false;
                            distance = 5;
                            forward = -0.5;
                            sideways = 0;
                            rotation = 0;
                            break;
                        case 7:
                            //move right 15 inches
                            distance = 15;
                            forward = 0;
                            sideways = 0.5;
                            rotation = 0;
                            break;

                             */

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
        if (Math.abs(odometryX) + Math.abs(odometryY) < Distance*351){
            robot.mecanumDrive.updateOdometry();
            robot.mecanumDrive.Drive(forwardPower, sidewaysPower, rotationalPower);
        } else {
            Stage++;
            robot.mecanumDrive.init(hardwareMap);
            Pause = 30;
        }


    }
    private void park(){
        double forward;
        double right;
        double posX;
        double posY;

        forward = 0;
        right = 0;
        robot.mecanumDrive.updateOdometry();
        posX = robot.mecanumDrive.odometryX;
        posY = robot.mecanumDrive.odometryY;
        telemetry.addData("positon x:", posX);
        telemetry.addData("positon y:", posY);
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
