package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.Roboto;

public class Lift {
    public TouchSensor TS;
    public DcMotor wenchMotor;
    public DcMotor wenchMotor2;
    int ticks;
    public boolean atTop;
    public boolean atBottom;
    public boolean stageIncrease1;
    public boolean stageIncrease2;

    public void init(HardwareMap hardwareMap){
       wenchMotor = hardwareMap.get(DcMotor.class,"2");
       wenchMotor2 = hardwareMap.get(DcMotor.class,"ex2");
       TS = hardwareMap.get(TouchSensor.class,"TS");
       wenchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       wenchMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ticks = 0;
        atBottom=true;
        atTop=true;

    }
    public void reset(){

            wenchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            wenchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void hoist(){
        wenchMotor.setPower(-0.6);
        wenchMotor2.setPower(0.6);
    }

    public void release(){
        wenchMotor.setPower(0.6);
        wenchMotor2.setPower(-0.6);
    }
    public void Stop(){
        wenchMotor.setPower(0);
        wenchMotor2.setPower(0);
    }
    public void ToTop(){
        stageIncrease1=false;
        if(!atTop) {

            if (wenchMotor.getCurrentPosition() > 3190) {
                atTop=true;
                stageIncrease1=true;
                wenchMotor.setPower(0);
                wenchMotor2.setPower(0);

            } else {
                wenchMotor.setPower(0.6);
                wenchMotor2.setPower(-0.6);
            }

        }
    }
    public void ToBottom(){
        stageIncrease2=false;
        if(!atBottom){

            if(TS.isPressed()){
                wenchMotor.setPower(0);
                wenchMotor2.setPower(0);
                atBottom=true;
                stageIncrease2=true;

            }else{
                wenchMotor.setPower(-0.5);
                wenchMotor2.setPower(0.5);
            }

        }
    }
}
