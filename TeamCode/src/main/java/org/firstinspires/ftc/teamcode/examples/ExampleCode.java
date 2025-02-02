//Jun Park
package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Single-Motor Test", group = "Iterative Opmode")
public class ExampleCode extends OpMode {// classes controlling robots must exted OpMode class

    //here, motors are defined. This example class contains motors for a four wheel robot.

    //F = front
    //B = back
    //L = left
    //R = right
    DcMotor motor1;
    //DcMotor motorFR;
    //DcMotor motorBL;
    //DcMotor motorBR;

    //This is a constructor. We don't need anything here for now, so just pass it on to super().
    public ExampleCode() {
        super();
    }

    //to make an OpMode class, you must override a few methods. here is the init() method.
    //code here excecutes when init button is pressed on the controller phone.
    @Override
    public void init() {
        //motors needs to be configured on the controller phones exactly as they are here to work
        //the following code is used to map our motor variables to the physical motors
        motor1 = hardwareMap.dcMotor.get("motor1");
        //motorFR = hardwareMap.dcMotor.get("motorFR");
        //motorBL = hardwareMap.dcMotor.get("motorBL");
        //motorBR = hardwareMap.dcMotor.get("motorBR");

        //the following code is used to set the initial directions of the motors.
        //normally, the motors on the left should be set to Direction.REVERSE,
        //but testing should resolve any issues with motor directions.
        motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        //motorFR.setDirection(DcMotorSimple.Direction.FORWARD);
        //motorBL.setDirection(DcMotorSimple.Direction.REVERSE);
        //motorBR.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    //here is the loop() method that also needs override
    //code here excecutes in an infinite loop after the start button is pressed.
    @Override
    public void loop() {
        //this method establishes the controls for the robot using the two gamepads(controllers).

        //resetting robot movement to not moving so if no controls are inputted, robot doesn't move.
        resetMotors();

        //this is a helper method for all controls for the robot.
        controlRobot();

        //this is a helper method for reporting data to the controller phone.
        reportTelemetry();

    }

    //this is a helper method that contains all controls for the robot.
    //this method contains one loop of the controls, since it is called by the loop() method.
    private void controlRobot() {

        //left joystick of the first controller will control the movement of the robot.
        if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0) {
            if (Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y)) {
                rotate((double) -gamepad1.left_stick_x);
            } else if (Math.abs(gamepad1.left_stick_x) < Math.abs(gamepad1.left_stick_y)) {
                moveForward((double) -gamepad1.left_stick_y);
            }
        } else {
            double leftPower = -gamepad1.right_stick_y;
            double rightPower = -gamepad1.right_stick_y;

            leftPower += (gamepad1.right_stick_x);
            rightPower -= (gamepad1.right_stick_x);

            motor1.setPower(leftPower);
            //motorFR.setPower(rightPower);
            //motorBL.setPower(leftPower);
            //motorBR.setPower(rightPower);
        }
    }

    //helper method to report data to controller phone.
    public void reportTelemetry() {

        //report gamepad1 left joystick data
        telemetry.addData("gamepad1 leftX", gamepad1.left_stick_x);
        telemetry.addData("gamepad1 leftY", gamepad1.left_stick_y);

        //report motors' power
        telemetry.addData("motor1", motor1.getPower());
        //telemetry.addData("motorFR", motorFR.getPower());
        //telemetry.addData("motorBL", motorBL.getPower());
        //telemetry.addData("motorBR", motorBR.getPower());

        //update the data screen.
        telemetry.update();
    }

    //helper method to reset all motors to not moving.
    private void resetMotors() {
        motor1.setPower(0.0);
        //motorFR.setPower(0.0);
        //motorBL.setPower(0.0);
        //motorBR.setPower(0.0);
    }

    //helper method to move the robot
    //pass in negative value to move backward
    private void moveForward(double power) {
        motor1.setPower(power);
        //motorFR.setPower(power);
        //motorBL.setPower(power);
        //motorBR.setPower(power);
    }

    //helper method to rotate the robot
    //pass in negative value to rotate right
    private void rotate(double power) {
        motor1.setPower(-power);
        //motorFR.setPower(power);
        //motorBL.setPower(-power);
        //motorBR.setPower(power);
    }
}