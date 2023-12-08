package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Robot;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous
public class AutoMark1 extends OpMode {
    Robot robot = new Robot();
    int Stage;
    int framesRan;
    int Location;
    double distance;
    double Forward;
    double Sideways;
    double Rotation;
    int CameraOn;
    int Pause;
    public double RightValue;

    public double MiddleValue;

    public double RightRedAvgfin;
    public double RightBlueAvgfin;
    public double BlueAvgfin;
    public double RedAvgfin;

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
        if (Math.abs(odometryX) + Math.abs(odometryY) < Distance) {
            robot.mecanumDrive.updateOdometry();
            robot.mecanumDrive.Drive(forwardPower, sidewaysPower, rotationalPower, false);
        } else {
            Stage++;

            Pause = 20;
        }


    }


}
