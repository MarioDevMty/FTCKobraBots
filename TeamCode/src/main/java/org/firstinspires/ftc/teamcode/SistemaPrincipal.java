package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class sistema_principal extends LinearOpMode {

    DcMotor Mi;
    DcMotor Md;
    DcMotorEx Mt;

    @Override
    public void runOpMode() {

        Mi = hardwareMap.get(DcMotor.class, "Mi");
        Md = hardwareMap.get(DcMotor.class, "Md");
        Mt = hardwareMap.get(DcMotorEx.class, "Mt");

        Md.setDirection(DcMotor.Direction.REVERSE);

        Mt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Mt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Mt.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            double avanzar = -gamepad1.left_stick_y;
            double girar = gamepad1.left_stick_x;

            double potenciaIzquierda = avanzar + girar;
            double potenciaDerecha = avanzar - girar;

            // limitar potencia entre -1 y 1
            potenciaIzquierda = Math.max(-1, Math.min(1, potenciaIzquierda));
            potenciaDerecha = Math.max(-1, Math.min(1, potenciaDerecha));

            Mi.setPower(potenciaIzquierda);
            Md.setPower(potenciaDerecha);

            // motor disparador
            if (gamepad1.a) {
                Mt.setPower(1.0);
            } else {
                Mt.setPower(0.0);
            }

            telemetry.addData("Mi", potenciaIzquierda);
            telemetry.addData("Md", potenciaDerecha);
            telemetry.addData("Mt", Mt.getPower());
            telemetry.update();
        }
    }
}