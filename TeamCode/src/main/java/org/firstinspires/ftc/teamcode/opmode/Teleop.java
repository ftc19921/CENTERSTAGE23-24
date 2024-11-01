package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp
public class Teleop extends OpMode {
    Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.mecanumDrive.Drive(gamepad1.right_stick_y, gamepad1.left_stick_x,gamepad1.right_trigger-gamepad1.left_trigger,true);
        if(gamepad2.right_trigger>gamepad2.left_trigger){
            robot.intakeArm.Extend();
        }
        if(gamepad2.left_trigger>gamepad2.right_trigger){
            robot.intakeArm.Retract();
        }
        if(gamepad2.dpad_down){
            robot.intake.in();
        }else if(gamepad2.dpad_up){
            robot.intake.out();
        }else{
            robot.intake.stop();
        }
        if(gamepad2.right_bumper){
            robot.intake.deposit();
        }
        if(gamepad2.left_bumper){
            robot.intake.normalPosition();
        }
        if(gamepad2.y){
            robot.endEffecter.outTake();
        }
        if(gamepad2.x){
            robot.endEffecter.normalPosition();
        }
        if(gamepad2.a){
            robot.outakeArm.hoist();
        }
        if(gamepad2.b){
            robot.outakeArm.release();
        }
    }
}
