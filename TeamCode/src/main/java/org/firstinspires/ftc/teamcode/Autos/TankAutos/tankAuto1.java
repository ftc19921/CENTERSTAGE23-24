package org.firstinspires.ftc.teamcode.Autos.TankAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class tankAuto1 extends OpMode {
    Robot robot = new Robot();
    int position;
    float forward=0;
    float turn=0;
    int rot = -1000;
    @Override
    public void init(){robot.init(hardwareMap);
        position = robot.camera.getLocation();
    }

    @Override
    public void loop(
    ){
        telemetry.addData("prop pos:", position);
        telemetry.addData("field pos",robot.tankDrive.getPosition(true));
        telemetry.update();
        forward=0;
        turn=0;
        switch (position){
            case -1:
                break;
            case 0:
                if (robot.tankDrive.getPosition(true) >= 1 * rot) {
                    forward = 1;
                }
                break;
            case 1:
                break;
            default:
                break;
        }
        robot.tankDrive.setPowers(-forward,turn);
    }

}
