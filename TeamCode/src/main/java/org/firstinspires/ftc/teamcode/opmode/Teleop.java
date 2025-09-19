package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Roboto;

@TeleOp
public class Teleop extends OpMode {
    Roboto robot = new Roboto();
    ColorSensor IntakeColorSensor;
    boolean Hoist;
    @Override
    public void init() {
        robot.init(hardwareMap);
        IntakeColorSensor=hardwareMap.get(ColorSensor.class,"ColorSensor");

    }

    @Override
    public void loop() {
        //telemetry.addData("colorSensorR", IntakeColorSensor.red());
        //telemetry.addData("colorSensorB", IntakeColorSensor.blue());
        //telemetry.addData("colorSensorG", IntakeColorSensor.green());
        telemetry.addData("DistanceY", robot.mecanumDrive.odometryY);
        telemetry.addData("DistanceX",robot.mecanumDrive.odometryX);
        telemetry.addData("Distance turned", robot.mecanumDrive.turnOdometry);
        telemetry.addData("Lift",robot.lift.wenchMotor.getCurrentPosition());

        //red: r750 b170 g400
        //blue: r141 b850 g300
        //yellow r1300 b350 g1800




        if(robot.lift.TS.isPressed()) {
            telemetry.addData("lseiyfgl","ksadcgy");
            robot.lift.reset();
        }
        robot.mecanumDrive.Drive(gamepad1.left_stick_y, -gamepad1.right_stick_x,(-gamepad1.right_trigger+gamepad1.left_trigger)/2);
        if(gamepad2.right_trigger>gamepad2.left_trigger){
            robot.Arm.Extend();
        }else if(gamepad2.left_trigger>gamepad2.right_trigger){
            robot.Arm.Retract();

        }

        if(gamepad2.dpad_left){
            robot.endEffecter.outTake();
        }
        if(gamepad2.dpad_right){
            robot.endEffecter.normalPosition();
        }
        if(gamepad2.a){
            robot.intake.in();
        }else if(gamepad2.b){
            robot.intake.out();
        }else{
            robot.intake.stop();
        }

        if(gamepad2.dpad_up) {
            robot.lift.release();

        }else if(gamepad2.dpad_down){
            robot.lift.hoist();

        }else {
            robot.lift.Stop();
        }


        if(gamepad2.left_bumper){
            robot.intake.normalPosition();
        }
        if(gamepad2.right_bumper){
            robot.intake.deposit();
        }
        if(gamepad2.y){
            robot.intake.vomit();
        }

        telemetry.update();
    }
}
