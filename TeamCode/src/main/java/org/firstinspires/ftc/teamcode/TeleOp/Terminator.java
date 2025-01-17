package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "terminatorCode", group = "testing")
public class Terminator extends LinearOpMode {

    private DcMotor support = null;
    private DcMotor sliderArm = null;
    private CRServo intake = null;
    private DcMotor fl = null;
    private DcMotor bl = null;
    private DcMotor fr = null;
    private DcMotor br = null;

    private double moveSpeed; //variable to control speed
    private double intakeSpeed; //variable to control the speed of the intake
    private double armSpeed; //variable to control the arm speed
    private double sliderValue; //variable to get the slider gamepad input
    private double sliderSpeed; //variable to control the slider speed
    private boolean isHanging;

    @Override
    public void runOpMode() {
        support = hardwareMap.get(DcMotor.class, "Arm");
        sliderArm = hardwareMap.get(DcMotor.class, "armRight");
        intake = hardwareMap.get(CRServo.class, "Intake");
        fl = hardwareMap.get(DcMotor.class, "Left-Front");
        bl = hardwareMap.get(DcMotor.class, "Left-Back");
        fr = hardwareMap.get(DcMotor.class, "Right-Front");
        br = hardwareMap.get(DcMotor.class, "Right-Back");

        //Variable
        moveSpeed = 0.9;
        intakeSpeed = 1;
        armSpeed = 1;
        sliderValue = 0;
        sliderSpeed = 0.8;

        waitForStart();

        while (opModeIsActive()) {

            //Player 1 Function Code
            {
                //Set the motor speeds to their respective side's joystick
                double leftSpeed = -gamepad1.left_stick_y * moveSpeed;
                double rightSpeed = -gamepad1.right_stick_y * moveSpeed;
                double sliderValue = gamepad2.right_stick_y * sliderSpeed;

                //Left and Right Drive Code
                fl.setPower(leftSpeed);
                bl.setPower(-leftSpeed);
                fr.setPower(rightSpeed);
                br.setPower(rightSpeed);

                //Full Force Forwards
                if (gamepad1.dpad_up){
                    fl.setPower(moveSpeed);
                    bl.setPower(-moveSpeed);
                    fr.setPower(moveSpeed);
                    br.setPower(moveSpeed);
                }

                //Full Force Backwards
                if (gamepad1.dpad_down){
                    fl.setPower(-moveSpeed);
                    bl.setPower(moveSpeed);
                    fr.setPower(-moveSpeed);
                    br.setPower(-moveSpeed);
                }

                //Diagonal Strafe Right
                if (gamepad1.right_trigger > 0){
                    bl.setPower(-moveSpeed);
                    br.setPower(moveSpeed);
                }

                //Diagonal Strafe Left
                if (gamepad1.left_trigger > 0){
                    fl.setPower(moveSpeed);
                    fr.setPower(moveSpeed);
                }

                //Strafe Right
                if (gamepad1.right_bumper){
                    fl.setPower(-moveSpeed);
                    bl.setPower(-moveSpeed);
                    fr.setPower(-moveSpeed);
                    br.setPower(moveSpeed);
                }

                //Strafe Left
                if (gamepad1.left_bumper){
                    fl.setPower(moveSpeed);
                    bl.setPower(moveSpeed);
                    fr.setPower(moveSpeed);
                    br.setPower(-moveSpeed);
                }
            }

            //Player 2 Function Code
            {
                intake.setPower(gamepad2.left_stick_y * 1);

                //Slider Arm Code
                sliderArm.setPower(gamepad2.right_stick_y * 0.75);

                //Support Arm Code
                if (gamepad2.left_trigger > 0) {
                    support.setPower(gamepad2.left_trigger * armSpeed);
                } else if (gamepad2.right_trigger > 0){
                    support.setPower(gamepad2.right_trigger * -armSpeed);
                } else {
                    support.setPower(0);
                }

                //Hand Code
                if (gamepad2.a)
                {
                    isHanging = true;
                }

                if (isHanging)
                {
                    support.setPower(-1);
                }
            }
        }
    }
}
