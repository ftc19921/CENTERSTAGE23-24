package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Roboto;

@Autonomous
public class Auto2Samples extends OpMode {
    Roboto robot = new Roboto();
    public int Stage;
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
        if(robot.lift.stageIncrease1||robot.lift.stageIncrease2){
            Stage++;
        }
        telemetry.addData("Stage: ",Stage);
        if(Stage==0){
            DistanceStrafe =0;
            DistanceForward =-27;
            TurnDistance = 0;
            MotorSpeed=0.4;
            robot.lift.atTop=false;
            robot.endEffecter.normalPosition();
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage==2){
            robot.endEffecter.outTake();
            Timer(1800000);
        }
        if(Stage==3){
            robot.endEffecter.normalPosition();
            robot.lift.atBottom=false;
            TurnDistance = 0;
            DistanceStrafe =-25;
            DistanceForward =0;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);

        }
        if(Stage==4){

            TurnDistance = 90;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage==6){

            TurnDistance = 0;
            DistanceStrafe =-6;
            DistanceForward =0;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }

        if(Stage==7){
            robot.intake.in();
            robot.intake.deposit();
            Timer(1900000);
        }
        if(Stage == 8){
            robot.Arm.Extend();
            Timer(2500000);
        }
        if(Stage==9){
            robot.intake.normalPosition();
            robot.Arm.Retract();
            Timer(2000000);
        }
        if(Stage==10){
            robot.intake.out();
            Timer(1600000);
        }
        if(Stage==11){
            TurnDistance = -5;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage==12){
            robot.lift.atTop=false;
            TurnDistance = 0;
            DistanceStrafe =0;
            DistanceForward =-12;
            MotorSpeed=0.4;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }

        if(Stage==14){
            robot.endEffecter.outTake();
            Timer(1500000);
        }
        if(Stage==15){
            robot.endEffecter.normalPosition();
            robot.lift.atBottom=false;
            TurnDistance = 0;
            DistanceStrafe =4;
            DistanceForward =15;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage==17){

            TurnDistance = -20;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);

        }if(Stage==18){
            robot.intake.in();
            robot.intake.deposit();
            Timer(1300000);
        }
        if(Stage == 19){
            robot.Arm.Extend();
            Timer(2400000);
        }
        if(Stage==20){
            robot.intake.normalPosition();
            robot.Arm.Retract();
            Timer(2000000);
        }
        if(Stage==21){
            robot.intake.out();
            Timer(1700000);
        }
        if(Stage==22){
            TurnDistance = 0;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage==23){
            robot.lift.atTop=false;
            TurnDistance = 0;
            DistanceStrafe =-5;
            DistanceForward =-20;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);

        }

        if(Stage==25){
            robot.endEffecter.outTake();
            Timer(1500000);
        }


        telemetry.addData("Lift:",robot.lift.wenchMotor2.getCurrentPosition());
        telemetry.update();
        robot.lift.ToTop();
        robot.lift.ToBottom();
    }

    public void Timer(double time){
        int i = 0;
        while(true){
            telemetry.addData("time",i);
            if(i<time){
                i++;
            }else{
                Stage++;
                break;

            }
        }
    }
    public void auto(double _DistanceX, double _DistanceY, double _Speed,double _DistanceTurn) {
        telemetry.addData("TurnOdometry ",robot.mecanumDrive.turnOdometry);
        telemetry.update();
        double odometryX = Math.abs(robot.mecanumDrive.odometryX);
        double odometryY = Math.abs(robot.mecanumDrive.odometryY);
        double turnOdometry = Math.abs(robot.mecanumDrive.turnOdometry);
        double _DistanceXAbs = Math.abs(_DistanceX);
        double _DistanceYAbs = Math.abs(_DistanceY);
        double _DistanceTurnAbs = Math.abs(_DistanceTurn);
        double forward=0;
        double straif=0;
        double turn = 0;

        if(odometryX<_DistanceXAbs||odometryY<_DistanceYAbs||turnOdometry<_DistanceTurnAbs){

            odometryX = Math.abs(robot.mecanumDrive.odometryX);
            odometryY = Math.abs(robot.mecanumDrive.odometryY);
            turnOdometry = Math.abs(robot.mecanumDrive.turnOdometry);
            telemetry.addData("DistanceX", odometryX);
            telemetry.addData("DistanceY",odometryY);
            telemetry.update();
            if(odometryX<_DistanceXAbs){
                forward = _Speed*(_DistanceX/_DistanceXAbs);
            }else{
                forward=0;
            }
            if(odometryY<_DistanceYAbs){
                straif= _Speed*(_DistanceY/_DistanceYAbs);
            }else{
                straif=0;
            }
            if(turnOdometry<_DistanceTurnAbs){
                turn = _Speed*(_DistanceTurn/_DistanceTurnAbs);
            }else{
                turn = 0;
            }
            robot.mecanumDrive.Drive(-forward,-straif,turn);
            telemetry.addLine("Forward" + -forward);
            telemetry.addLine("Straif" + -straif);
            telemetry.addLine("Turn" + turn);

        }else{

            robot.mecanumDrive.Drive(0, 0, 0);
            robot.mecanumDrive.resetOdometry();
            Stage++;

        }
    }


}

