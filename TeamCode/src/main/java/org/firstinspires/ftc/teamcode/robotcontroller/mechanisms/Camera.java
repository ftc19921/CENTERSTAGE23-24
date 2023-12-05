package org.firstinspires.ftc.teamcode.robotcontroller.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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

@Autonomous
public class Camera implements Mechanism {
    OpenCvWebcam webcam;

    double Phase;
    double rightCol;
//    double leftCol;
    double midCol;

    String debugString;
    int location;

    int tolerance = 180;

    public void init(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam1"), cameraMonitorViewId);

        webcam.setPipeline(new Pipeline());
        webcam.setMillisecondsPermissionTimeout(2500);
        Phase = 0;
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
//                telemetry.addLine("cam online");
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
        Mat LeftRedValue;
        Mat LeftBlueValue;
        Mat MiddleFinalBlue = new Mat();
        Mat MiddleFinalRed = new Mat();
//        Mat LeftfinalBlue = new Mat();
//        Mat LeftfinalRed = new Mat();
        Mat RightFinalBlue = new Mat();
        Mat RightFinalRed = new Mat();

        double MiddleBlueAvgFin;
        double MiddleRedAvgFin;
        double RightBlueAvgFin;
        double RightRedAvgFin;
//        double LeftBlueAvgfin;
//        double LeftRedAvgfin;

        Mat outPut = new Mat();


        Scalar MiddleBlue = new Scalar(0.0, 0.0, 255.0);
        Scalar MiddleRed = new Scalar(255.0, 0.0, 0.0);

        Scalar RightBlue = new Scalar(0.0, 0.0, 255.0);
        Scalar RightRed = new Scalar(255.0, 0.0, 0.0);

//        Scalar Leftblue = new Scalar(0.0, 0.0, 255.0);
//        Scalar Leftred = new Scalar(255.0, 0.0, 0.0);

        @Override
        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input, YCBCr, Imgproc.COLOR_RGBA2RGB);
            Size size = outPut.size();
            double width = size.width;
            double height = size.height;

//            Rect LeftRectBlue = new Rect(0, (int) height/2, (int) width/3, (int) height/4);
//            Rect LeftRectRed = new Rect(0, (int) height/2, (int) width/3, (int) height/4);

            Rect MiddleRectBlue = new Rect((int) width/10*3, (int) height/10*4, (int) width/5, (int) height/4);
            Rect MiddleRectRed = new Rect((int) width/10*3, (int) height/10*4, (int) width/5, (int) height/4);

            Rect RightRectBlue = new Rect((int) width/10*9, (int) height/2, (int) width/10, (int) height/8);
            Rect RightRectRed = new Rect((int) width/10*9, (int) height/2, (int) width/10, (int) height/8);


            input.copyTo(outPut);
            Imgproc.rectangle(outPut, MiddleRectBlue, MiddleBlue, 2);
            Imgproc.rectangle(outPut, MiddleRectRed, MiddleRed, 2);

            Imgproc.rectangle(outPut, RightRectBlue, RightBlue, 2);
            Imgproc.rectangle(outPut, RightRectRed, RightRed, 2);

//            Imgproc.rectangle(outPut, LeftRectBlue, Leftblue, 2);
//            Imgproc.rectangle(outPut, LeftRectRed, Leftred, 2);


            MiddleBlueValue = YCBCr.submat(MiddleRectBlue);
            MiddleRedValue = YCBCr.submat(MiddleRectRed);

            RightBlueValue = YCBCr.submat(RightRectBlue);
            RightRedValue = YCBCr.submat(RightRectRed);

//            LeftBlueValue = YCBCr.submat(LeftRectBlue);
//            LeftRedValue = YCBCr.submat(LeftRectRed);

            Core.extractChannel(MiddleBlueValue, MiddleFinalBlue, 2);
            Core.extractChannel(MiddleRedValue, MiddleFinalRed, 0);
            Core.extractChannel(RightBlueValue, RightFinalBlue, 2);
            Core.extractChannel(RightRedValue, RightFinalRed, 0);
//            Core.extractChannel(LeftBlueValue, LeftfinalBlue, 2);
//            Core.extractChannel(LeftRedValue, LeftfinalRed, 0);

            Scalar MiddleBlueAvg = Core.mean(MiddleFinalBlue);
            Scalar MiddleRedAvg = Core.mean(MiddleFinalRed);
            Scalar RightBlueAvg = Core.mean(RightFinalBlue);
            Scalar RightRedAvg = Core.mean(RightFinalRed);
//            Scalar LeftBlueAvg = Core.mean(LeftfinalBlue);
//            Scalar LeftRedAvg = Core.mean(LeftfinalRed);

            RightBlueAvgFin = RightBlueAvg.val[0];
            RightRedAvgFin = RightRedAvg.val[0];
//            LeftBlueAvgfin = LeftBlueAvg.val[0];
//            LeftRedAvgfin = LeftRedAvg.val[0];
            MiddleBlueAvgFin = MiddleBlueAvg.val[0];
            MiddleRedAvgFin = MiddleRedAvg.val[0];
//            telemetry.addData("RB", RightBlueAvgfin);
//            telemetry.addData("RR", RightRedAvgfin);
//            telemetry.addData("MB", BlueAvgfin);
//            telemetry.addData("MR", RedAvgfin);
//            telemetry.addData("LB", LeftBlueAvgfin);
//            telemetry.addData("LR", LeftRedAvgfin);
            rightCol = Math.max(RightBlueAvgFin, RightRedAvgFin);
            midCol = Math.max(MiddleBlueAvgFin, MiddleRedAvgFin);
//            leftCol = Math.max(LeftBlueAvgfin, LeftRedAvgfin);
            if (rightCol >= tolerance && rightCol >= midCol) {
                debugString = "Team prop is on the Right";
                location = 1;
//                telemetry.addLine("Team prop is on the Left");
            } else if (midCol > tolerance && midCol >= rightCol) {
                debugString = "Team prop is on the Middle";
                location = 0;
//                telemetry.addLine("Team prop is on the Right");
            } else {
                debugString = "Team prop is on the Left";
                location = -1;
//                telemetry.addLine("Team prop is on the Middle");
            }
            
            debugString += "RB" + (int) RightBlueAvgFin;
            debugString += "RR" + (int) RightRedAvgFin;
            debugString += "MB" + (int) MiddleBlueAvgFin;
            debugString += "MR" + (int) MiddleRedAvgFin;
//            location += "LB" + (int) LeftBlueAvgfin;
//            location += "LR" + (int) LeftRedAvgfin;

            return (outPut);
        }
    }
}
