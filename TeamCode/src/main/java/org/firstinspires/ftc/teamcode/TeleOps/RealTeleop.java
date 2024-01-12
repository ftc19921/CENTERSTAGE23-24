package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp
public class RealTeleop extends OpMode {
    Robot robot = new Robot();

    double turn;

    boolean fieldCentric;

    boolean backJustPressed;

    @Override
    public void init() {
        robot.init(hardwareMap);
        fieldCentric = false;
        backJustPressed = false;
    }

    @Override
    public void loop() {
        if (gamepad1.back) {
            if (!backJustPressed) {
                fieldCentric = !fieldCentric;
            }
            backJustPressed = true;
        } else {
            backJustPressed = false;
        }

        boolean rumble2 = false;

        turn = (gamepad1.right_trigger + ((gamepad1.right_bumper ? 1 : 0) * 0.2)) - (gamepad1.left_trigger + ((gamepad1.left_bumper ? 1 : 0) * 0.2));
        turn = Math.min(1, Math.max(-1, turn));
        if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0 || turn != 0) {
            gamepad1.rumble(0.2, 0.2, Gamepad.RUMBLE_DURATION_CONTINUOUS);
        } else {
            gamepad1.stopRumble();
        }
        robot.mecanumDrive.Drive(-gamepad1.left_stick_y, gamepad1.right_stick_x, turn);
        robot.arm.update();

        if (gamepad2.dpad_down) {
            robot.arm.flat();
        } else if (gamepad2.dpad_up) {
            robot.arm.place();
        } else if (gamepad2.dpad_right) {
            robot.arm.hang();
            gamepad2.rumble(2000);
            rumble2 = true;
        }

        robot.arm.setPower(gamepad2.left_stick_y);


        if (gamepad2.a) {
            robot.claw.move(1);
        } else if (gamepad2.b) {
            robot.claw.move(-1);
        } else {
            robot.claw.move(0);
        }
        robot.clawArm.move((float) (gamepad2.right_stick_y));

        if (robot.arm.moving()) {
            gamepad2.rumble(0.2, 0.2, Gamepad.RUMBLE_DURATION_CONTINUOUS);
            rumble2 = true;
        }
        telemetry.addData("Position", robot.arm.getPos() + "/" + robot.arm.getTarget());

        if (gamepad2.left_bumper && gamepad2.right_bumper) {
            robot.drone.launch();
        } else {
            robot.drone.stop();
        }

        if (!rumble2) {
            gamepad2.stopRumble();
        }
    }
}
