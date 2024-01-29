package org.firstinspires.ftc.teamcode.Mechinisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class Camera{
    OpenCvWebcam webcam;

    double Phase;
    public double RightCol;
    public double LeftCol;
    public double MiddleCol;
    public double LeftMiddleCol;
    public int overallColor;
    String debugString;
    public int location;
    public int blueLocation;



    public void init(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam1"), cameraMonitorViewId);

        webcam.setPipeline(new Pipeline());
        webcam.setMillisecondsPermissionTimeout(2500);
        Phase = 0;
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {

                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }

    public String getDebugString() {
        return debugString;
    }

    public int getLocation() {
        return location;
    }
    public int getBlueLocation() {
        return blueLocation;
    }

    class Pipeline extends OpenCvPipeline {

        Mat YCBCr = new Mat();

        Mat MiddleRedValue;
        Mat MiddleBlueValue;
        Mat RightRedValue;
        Mat RightBlueValue;

        Mat LeftMiddleRedValue;
        Mat LeftMiddleBlueValue;
        Mat LeftRedValue;
        Mat LeftBlueValue;

        Mat MiddlefinalBlue = new Mat();
        Mat MiddlefinalRed = new Mat();

        Mat RightfinalBlue = new Mat();
        Mat RightfinalRed = new Mat();

        Mat LeftMiddlefinalBlue = new Mat();
        Mat LeftMiddlefinalRed = new Mat();

        Mat LeftfinalBlue = new Mat();
        Mat LeftfinalRed = new Mat();

        double MiddleBlueAvgfin;
        double MiddleRedAvgfin;
        double RightBlueAvgfin;
        double RightRedAvgfin;
        double LeftMiddleBlueAvgfin;
        double LeftMiddleRedAvgfin;
        double LeftBlueAvgfin;
        double LeftRedAvgfin;

        Mat outPut = new Mat();



        Scalar Blue = new Scalar(0, 255, 0.0);
        Scalar Green = new Scalar(0.0, 0.0, 255.0);
        Scalar Red = new Scalar(255.0, 0.0, 0.0);
        Scalar Yellow = new Scalar(125,0,0);


        @Override
        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input, YCBCr, Imgproc.COLOR_RGB2YCrCb);
            Size size = outPut.size();
            double width = size.width;
            double height = size.height;



            Rect MiddleRectBlue = new Rect(40,115, (int) width/10, (int) height/8);
            Rect MiddleRectRed = new Rect(40, 115, (int) width/10, (int) height/8);

            Rect RightRectBlue = new Rect(190, 120, (int) width/10, (int) height/8);
            Rect RightRectRed = new Rect(190, 120, (int) width/10, (int) height/8);

            Rect LeftRectBlue = new Rect(10,140, (int) width/10, (int) height/8);
            Rect LeftRectRed = new Rect(10, 140, (int) width/10, (int) height/8);

            Rect LeftMiddleRectBlue = new Rect(150, 120, (int) width/10, (int) height/8);
            Rect LeftMiddleRectRed = new Rect(150, 120, (int) width/10, (int) height/8);




            input.copyTo(outPut);
            Imgproc.rectangle(outPut, MiddleRectBlue, Blue, 2);
            Imgproc.rectangle(outPut, MiddleRectRed, Blue, 2);

            Imgproc.rectangle(outPut, RightRectBlue, Red, 2);
            Imgproc.rectangle(outPut, RightRectRed, Red, 2);

            Imgproc.rectangle(outPut, LeftMiddleRectBlue, Yellow, 2);
            Imgproc.rectangle(outPut, LeftMiddleRectRed, Yellow, 2);

            Imgproc.rectangle(outPut, LeftRectBlue, Green, 2);
            Imgproc.rectangle(outPut, LeftRectRed, Green, 2);




            MiddleBlueValue = YCBCr.submat(MiddleRectBlue);
            MiddleRedValue = YCBCr.submat(MiddleRectRed);

            RightBlueValue = YCBCr.submat(RightRectBlue);
            RightRedValue = YCBCr.submat(RightRectRed);

            LeftMiddleBlueValue = YCBCr.submat(LeftMiddleRectBlue);
            LeftMiddleRedValue = YCBCr.submat(LeftMiddleRectRed);

            LeftBlueValue = YCBCr.submat(LeftRectBlue);
            LeftRedValue = YCBCr.submat(LeftRectRed);



            Core.extractChannel(MiddleBlueValue, MiddlefinalBlue, 2);
            Core.extractChannel(MiddleRedValue, MiddlefinalRed, 1);
            Core.extractChannel(RightBlueValue, RightfinalBlue, 2);
            Core.extractChannel(RightRedValue, RightfinalRed, 1);

            Core.extractChannel(LeftMiddleBlueValue, LeftMiddlefinalBlue, 2);
            Core.extractChannel(LeftMiddleRedValue, LeftMiddlefinalRed, 1);
            Core.extractChannel(LeftBlueValue, LeftfinalBlue, 2);
            Core.extractChannel(LeftRedValue, LeftfinalRed, 1);


            Scalar MiddleBlueAvg = Core.mean(MiddlefinalBlue);
            Scalar MiddleRedAvg = Core.mean(MiddlefinalRed);
            Scalar RightBlueAvg = Core.mean(RightfinalBlue);
            Scalar RightRedAvg = Core.mean(RightfinalRed);

            Scalar LeftMiddleBlueAvg = Core.mean(LeftMiddlefinalBlue);
            Scalar LeftMiddleRedAvg = Core.mean(LeftMiddlefinalRed);
            Scalar LeftBlueAvg = Core.mean(LeftfinalBlue);
            Scalar LeftRedAvg = Core.mean(LeftfinalRed);


            RightBlueAvgfin = RightBlueAvg.val[0];
            RightRedAvgfin = RightRedAvg.val[0];

            MiddleBlueAvgfin = MiddleBlueAvg.val[0];
            MiddleRedAvgfin = MiddleRedAvg.val[0];

            LeftBlueAvgfin = LeftBlueAvg.val[0];
            LeftRedAvgfin = LeftRedAvg.val[0];

            LeftMiddleBlueAvgfin = LeftMiddleBlueAvg.val[0];
            LeftMiddleRedAvgfin = LeftMiddleRedAvg.val[0];

            RightCol = RightBlueAvgfin+ RightRedAvgfin;
            MiddleCol = MiddleBlueAvgfin+ MiddleRedAvgfin;

            LeftCol = LeftBlueAvgfin+ LeftRedAvgfin;
            LeftMiddleCol = LeftMiddleBlueAvgfin+ LeftMiddleRedAvgfin;

            if ( RightCol > MiddleCol +6) {

                location = 1;
            } else if ( MiddleCol > RightCol+6) {

                location = 0;
            } else {

                location = -1;
            }


            if ( LeftCol > LeftMiddleCol +6) {

                blueLocation = 1;
            } else if ( LeftMiddleCol > LeftCol + 6) {

                blueLocation = 0;
            } else {

                blueLocation = -1;
            }
            if(RightBlueAvgfin+ MiddleBlueAvgfin+LeftBlueAvgfin+LeftMiddleBlueAvgfin>RightRedAvgfin+MiddleRedAvgfin+LeftRedAvgfin+LeftMiddleRedAvgfin){
                overallColor = 1;
            }else{
                overallColor = 0;
            }




            return (outPut);
        }
    }
}
