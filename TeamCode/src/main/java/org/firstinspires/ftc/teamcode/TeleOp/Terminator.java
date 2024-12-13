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

        moveSpeed = 0.75;

        waitForStart();

        while (opModeIsActive()) {

            //Gamepad 1 Function Code
            {
                double leftSpeed = gamepad1.left_stick_y * moveSpeed;
                double rightSpeed = -gamepad1.right_stick_y * moveSpeed;

                fl.setPower(leftSpeed);
                bl.setPower(-leftSpeed);
                fr.setPower(rightSpeed);
                br.setPower(rightSpeed);

                if (gamepad1.left_bumper){
                    fl.setPower(-leftSpeed);
                    bl.setPower(-leftSpeed);
                    fr.setPower(-rightSpeed);
                    br.setPower(rightSpeed);
                }

                if (gamepad1.right_bumper){
                    fl.setPower(leftSpeed);
                    bl.setPower(leftSpeed);
                    fr.setPower(rightSpeed);
                    br.setPower(-rightSpeed);
                }
            }

            //Gamepad 2 Function Code
            {
                if (gamepad2.right_trigger > 0) {
                    support.setTargetPosition(600);
                    support.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    support.setVelocity(500 * gamepad2.right_trigger);
                } else if (gamepad2.left_trigger > 0) {
                    support.setTargetPosition(60);
                    support.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    support.setVelocity(500 * gamepad2.left_trigger);
                } else {
                    support.setVelocity(3);
                }
            }
        }
    }
}
