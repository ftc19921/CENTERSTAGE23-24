package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake implements Mechanism {

    public static final int INTAKE_IN_POWER = 1;
    public static final int INTAKE_OUT_POWER = -1;
    private DcMotor intakeMotor;

    @Override
    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
    }

    public void forward(){
        intakeMotor.setPower(INTAKE_IN_POWER);
    }
    public void backward() {
        intakeMotor.setPower(INTAKE_OUT_POWER);
    }

    public void stop() {
        intakeMotor.setPower(0);
    }
    public void setDirection(int direction) {
        switch (direction) {
            case 1:
                intakeMotor.setPower(1);
                break;
            case -1:
                intakeMotor.setPower(-1);
            default:
                intakeMotor.setPower(0);
        }
    }
}
