package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Roboto;

@TeleOp
public class Teleop1 extends OpMode {
    Roboto robot = new Roboto();


    @Override
    public void init() {
        robot.init(hardwareMap);

    }

    @Override
    public void loop() {
        if(gamepad1.a){
            robot.stilts.SpinDown();
        }else if(gamepad1.b){
            robot.stilts.SpinUp();
        }else{
            robot.stilts.StopMotor();
        }

    }
}
