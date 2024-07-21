package org.firstinspires.ftc.teamcode.Autos.TankAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class autoPark extends OpMode {
    Robot robot = new Robot();
    int frames = 0;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (frames < 10) {
//            robot.tankDrive.setPowers(1, 0);
        } else {
//            robot.tankDrive.setPowers(0, 0);
        }
        frames++;
    }
}
