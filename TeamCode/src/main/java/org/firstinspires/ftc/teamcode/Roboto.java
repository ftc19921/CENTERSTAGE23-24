package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Stilts;

public class Roboto {

    public MecanumDrive mecanumDrive = new MecanumDrive();

    public Stilts stilts = new Stilts();
    public void init(HardwareMap hardwareMap){
        stilts.init(hardwareMap);

        //mecanumDrive.init(hardwareMap);


    }
}
