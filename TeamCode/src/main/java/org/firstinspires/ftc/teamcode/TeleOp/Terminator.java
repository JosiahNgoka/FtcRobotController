package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp (name = "armPos", group = "testing")
public class Terminator extends LinearOpMode {

    private DcMotorEx support = null;
    private DcMotor fl = null;
    private DcMotor bl = null;
    private DcMotor fr = null;
    private DcMotor br = null;

    private double moveSpeed; //variable to control speed

    @Override
    public void runOpMode() {
        support = hardwareMap.get(DcMotorEx.class, "Arm");
        fl = hardwareMap.get(DcMotor.class, "Left-Front");
        bl = hardwareMap.get(DcMotor.class, "Left-Back");
        fr = hardwareMap.get(DcMotor.class, "Right-Front");
        br = hardwareMap.get(DcMotor.class, "Right-Back");

        support.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        support.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        moveSpeed = 1;

        waitForStart();

        while (opModeIsActive()) {

            //Gamepad 1 Function Code
            {
                //Set the motor speeds to their respective side's joystick
                double leftSpeed = -gamepad1.left_stick_y * moveSpeed;
                double rightSpeed = -gamepad1.right_stick_y * moveSpeed;

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

            //Gamepad 2 Function Code
            {

            }
        }
    }
}
