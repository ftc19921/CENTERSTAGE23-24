package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Roboto;

@Autonomous
public class Auto2SamplesUnderWork extends OpMode {
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

        telemetry.addLine(""+Stage);

        if(Stage==0){
            DistanceStrafe =0;
            DistanceForward =-35;
            TurnDistance = 0;
            MotorSpeed=0.6;
            robot.lift.atTop=false;

            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }

        if(Stage==2){
            robot.endEffecter.outTake();
            Timer(2600000);
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
        if(Stage==5){

            TurnDistance = 85;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=1;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage==6){

            TurnDistance = 0;
            DistanceStrafe =-7;
            DistanceForward =0;
            MotorSpeed=1;
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
        if(Stage == 9){
            TurnDistance = 5;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=0.4;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage == 10){
            TurnDistance = -10;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=0.4;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage == 11){
            TurnDistance = 5;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=0.4;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }

        if(Stage==12){
            robot.intake.normalPosition();
            robot.Arm.Retract();
            Timer(2000000);
        }
        if(Stage==13){
            robot.intake.out();
            Timer(2100000);
        }
        if(Stage==14){

            TurnDistance = -10;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=1;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);

        }
        if(Stage==15){
            robot.lift.atTop=false;
            TurnDistance = 0;
            DistanceStrafe =0;
            DistanceForward =-20;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);

        }

        if(Stage==17){
            robot.endEffecter.outTake();
            Timer(1500000);
        }


        telemetry.addData("Lift:",robot.lift.wenchMotor.getCurrentPosition());
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

                    forward = (_DistanceX / _DistanceXAbs) * _Speed  * (1 - ((odometryX) / (_DistanceXAbs))) +(0.2*(_DistanceX / _DistanceXAbs));

            }else{
                forward=0;
            }
            if(odometryY<_DistanceYAbs){

                    straif = _Speed * (_DistanceY / _DistanceYAbs) * (1 - ((odometryY)/ (_DistanceYAbs)))+(0.2*(_DistanceX / _DistanceXAbs));

            }else{
                straif=0;
            }
            if(turnOdometry<_DistanceTurnAbs){

                    turn = (_Speed * (_DistanceTurn / _DistanceTurnAbs) * (1 - ((turnOdometry) / (_DistanceTurnAbs)))+(0.2*(_DistanceX / _DistanceXAbs)));

            }else{
                turn = 0;
            }
            robot.mecanumDrive.Drive(-forward,-straif,turn);
            telemetry.addLine("Forward" + -forward);
            telemetry.addLine("Straif" + -straif);
            telemetry.addLine("Turn" + turn);

        }
        if((odometryX >= _DistanceXAbs) && (odometryY >= _DistanceYAbs) && (turnOdometry >= _DistanceTurnAbs)) {

            robot.mecanumDrive.Drive(0, 0, 0);
            robot.mecanumDrive.resetOdometry();
            Stage++;

        }
    }


}

