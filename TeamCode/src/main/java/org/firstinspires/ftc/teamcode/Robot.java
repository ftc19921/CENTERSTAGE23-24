package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.Arm;
import org.firstinspires.ftc.teamcode.mechanisms.EndEffecter;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.OutakeArm;

public class Robot {
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public Intake intake = new Intake();
    public Arm intakeArm = new Arm();
    public OutakeArm outakeArm = new OutakeArm();
    public EndEffecter endEffecter = new EndEffecter();
    public void init(HardwareMap hardwareMap){
        outakeArm.init(hardwareMap);
        endEffecter.init(hardwareMap);
        mecanumDrive.init(hardwareMap);
        intake.init(hardwareMap);
        intakeArm.init(hardwareMap);

    }
}
