package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.teamcode.Roboto;

@Autonomous
public class AutoMark1 extends OpMode {
    Roboto robot = new Roboto();
    int Stage;
    double DistanceForward;
    double DistanceStrafe;
    double MotorSpeed;

    double TurnDistance;
   // ElapsedTime Timer = new ElapsedTime();




    @Override
    public void init() {
        robot.init(hardwareMap);
        //Timer.reset();
        int Stage = 0;
        //Timer.startTime();
    }


    public void loop() {

        if(Stage==0){
            DistanceStrafe =0;
            DistanceForward =-27;
            MotorSpeed=0.4;
            robot.lift.ToTop();
            TurnDistance = 0;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage==1){
            robot.endEffecter.outTake();
            //Timer.wait(10,10);
        }
        /*if(Stage==2){
            TurnDistance = 0;
            DistanceStrafe =10;
            DistanceForward =30;
            MotorSpeed=0.4;
            robot.lift.ToBottom();
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage==3){
            TurnDistance = 180;
            MotorSpeed=0.4;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }

        telemetry.addData("Lift:",robot.lift.wenchMotor2.getCurrentPosition());
        telemetry.update();
*/
    }


    public void auto(double _DistanceX, double _DistanceY, double _Speed,double _DistanceTurn) {
        double odometryX = Math.abs(robot.mecanumDrive.odometryX);
        double odometryY = Math.abs(robot.mecanumDrive.odometryY);
        double turnOdometry = Math.abs(robot.mecanumDrive.turnOdometry);
        double _DistanceXAbs = Math.abs(_DistanceX);
        double _DistanceYAbs = Math.abs(_DistanceY);
        double _DistanceTurnAbs = Math.abs(_DistanceTurn);
        double forward=0;
        double straif=0;
        double turn = 0;

        if(odometryX<_DistanceXAbs||odometryY<_DistanceYAbs||turnOdometry<_DistanceTurn){
            odometryX = Math.abs(robot.mecanumDrive.odometryX);
            odometryY = Math.abs(robot.mecanumDrive.odometryY);
            telemetry.addData("DistanceX", odometryX);
            telemetry.addData("DistanceY",odometryY);
            telemetry.update();
            if(odometryX<_DistanceXAbs){
                forward=_Speed*(_DistanceXAbs/_DistanceX);
            }else{
                forward=0;
            }
            if(odometryY<_DistanceYAbs){
                straif=_Speed*(_DistanceYAbs/_DistanceY);
            }else{
                straif=0;
            }
            if(odometryY<_DistanceTurnAbs){
                turn = _Speed*(_DistanceTurn/_DistanceTurnAbs);
            }
            robot.mecanumDrive.Drive(-forward,straif,turn);
        }
        if((odometryX >= _DistanceXAbs) && (odometryY >= _DistanceYAbs)) {
            robot.mecanumDrive.Drive(0, 0, 0);
            Stage++;
        }
    }


}

