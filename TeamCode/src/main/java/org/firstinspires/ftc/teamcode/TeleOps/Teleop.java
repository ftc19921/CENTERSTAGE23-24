package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp
public class Teleop extends OpMode {
    Robot robot = new Robot();
    double turn;
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.right_trigger>gamepad1.left_trigger){
            turn=gamepad1.right_trigger;
        }else if(gamepad1.right_trigger<gamepad1.left_trigger){
            turn=-gamepad1.left_trigger;
        }else{
            turn=0;
        }
        robot.mecanumDrive.Drive(-gamepad1.left_stick_y, gamepad1.right_stick_x, turn);
        if(gamepad2.dpad_up){
            robot.arm.Hang();
        }else if(gamepad2.dpad_down){
            robot.arm.DeHang();
        }else{
            robot.arm.Stop();
        }
        if(gamepad2.left_bumper){
            robot.drone.Launch();
        }else{
            robot.drone.Stop();
        }
    }
}
