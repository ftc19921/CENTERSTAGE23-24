package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.Arm;
import org.firstinspires.ftc.teamcode.mechanisms.EndEffecter;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Lift;

public class Roboto {
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public Intake intake = new Intake();
    public Arm Arm = new Arm();
    public Lift lift= new Lift();
    public EndEffecter endEffecter = new EndEffecter();
    public void init(HardwareMap hardwareMap){
        lift.init(hardwareMap);
        endEffecter.init(hardwareMap);
        mecanumDrive.init(hardwareMap);
        intake.init(hardwareMap);
        Arm.init(hardwareMap);
    }
}
