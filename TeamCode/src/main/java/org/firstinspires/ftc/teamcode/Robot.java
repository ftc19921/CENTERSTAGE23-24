package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.Arm;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

public class Robot {
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public Intake intake = new Intake();
    public Arm arm = new Arm();

    public void init(HardwareMap hardwareMap){
       mecanumDrive.init(hardwareMap);
       intake.init(hardwareMap);
       arm.init(hardwareMap);

    }

}
