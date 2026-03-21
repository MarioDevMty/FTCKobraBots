package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class SistemaPrincipal extends LinearOpMode {

    DcMotor Mi;
    DcMotor Md;
    DcMotor Mr;
    DcMotorEx Mt;

    @Override
    public void runOpMode() {

        Mi = hardwareMap.get(DcMotor.class, "Mi");
        Md = hardwareMap.get(DcMotor.class, "Md");
        Mr = hardwareMap.get(DcMotor.class, "Mr");
        Mt = hardwareMap.get(DcMotorEx.class, "Mt");

        Md.setDirection(DcMotor.Direction.REVERSE);

        Mi.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Mi.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Mt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Mt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Mt.setDirection(DcMotor.Direction.REVERSE);

        Mr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Mr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Mr.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        while (opModeIsActive()) {

            double avanzar = -gamepad1.left_stick_y;
            double girar = gamepad1.right_stick_x;

            double potenciaIzquierda = avanzar + girar;
            double potenciaDerecha = avanzar - girar;

            potenciaIzquierda = Math.max(-1, Math.min(1, potenciaIzquierda));
            potenciaDerecha = Math.max(-1, Math.min(1, potenciaDerecha));

            Mi.setPower(potenciaIzquierda);
            Md.setPower(potenciaDerecha);

            Mt.setPower(gamepad1.right_trigger);
            if (gamepad1.left_bumper) {
                Mr.setPower(1);
            } else {
                Mr.setPower(0);
            }

            telemetry.addData("Mi", potenciaIzquierda);
            telemetry.addData("Md", potenciaDerecha);
            telemetry.addData("Mt", Mt.getPower());
            telemetry.addData("Mr", Mr.getPower());
            telemetry.update();
        }
    }
}