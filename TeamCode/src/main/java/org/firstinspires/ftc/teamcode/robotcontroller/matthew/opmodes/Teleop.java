package org.firstinspires.ftc.teamcode.robotcontroller.matthew.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotcontroller.matthew.Robot;
import org.firstinspires.ftc.teamcode.robotcontroller.matthew.mechanisms.Camera;

@TeleOp
public class Teleop extends OpMode {
    Robot robot = new Robot();
    Camera camera = new Camera();
    @Override
    public void init() {
        robot.init(hardwareMap);
        camera.init(hardwareMap);
    }

    @Override
    public void loop() {
//        robot.tankDrive.drive(gamepad1.left_stick_y, gamepad1.left_trigger-gamepad1.right_trigger);
        robot.macunumDrive.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.left_trigger-gamepad1.right_trigger);
        telemetry.addLine(camera.getDebugString());
        //        robot.drone.launch(gamepad1.y);
    }
}
