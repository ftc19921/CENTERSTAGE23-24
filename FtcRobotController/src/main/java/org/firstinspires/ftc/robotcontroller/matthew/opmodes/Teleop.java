package org.firstinspires.ftc.robotcontroller.matthew.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.matthew.Robot;

@TeleOp
public class Teleop extends OpMode {
    Robot robot = new Robot();
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.tankDrive.drive(gamepad1.left_stick_y, gamepad1.left_trigger-gamepad1.right_trigger);
        robot.drone.launch(gamepad1.y);
    }
}
