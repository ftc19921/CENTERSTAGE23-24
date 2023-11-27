package org.firstinspires.ftc.teamcode.Auto;

import android.location.Location;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.R;
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
    double doubleOdometryX;
    double doubleOdometryY;
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
        doubleOdometryX = robot.mecanumDrive.odometryX;
        doubleOdometryY = robot.mecanumDrive.odometryY;
        telemetry.addData("Location",Location);
        telemetry.addData("Stage",Stage);
        telemetry.addData("odemetryX",doubleOdometryX);
        telemetry.addData("odemetryX",doubleOdometryY);
        if(Location == 1) {
            switch (Stage) {
                case 0:
                    robot.auto.Auto(2000, 0.3, 0, 0, Stage,doubleOdometryX,doubleOdometryY);

                    break;
            }
        }else if(Location == 2){
            switch (Stage) {
                case 0:
                    robot.auto.Auto(1500, 0.3, 0, 0, Stage,doubleOdometryX,doubleOdometryY);
                case 1:
                    robot.auto.Auto(500, 0, 0, 0.3, Stage,doubleOdometryX,doubleOdometryY);

                    break;
            }
        }else{
            switch (Stage) {
                case 0:
                    robot.auto.Auto(1500, 0.3, 0, 0, Stage,doubleOdometryX,doubleOdometryY);
                case 1:
                    robot.auto.Auto(500, 0, 0, -0.3, Stage,doubleOdometryX,doubleOdometryY);

                    break;
            }
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

        Scalar red=new Scalar(255.0,0.0,0.0);


        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input, YCBCr, Imgproc.COLOR_RGBA2RGB);

            Rect MiddleRect = new Rect(145, 107, 10, 10);
            Rect RightRect = new Rect(195, 107, 10, 10);
            Rect LeftRect = new Rect(115, 107, 10, 10);


            input.copyTo(outPut);
            Imgproc.rectangle(outPut, MiddleRect, blue, 2);
            Imgproc.rectangle(outPut, MiddleRect, red, 2);

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
