package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class intakeTest extends LinearOpMode {
    private Servo intakeServo;
    private Servo clawServo;

    double intakeGrabPosition = 0.4;//servo position
    double intakeAimPosition = 0.3;
    double intakeDefaultPosition = 0.9;


    public void setIntake(double position){
        intakeServo.setPosition(position);
    }

    public void setClawPosition(boolean state){
        if (!state){
            clawServo.setPosition(0.9);//claw open; value to be determined

        }else{
            clawServo.setPosition(0.5);//claw closed
        }
    }

    @Override
    public void runOpMode(){
        intakeServo = hardwareMap.get(Servo.class, "IntakeServo");
        clawServo = hardwareMap.get(Servo.class, "clawServo");


        telemetry.addData("Status","Initialized");
        telemetry.update();
        waitForStart();

        while(opModeIsActive()) {
            if (gamepad1.a) {
                setClawPosition(true);
                setIntake(intakeAimPosition);
                if(gamepad1.b){
                    setClawPosition(false);//claw open
                else if(gamepad1.x){
                    setIntake(intakeGrabPosition);
                    setClawPosition(true);
                    setIntake(intakeAimPosition);
                }
            }
            else{
                setIntake(intakeDefaultPosition);
                setClawPosition(true);
            }
        }
    }
}

