package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
@TeleOp
public class TrollTeleop extends OpMode {
    Robot robot = new Robot();
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.tankDrive.setPowers(gamepad1.left_stick_y, gamepad1.right_stick_x);
        telemetry.addData("turn",gamepad1.right_stick_x);
        }
}
