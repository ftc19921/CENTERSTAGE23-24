package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Roboto;

@Autonomous
public class AutoMark1 extends OpMode {
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

        if(Stage==0){
            DistanceStrafe =0;
            DistanceForward =-25;
            TurnDistance = 0;
            MotorSpeed=0.6;
            robot.lift.ToTop();

            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage==1){
            robot.endEffecter.outTake();
            Timer(2500000);
        }
        if(Stage==2){
            robot.endEffecter.normalPosition();
            robot.lift.ToBottom();
            TurnDistance = 0;
            DistanceStrafe =-32;
            DistanceForward =0;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);

        }
        if(Stage==3){

            TurnDistance = 900;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage==4){
            robot.intake.in();
            robot.intake.deposit();
            Timer(1700000);
        }
        if(Stage == 5){
            robot.Arm.Extend();
            Timer(2500000);
        }
        if(Stage==6){
            robot.intake.normalPosition();
            robot.Arm.Retract();
            Timer(2000000);
        }
        if(Stage==7){
            robot.intake.out();
            Timer(2100000);
        }
        if(Stage==8){
            robot.lift.ToTop();
            TurnDistance = -150;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=0.4;

            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage==9){
            TurnDistance = 0;
            DistanceStrafe =20;
            DistanceForward =0;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);

        }
        if(Stage==9){
            TurnDistance = 0;
            DistanceStrafe =0;
            DistanceForward =-20;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);

        }
        if(Stage==10){
            robot.endEffecter.outTake();
            Timer(1500000);
        }
        if(Stage==11){
            robot.endEffecter.normalPosition();
            robot.lift.ToBottom();
            TurnDistance = 0;
            DistanceStrafe =0;
            DistanceForward =18;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);

        }
        if(Stage==12){
            TurnDistance = 150;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=0.4;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);

        }
        if(Stage==13){
            robot.intake.in();
            robot.intake.deposit();
            Timer(2500000);
        }
        if(Stage == 14){
            robot.Arm.Extend();
            Timer(2500000);
        }
        if(Stage==15){
            robot.intake.normalPosition();
            robot.Arm.Retract();
            Timer(2500000);
        }
        if(Stage==16){
            robot.intake.out();
            Timer(3000000);
        }
        if(Stage==17){
            robot.lift.ToTop();
            TurnDistance = -200;
            DistanceStrafe =0;
            DistanceForward =0;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);
        }
        if(Stage==18){
            TurnDistance = 0;
            DistanceStrafe =0;
            DistanceForward =-18;
            MotorSpeed=0.6;
            auto(DistanceForward, DistanceStrafe,MotorSpeed,TurnDistance);

        }
        if(Stage==19){
            robot.endEffecter.outTake();
        }

        telemetry.addData("Lift:",robot.lift.wenchMotor2.getCurrentPosition());
        telemetry.update();

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
                forward=_Speed*(_DistanceXAbs/_DistanceX);
            }else{
                forward=0;
            }
            if(odometryY<_DistanceYAbs){
                straif=_Speed*(_DistanceYAbs/_DistanceY);
            }else{
                straif=0;
            }
            if(turnOdometry<_DistanceTurnAbs){
                turn = _Speed*(_DistanceTurn/_DistanceTurnAbs);
            }else{
                turn = 0;
            }
            robot.mecanumDrive.Drive(-forward,-straif,turn);
        }
        if((odometryX >= _DistanceXAbs) && (odometryY >= _DistanceYAbs)&&(turnOdometry >= _DistanceTurnAbs)) {

            robot.mecanumDrive.Drive(0, 0, 0);
            robot.mecanumDrive.resetOdometry();
            Stage++;

        }
    }


}

