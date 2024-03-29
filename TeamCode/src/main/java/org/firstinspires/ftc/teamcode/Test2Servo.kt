package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import java.lang.Exception

@TeleOp
class Test2Servo: LinearOpMode() {
    override fun runOpMode() {
        val runtime = ElapsedTime()

        telemetry.addData("Status", "Initialized")
        telemetry.update()
        val hwMap = hardwareMap

        val servoTest = hwMap.servo["outtakeTest1"] ?: throw Exception("Failed to find servo outtakeTest1")
        val servoTest2 = hwMap.servo["outtakeTest2"] ?: throw Exception("Failed to find servo outtakeTest2")
        // 0.7 start
          servoTest.position = 0.648 // 0.645
          servoTest2.position =0.2
        waitForStart()
        runtime.reset()

// 0.45 right
        // 0.55 left
        val gp1 = Gamepad(gamepad1)
        val gp2 = Gamepad(gamepad2)
        while (opModeIsActive()) {


            if(gp1.checkToggle(Gamepad.Button.DPAD_UP))
            {
                servoTest.position += 0.001
            }

            if(gp1.checkToggle(Gamepad.Button.DPAD_DOWN))
            {
                servoTest.position -= 0.001
            }
            if(gp2.checkToggle(Gamepad.Button.DPAD_UP))
            {
                servoTest2.position += 0.001
            }

            if(gp2.checkToggle(Gamepad.Button.DPAD_DOWN))
            {
                servoTest2.position -= 0.001
            }
            if(gp1.checkToggle(Gamepad.Button.X))
                servoTest.position = 0.78



            telemetry.addData("Status(servo 1): ", servoTest.position)
            telemetry.addData("Status(servo 2): ", servoTest2.position)

            telemetry.update()
        }

    }
}