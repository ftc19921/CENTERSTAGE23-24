package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp()
public class TestMecanum extends OpMode {
    Robot robot = new Robot();
    //Intake intake = new Intake();

    @Override
    public void init() {
        robot.init(hardwareMap);
        //intake.init(hardwareMap);
    }

    @Override
    public void loop() {
        //robot.mecanumDrive.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        robot.mecanumDrive.test(gamepad1.a, gamepad1.b, gamepad1.x, gamepad1.y);
        /*
        if (gamepad1.dpad_left || gamepad1.dpad_right) {
            intake.stop();
        } else if (gamepad1.dpad_up) {
            intake.forward();
        } else if (gamepad1.dpad_down){
            intake.backward();
        }
        */
    }
}
