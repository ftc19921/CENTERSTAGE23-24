package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class AutoCloseRed extends OpMode {
    Robot robot = new Robot();
    int frame = 0;
    int simToReal = 1;
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        double forward = 0;
        double right = 0;
        double turn = 0;
        if (frame < 100*simToReal) {
            forward = 1;
        }
        if (frame < 1000*simToReal) {
            if (frame < 100*simToReal) {
                right = 0.1;
            } else {
                right = 1;
            }
        }
        robot.mecanumDrive.Drive(forward, right, turn);
        frame++;
    }
}
