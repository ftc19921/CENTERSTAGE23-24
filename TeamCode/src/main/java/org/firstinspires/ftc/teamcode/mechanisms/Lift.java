package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Lift {
    public TouchSensor TS;
    DcMotor wenchMotor;
    public DcMotor wenchMotor2;
    int ticks;
    public void init(HardwareMap hardwareMap){
       wenchMotor = hardwareMap.get(DcMotor.class,"2");
       wenchMotor2 = hardwareMap.get(DcMotor.class,"ex2");
       TS = hardwareMap.get(TouchSensor.class,"TS");
       wenchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        wenchMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ticks = 0;
    }
    public void reset(){

            wenchMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            wenchMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    public void hoist(){
        wenchMotor.setPower(0.8);
        wenchMotor2.setPower(-0.8);
    }
    public void release(){
        wenchMotor.setPower(-0.8);
        wenchMotor2.setPower(0.8);
    }
    public void Stop(){
        wenchMotor.setPower(0);
        wenchMotor2.setPower(0);
    }
    public void ToTop(){
        while(true){

            if(wenchMotor2.getCurrentPosition()>3190){

                break;
            }else{
                wenchMotor.setPower(-0.4);
                wenchMotor2.setPower(0.4);
            }

        }
    }
    public void ToBottom(){
        while(true){

            if(wenchMotor2.getCurrentPosition()==0){
                wenchMotor.setPower(0);
                wenchMotor2.setPower(0);
                break;
            }else{
                wenchMotor.setPower(0.4);
                wenchMotor2.setPower(-0.4);
            }

        }
    }
}
