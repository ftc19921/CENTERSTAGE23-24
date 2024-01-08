package org.firstinspires.ftc.teamcode.TeamCode.Mechinisms;

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
    double RightCol;
    double leftCol;
    double MiddleCol;

    String debugString;
    public int location;



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

    class Pipeline extends OpenCvPipeline {

        Mat YCBCr = new Mat();

        Mat MiddleRedValue;
        Mat MiddleBlueValue;
        Mat RightRedValue;
        Mat RightBlueValue;

        Mat MiddlefinalBlue = new Mat();
        Mat MiddlefinalRed = new Mat();

        Mat RightfinalBlue = new Mat();
        Mat RightfinalRed = new Mat();

        double MiddleBlueAvgfin;
        double MiddleRedAvgfin;
        double RightBlueAvgfin;
        double RightRedAvgfin;
        double LeftBlueAvgfin;
        double LeftRedAvgfin;

        Mat outPut = new Mat();


        Scalar MiddleBlue = new Scalar(0.0, 0.0, 255.0);
        Scalar MiddleRed = new Scalar(255.0, 0.0, 0.0);

        Scalar RightBlue = new Scalar(0.0, 0.0, 255.0);
        Scalar RightRed = new Scalar(255.0, 0.0, 0.0);


        @Override
        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input, YCBCr, Imgproc.COLOR_RGB2YCrCb);
            Size size = outPut.size();
            double width = size.width;
            double height = size.height;



            Rect MiddleRectBlue = new Rect(40,75, (int) width/10, (int) height/8);
            Rect MiddleRectRed = new Rect(40, 75, (int) width/10, (int) height/8);

            Rect RightRectBlue = new Rect(220, 120, (int) width/10, (int) height/8);
            Rect RightRectRed = new Rect(220, 120, (int) width/10, (int) height/8);



            input.copyTo(outPut);
            Imgproc.rectangle(outPut, MiddleRectBlue, MiddleBlue, 2);
            Imgproc.rectangle(outPut, MiddleRectRed, MiddleRed, 2);

            Imgproc.rectangle(outPut, RightRectBlue, RightBlue, 2);
            Imgproc.rectangle(outPut, RightRectRed, RightRed, 2);




            MiddleBlueValue = YCBCr.submat(MiddleRectBlue);
            MiddleRedValue = YCBCr.submat(MiddleRectRed);

            RightBlueValue = YCBCr.submat(RightRectBlue);
            RightRedValue = YCBCr.submat(RightRectRed);



            Core.extractChannel(MiddleBlueValue, MiddlefinalBlue, 2);
            Core.extractChannel(MiddleRedValue, MiddlefinalRed, 1);
            Core.extractChannel(RightBlueValue, RightfinalBlue, 2);
            Core.extractChannel(RightRedValue, RightfinalRed, 1);


            Scalar MiddleBlueAvg = Core.mean(MiddlefinalBlue);
            Scalar MiddleRedAvg = Core.mean(MiddlefinalRed);
            Scalar RightBlueAvg = Core.mean(RightfinalBlue);
            Scalar RightRedAvg = Core.mean(RightfinalRed);


            RightBlueAvgfin = RightBlueAvg.val[0];
            RightRedAvgfin = RightRedAvg.val[0];

            MiddleBlueAvgfin = MiddleBlueAvg.val[0];
            MiddleRedAvgfin = MiddleRedAvg.val[0];

            RightCol = RightBlueAvgfin+ RightRedAvgfin;
            MiddleCol = MiddleBlueAvgfin+ MiddleRedAvgfin;

            if ( RightCol > MiddleCol +8) {
                debugString = "Team prop is on the Right";
                location = 1;
            } else if ( MiddleCol > RightCol+8) {
                debugString = "Team prop is on the Middle";
                location = 0;
            } else {
                debugString = "Team prop is on the Left";
                location = -1;
            }
            
            debugString += "Right:  " + (int) RightCol;
            debugString += "Middle:  " + (int) MiddleCol;



            return (outPut);
        }
    }
}
