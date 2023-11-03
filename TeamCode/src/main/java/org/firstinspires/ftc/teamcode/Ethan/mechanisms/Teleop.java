package org.firstinspires.ftc.teamcode.Ethan.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Ethan.Robot;

@TeleOp
public class Teleop extends OpMode {
    Robot robot = new Robot();
    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.mecanumDrive.drive(-gamepad1.right_stick_y, gamepad1.right_stick_x,gamepad1.left_stick_x);

    }
}
