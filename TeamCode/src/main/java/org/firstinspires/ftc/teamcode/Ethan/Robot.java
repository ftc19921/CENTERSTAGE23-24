package org.firstinspires.ftc.teamcode.Ethan;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Ethan.mechanisms.MecanumDrive;

public class Robot {
    public MecanumDrive mecanumDrive = new MecanumDrive();

    public void init(HardwareMap hardwareMap){
        mecanumDrive.init(hardwareMap);
    }
}
