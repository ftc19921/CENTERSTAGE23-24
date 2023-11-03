package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    public MecanumDrive mecanumDrive = new MecanumDrive();

    public void init(HardwareMap hardwareMap){
        mecanumDrive.init(hardwareMap);
    }
}
