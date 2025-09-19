package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Roboto;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

@TeleOp
public class TeleOp2 extends OpMode {
    Roboto robot = new Roboto();
    ColorSensor IntakeColorSensor;

    @Override
    public void init() {
        robot.init(hardwareMap);
        IntakeColorSensor=hardwareMap.get(ColorSensor.class,"ColorSensor");
    }

    @Override
    public void loop() {
        if (gamepad1.left_stick_y == 0){

            robot.mecanumDrive.Drive(5, 5, 5);
        } else {robot.mecanumDrive.Drive(0, 0, 0);}


    }
}
