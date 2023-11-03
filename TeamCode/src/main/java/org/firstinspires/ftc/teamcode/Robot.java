package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MechanumDrive;

public class Robot {
    public MechanumDrive mechanumDrive = new MechanumDrive();
    public Intake intake = new Intake();

    public void init(HardwareMap hardwareMap){
        mechanumDrive.init(hardwareMap);
        intake.init(hardwareMap);
    }
}
