package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
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
        robot.mecanumDrive.Drive(gamepad1.right_stick_y, gamepad1.left_stick_x, turn);
    }
}
