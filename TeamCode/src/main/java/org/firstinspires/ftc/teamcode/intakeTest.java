package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class intakeTest extends LinearOpMode {
    private Servo intakeServo;
    private Servo clawServo;
    double intakeGrabPosition = 0.4;//servo position
    double intakeAimPosition = 0.3;
    double intakeDefaultPosition = 0.9;
    //claw open
    private enum intakeArmState{
        AIM,GRAB,DEFAULT
    }
    private intakeArmState intakeSystemState = intakeArmState.DEFAULT;

    private boolean clawState = false;

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
            //intaker Commands
            if (gamepad2.a) {
                clawState = true;
                intakeSystemState = intakeArmState.AIM;
                if(gamepad2.b){//outtake
                    clawState = false;
                    intakeSystemState = intakeArmState.AIM;
                }
                else if(gamepad1.x){
                    intakeSystemState = intakeArmState.GRAB;
                    clawState = true;
                }
            }
            else{
                intakeSystemState = intakeArmState.DEFAULT;
                clawState = true;
            }

            //apply intaker positions
            switch (intakeSystemState){
            case AIM:
                setIntake(intakeAimPosition);
                break;
            case GRAB:
                setIntake(intakeGrabPosition);
                break;
            case DEFAULT:
                setIntake(intakeDefaultPosition);
                break;
            default:
                System.out.println("Invalid Intake State");
                break;
            }

            //apply claw position
            setClawPosition(clawState);
        }
    }
}

