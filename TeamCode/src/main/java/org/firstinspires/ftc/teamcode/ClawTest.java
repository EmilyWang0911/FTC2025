package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class ClawTest extends LinearOpMode {

    private Servo servoL;
    private Servo servoR;

    public void setClawPosition(boolean state){
        if (state == false){
            servoL.setPosition(0.4);
            servoR.setPosition(1);
        }else{
            servoL.setPosition(0.9);
            servoR.setPosition(0.5);
        }
    }
    @Override
    public void runOpMode(){
        servoL = hardwareMap.get(Servo.class, "servoL");
        servoR = hardwareMap.get(Servo.class, "servoR");

        telemetry.addData("Status","Initialized");
        telemetry.update();
        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.y){
                setClawPosition(false);
            }
            else if(gamepad1.b){
                setClawPosition(true);
            }
        }
    }
}