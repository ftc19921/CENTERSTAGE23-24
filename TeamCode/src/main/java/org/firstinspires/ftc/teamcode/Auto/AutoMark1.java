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
    OpenCvWebcam webcam;
    int Location;
    double distance;
    double Forward;
    double Sideways;
    double Rotation;



    @Override
    public void init() {
        robot.init(hardwareMap);



        Location =0;
        int Stage = 0;
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam1"), cameraMonitorViewId);

        webcam.setPipeline(new Pipeline());
        webcam.setMillisecondsPermissionTimeout(2500);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener(){
                public void onOpened()
                {

                    webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                }

                public void onError(int errorCode)
                {

                }
            });
        }




    public void loop() {
        telemetry.addData("Location",Location);
        telemetry.addData("Stage",Stage);


        if(Location == 1) {
            switch (Stage) {
                case 0:
                    distance=10185;
                    Forward=0.5;
                    Sideways=0;
                    Rotation=0;
                    break;
                case 1:
                    distance=10185;
                    Forward=0;
                    Sideways=0;
                    Rotation=0;
                    break;
            }
        }else if(Location == 2){
            switch (Stage) {
                case 0:
                    distance=8912;
                    Forward=0.5;
                    Sideways=0;
                    Rotation=0;
                    break;
                case 1:
                    distance=3500;
                    Forward=0;
                    Sideways=0;
                    Rotation=-1;
                    break;
                case 2:
                    distance=10000;
                    Forward=0;
                    Sideways=0;
                    Rotation=0;

                    break;
            }
        }else{
            switch (Stage) {
                case 0:
                    distance=8912;
                    Forward=0.5;
                    Sideways=0;
                    Rotation=0;
                    break;
                case 1:
                    distance=3500;
                    Forward=0;
                    Sideways=0;
                    Rotation=1;

                    break;
                case 2:
                    distance=10000;
                    Forward=0;
                    Sideways=0;
                    Rotation=0;

                    break;
            }
        }
        double odometryX = robot.mecanumDrive.odometryX;
        double odometryY = robot.mecanumDrive.odometryY;

        auto(distance,Forward,Sideways,Rotation);
        telemetry.addData("Distance",Math.abs(odometryX)+Math.abs(odometryY));
    }




    public void auto(double Distance, double forwardPower, double sidewaysPower, double rotationalPower){
        robot.mecanumDrive.updateOdometry();
        double odometryX = robot.mecanumDrive.odometryX;
        double odometryY = robot.mecanumDrive.odometryY;
        if(Math.abs(odometryX)+Math.abs(odometryY)<Distance){
            robot.mecanumDrive.updateOdometry();
            robot.mecanumDrive.Drive(forwardPower, sidewaysPower,rotationalPower,false);
        }else{
            Stage++;
            robot.init(hardwareMap);
        }


    }





    class Pipeline extends OpenCvPipeline {


        Mat YCBCr= new Mat();

        Mat finalBlue= new Mat();
        Mat finalRed= new Mat();
        Mat finalBlueRight= new Mat();
        Mat finalRedRight= new Mat();
        Mat finalBlueLeft= new Mat();
        Mat finalRedLeft= new Mat();

        double LeftRedAvgfin;
        double LeftBlueAvgfin;
        double RightRedAvgfin;
        double RightBlueAvgfin;
        double BlueAvgfin;
        double RedAvgfin;
        double RightValue;
        double LeftValue;
        double MiddleValue;
        Mat outPut = new Mat();
        Scalar blue=new Scalar(0.0,0.0,255.0);
        Scalar purple=new Scalar(100.0,0.0,155.0);

        Scalar red=new Scalar(255.0,0.0,0.0);


        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input, YCBCr, Imgproc.COLOR_RGBA2RGB);

            Rect MiddleRect = new Rect(105, 107, 10, 10);
            Rect RightRect = new Rect(80, 157, 10, 10);
            Rect LeftRect = new Rect(80, 57, 10, 10);

            input.copyTo(outPut);

            Imgproc.rectangle(outPut, MiddleRect, blue, 1);

            Imgproc.rectangle(outPut, RightRect, red, 1);

            Imgproc.rectangle(outPut, LeftRect, purple, 1);

            Mat BlueValue = YCBCr.submat(MiddleRect);
            Mat RedValue = YCBCr.submat(MiddleRect);
            Mat RightBlueValue = YCBCr.submat(RightRect);
            Mat RightRedValue = YCBCr.submat(RightRect);
            Mat LeftBlueValue = YCBCr.submat(LeftRect);
            Mat LeftRedValue = YCBCr.submat(MiddleRect);

            Core.extractChannel(BlueValue, finalBlue, 0);
            Core.extractChannel(RedValue, finalRed, 2);
            Core.extractChannel(RightBlueValue, finalBlueRight, 0);
            Core.extractChannel(RightRedValue, finalRedRight, 2);
            Core.extractChannel(LeftBlueValue, finalBlueLeft, 0);
            Core.extractChannel(LeftRedValue, finalRedLeft, 2);

            Scalar BlueAvg = Core.mean(finalBlue);
            Scalar RedAvg = Core.mean(finalRed);
            Scalar BlueAvgRight = Core.mean(finalBlueRight);
            Scalar RedAvgRight = Core.mean(finalRedRight);
            Scalar BlueAvgLeft = Core.mean(finalBlueLeft);
            Scalar RedAvgLeft = Core.mean(finalRedLeft);


            BlueAvgfin = BlueAvg.val[0];
            RedAvgfin = RedAvg.val[2];
            RightBlueAvgfin = BlueAvgRight.val[0];
            RightRedAvgfin = RedAvgRight.val[2];
            LeftBlueAvgfin = BlueAvgLeft.val[0];
            LeftRedAvgfin = RedAvgLeft.val[2];

            MiddleValue = BlueAvgfin+RedAvgfin;
            RightValue = RightBlueAvgfin+RightRedAvgfin;
            LeftValue = LeftBlueAvgfin+LeftRedAvgfin;

            if(MiddleValue>RightValue&&MiddleValue>LeftValue){
                Location = 1;
            }else if (LeftValue>RightValue&&LeftValue>MiddleValue){
                Location = 2;
            }else{
                Location = 3;
            }


            return (outPut);
        }
    }
}
