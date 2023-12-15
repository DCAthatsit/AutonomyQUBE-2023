package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.hardware.Hardware
import java.lang.Math.atan2
/*
@TeleOp(name = "CompleteDrive_player1", group = "Main")
class CompleteDrive_player1: OpMode() {

    override fun preInit() {

    }
    override fun preInitLoop() {
        // hw.outtake.initSlider()
        // hw.outtake.initClaws()
        telemetry.addLine("Waiting for start...")
        telemetry.update()
        idle()
    }

    enum class LiftState{
        LIFT_START,
        LIFT_EXTEND_LOW,
        LIFT_EXTEND_MEDIUM,
        LIFT_EXTEND_UP,
        LIFT_LOW,
        LIFT_MEDIUM,
        LIFT_UP,
        LIFT_DUMP_UP,
        LIFT_DUMP_MEDIUM,
        LIFT_DUMP_LOW,
        LIFT_RETRACT,
    }

    override fun Hardware.run() {

        val gp1 = Gamepad(gamepad1)
        val gp2 = Gamepad(gamepad2)

        var liftState = LiftState.LIFT_START

        var liftTimer = ElapsedTime()
        liftTimer.reset()

        //TODO Adjust these variables according to the needs

        val DUMP_TIME_LOW = 0.1
        val DUMP_TIME_UP = 0.2package org.firstinspires.ftc.teamcode

        import com.qualcomm.robotcore.eventloop.opmode.Disabled
                import com.qualcomm.robotcore.eventloop.opmode.TeleOp
                import com.qualcomm.robotcore.hardware.DistanceSensor
                import com.qualcomm.robotcore.util.ElapsedTime
                import org.firstinspires.ftc.teamcode.hardware.Hardware
                import org.firstinspires.ftc.teamcode.hardware.Outtake
                import java.lang.Math.atan2

                @TeleOp(name = "CompleteDrive", group = "Main")
                class CompleteDrive: OpMode() {

                    override fun preInit() {
                    }
                    override fun preInitLoop() {
                        //   hw.outtake.initSlider()
                        //   hw.outtake.initClaws()
                        telemetry.addLine("Waiting for start...")
                        telemetry.update()
                        idle()
                    }

                    enum class LiftState{
                        LIFT_START,
                        LIFT_EXTEND_LOW,
                        LIFT_EXTEND_MEDIUM,
                        LIFT_EXTEND_UP,
                        LIFT_LOW,
                        LIFT_MEDIUM,
                        LIFT_UP,
                        LIFT_DUMP_UP,
                        LIFT_DUMP_MEDIUM,
                        LIFT_DUMP_LOW,
                        LIFT_RETRACT,
                    }

                    override fun Hardware.run() {

                        val gp1 = Gamepad(gamepad1)
                        val gp2 = Gamepad(gamepad2)

                        var leftTriggerIsPressed = false
                        var rightTriggerIsPressed = false

                        var liftState = LiftState.LIFT_START

                        var liftTimer = ElapsedTime()
                        liftTimer.reset()

                        //TODO Adjust these variables according to the needs

                        var DUMP_TIME_LOW = 0.1
                        var DUMP_TIME_UP = 0.2
                        var DUMP_TIME_MEDIUM = 0.9



                        var isReleased = false
                        waitForStart()
                        while(opModeIsActive())
                        {

                            val power = speed
                            val rotPower = rotation

                            hw.motors.move(direction,power,rotPower)
                            outtake.printPosition(telemetry)
                            //Intake forward and backward

                            //State of sliders for smooth movement
                            when(liftState)
                            {
                                LiftState.LIFT_START -> {
                                    if(gp2.checkToggle(Gamepad.Button.RIGHT_BUMPER))
                                    {
                                        outtake.holdCleste()
                                        outtake.openSlider()
                                        liftState = LiftState.LIFT_EXTEND_UP
                                    }
                                    if(gp2.checkToggle(Gamepad.Button.B))
                                    {
                                        outtake.holdCleste()
                                        outtake.openMidSlider()
                                        liftState = LiftState.LIFT_EXTEND_MEDIUM
                                    }
                                    /*   if(gp2.checkToggle(Gamepad.Button.X))
                                       {
                                           outtake.holdCone()
                                           outtake.openLowSlider()
                                           liftState = LiftState.LIFT_EXTEND_LOW
                                       } */
                                }

                                /*   LiftState.LIFT_EXTEND_LOW -> {
                                       if(outtake.getSliderPositionRelativeToPosLow() < 50)
                                       {
                                           outtake.releaseArm()
                                           outtake.openServoRotation()
                                           liftState = LiftState.LIFT_LOW
                                       }
                                   } */


                                LiftState.LIFT_EXTEND_MEDIUM -> {
                                    if(outtake.getSliderPositionRelativeToPosMedium() < 100)
                                    {
                                        outtake.outtakeServo()
                                        outtake.openServoRotation()
                                        liftState = LiftState.LIFT_MEDIUM
                                    }
                                }

                                LiftState.LIFT_EXTEND_UP -> {
                                    if(outtake.getSliderPositionRelativeToPosHigh() < 1700)
                                    {
                                        outtake.outtakeServo()
                                        outtake.openServoRotation()
                                        liftState = LiftState.LIFT_UP
                                    }
                                }

                                LiftState.LIFT_LOW -> {
                                    if(gp2.checkToggle(Gamepad.Button.LEFT_BUMPER))
                                    {
                                        outtake.openCleste()
                                        isReleased = false
                                        outtake.closeServoRotation()
                                        /*outtake.interArm()
                                        sleep(300)*/
                                        //outtake.closeArm()
                                        outtake.intakeServo()

                                        liftTimer.reset()
                                        liftState = LiftState.LIFT_DUMP_LOW
                                    }
                                    if(gp2.checkToggle(Gamepad.Button.RIGHT_BUMPER))
                                    {
                                        outtake.holdCleste()
                                        outtake.openSlider()
                                        liftState = LiftState.LIFT_EXTEND_UP
                                    }
                                    if(gp2.checkToggle(Gamepad.Button.B))
                                    {
                                        outtake.holdCleste()
                                        outtake.openMidSlider()
                                        liftState = LiftState.LIFT_EXTEND_MEDIUM
                                    }
                                    /*   if(gp2.checkToggle(Gamepad.Button.X))
                                       {
                                           outtake.holdCone()
                                           outtake.openLowSlider()
                                           liftState = LiftState.LIFT_EXTEND_LOW
                                       } */
                                }

                                LiftState.LIFT_MEDIUM -> {
                                    if(gp2.checkToggle(Gamepad.Button.LEFT_BUMPER))
                                    {
                                        outtake.openCleste()
                                        isReleased = false
                                        outtake.closeServoRotation()
                                        /*outtake.interArm()
                                        sleep(300)*/
                                        outtake.intakeServo()

                                        liftTimer.reset()
                                        liftState = LiftState.LIFT_DUMP_MEDIUM
                                    }
                                    if(gp2.checkToggle(Gamepad.Button.RIGHT_BUMPER))
                                    {
                                        outtake.holdCleste()
                                        outtake.openSlider()
                                        liftState = LiftState.LIFT_EXTEND_UP
                                    }
                                    if(gp2.checkToggle(Gamepad.Button.B))
                                    {
                                        outtake.holdCleste()
                                        outtake.openMidSlider()
                                        liftState = LiftState.LIFT_EXTEND_MEDIUM
                                    }
                                    /*  if(gp2.checkToggle(Gamepad.Button.X))
                                     {
                                          outtake.holdCone()
                                          outtake.openLowSlider()
                                          liftState = LiftState.LIFT_EXTEND_LOW
                                      } */
                                }

                                LiftState.LIFT_UP -> {
                                    if(gp2.checkToggle(Gamepad.Button.LEFT_BUMPER))
                                    {
                                        outtake.holdCleste()
                                        isReleased = false
                                        outtake.closeServoRotation()
                                        /*outtake.interArm()
                                        sleep(300)*/
                                        outtake.intakeServo()
                                        liftTimer.reset()
                                        liftState = LiftState.LIFT_DUMP_UP
                                    }
                                    if(gp2.checkToggle(Gamepad.Button.RIGHT_BUMPER))
                                    {
                                        outtake.holdCleste()
                                        outtake.openSlider()
                                        liftState = LiftState.LIFT_EXTEND_UP
                                    }
                                    if(gp2.checkToggle(Gamepad.Button.B))
                                    {
                                        outtake.holdCleste()
                                        outtake.openMidSlider()
                                        liftState = LiftState.LIFT_EXTEND_MEDIUM
                                    }
                                    /* if(gp2.checkToggle(Gamepad.Button.X))
                                      {
                                          outtake.holdCone()
                                          outtake.openLowSlider()
                                          liftState = LiftState.LIFT_EXTEND_LOW
                                      } */
                                }

                                LiftState.LIFT_DUMP_LOW -> {
                                    if(liftTimer.seconds() >= DUMP_TIME_LOW)
                                    {
                                        outtake.closeSlider()
                                        liftState = LiftState.LIFT_RETRACT
                                    }
                                }

                                LiftState.LIFT_DUMP_MEDIUM -> {
                                    if(liftTimer.seconds() >= DUMP_TIME_MEDIUM)
                                    {
                                        outtake.closeSlider()
                                        liftState = LiftState.LIFT_RETRACT
                                    }
                                }

                                LiftState.LIFT_DUMP_UP -> {
                                    if(liftTimer.seconds() >= DUMP_TIME_UP)
                                    {
                                        outtake.closeSlider()
                                        liftState = LiftState.LIFT_RETRACT
                                    }
                                }

                                LiftState.LIFT_RETRACT -> {
                                    liftState = LiftState.LIFT_START
                                }

                                else -> {
                                    liftState = LiftState.LIFT_START
                                }
                            }

                            if(gp2.checkToggle(Gamepad.Button.A))
                            {
                                isReleased = if(!isReleased) {
                                    //         outtake.releaseCone()
                                    outtake.openCleste()
                                    true
                                } else {
                                    //   outtake.holdCone()
                                    outtake.holdCleste()
                                    false
                                }
                            }

                            if(gp1.checkToggle(Gamepad.Button.DPAD_RIGHT))
                                outtake.interArm()
                            if(gp1.checkToggle(Gamepad.Button.DPAD_UP))
                                outtake.outtakeServo()
                            if(gp2.checkToggle(Gamepad.Button.Y))
                                outtake.stop()
                            if(gp2.checkToggle(Gamepad.Button.DPAD_DOWN)) {
                                outtake.intakeServo()
                                outtake.closeServoRotation()
                            }
                            outtake.printPosition(telemetry)
                            if(gp2.checkToggle(Gamepad.Button.DPAD_LEFT))
                                outtake.GoToDown()
                            if(gp2.checkToggle(Gamepad.Button.DPAD_RIGHT))
                                outtake.GoToUp()
                            if(gp2.checkToggle(Gamepad.Button.X))
                                outtake.GoToLowJunction()
                            telemetry.update()
                            if(gp2.checkToggle(Gamepad.Button.DPAD_UP))
                                outtake.GoDownSlider()
                            if(gp2.checkToggle(Gamepad.Button.Y))
                                outtake.pentruTheo()

                            if(gp2.right_trigger>0.0 && !rightTriggerIsPressed)
                            {
                                rightTriggerIsPressed = true
                                outtake.GoToUp()
                            }  else if (gp2.right_trigger < 0.01)
                                rightTriggerIsPressed = false

                            if(gp2.left_trigger>0.0 && !leftTriggerIsPressed)
                            {
                                leftTriggerIsPressed = true
                                outtake.GoToDown()
                            } else if(gp2.left_trigger<0.1)
                                leftTriggerIsPressed = false
                        }
                    }

                    ///The direction in which the robot is translating
                    private val direction: Double
                        get() {
                            val x = -gamepad1.left_stick_x.toDouble()  // +
                            val y = +gamepad1.left_stick_y.toDouble() // -

                            return atan2(y, x) / Math.PI * 180.0 - 90.0
                        }

                    /// Rotation around the robot's Z axis.
                    private val rotation: Double
                        get() = -gamepad1.right_stick_x.toDouble()  // -

                    /// Translation speed.
                    private val speed: Double
                        get() {
                            val x = gamepad1.left_stick_x.toDouble() //+
                            val y = gamepad1.left_stick_y.toDouble() //+

                            return Math.sqrt((x * x) + (y * y))
                        }
                }
        val DUMP_TIME_MEDIUM = 0.9

        var isReleased = false
        waitForStart()
        while(opModeIsActive())
        {

            val power = speed
            val rotPower = rotation

            hw.motors.move(direction,power,rotPower)
            outtake.printPosition(telemetry)
            //outtake.closeSliderButton(telemetry)
            //Intake forward and backward

            //State of sliders for smooth movement
            when(liftState)
            {
                LiftState.LIFT_START -> {
                    if(gp2.checkToggle(Gamepad.Button.RIGHT_BUMPER))
                    {
                        outtake.holdCone()
                        outtake.openSlider()
                        liftState = LiftState.LIFT_EXTEND_UP
                    }
                    if(gp2.checkToggle(Gamepad.Button.B))
                    {
                        outtake.holdCone()
                        outtake.openMidSlider()
                        liftState = LiftState.LIFT_EXTEND_MEDIUM
                    }
                    /*   if(gp2.checkToggle(Gamepad.Button.X))
                       {
                           outtake.holdCone()
                           outtake.openLowSlider()
                           liftState = LiftState.LIFT_EXTEND_LOW
                       } */
                }

                /*   LiftState.LIFT_EXTEND_LOW -> {
                       if(outtake.getSliderPositionRelativeToPosLow() < 50)
                       {
                           outtake.releaseArm()
                           outtake.openServoRotation()
                           liftState = LiftState.LIFT_LOW
                       }
                   } */


                LiftState.LIFT_EXTEND_MEDIUM -> {
                    if(outtake.getSliderPositionRelativeToPosMedium() < 100)
                    {
                        outtake.releaseArm()
                        outtake.openServoRotation()
                        liftState = LiftState.LIFT_MEDIUM
                    }
                }

                LiftState.LIFT_EXTEND_UP -> {
                    if(outtake.getSliderPositionRelativeToPosHigh() < 1700)
                    {
                        outtake.releaseArm()
                        outtake.openServoRotation()
                        liftState = LiftState.LIFT_UP
                    }
                }

                LiftState.LIFT_LOW -> {
                    if(gp2.checkToggle(Gamepad.Button.LEFT_BUMPER))
                    {
                        outtake.releaseCone()
                        isReleased = false
                        outtake.closeServoRotation()
                        /*outtake.interArm()
                        sleep(300)*/
                        outtake.closeArm()
                        liftTimer.reset()
                        liftState = LiftState.LIFT_DUMP_LOW
                    }
                    if(gp2.checkToggle(Gamepad.Button.RIGHT_BUMPER))
                    {
                        outtake.holdCone()
                        outtake.openSlider()
                        liftState = LiftState.LIFT_EXTEND_UP
                    }
                    if(gp2.checkToggle(Gamepad.Button.B))
                    {
                        outtake.holdCone()
                        outtake.openMidSlider()
                        liftState = LiftState.LIFT_EXTEND_MEDIUM
                    }
                    /*   if(gp2.checkToggle(Gamepad.Button.X))
                       {
                           outtake.holdCone()
                           outtake.openLowSlider()
                           liftState = LiftState.LIFT_EXTEND_LOW
                       } */
                }

                LiftState.LIFT_MEDIUM -> {
                    if(gp2.checkToggle(Gamepad.Button.LEFT_BUMPER))
                    {
                        outtake.releaseCone()
                        isReleased = false
                        outtake.closeServoRotation()
                        /*outtake.interArm()
                        sleep(300)*/
                        outtake.closeArm()
                        liftTimer.reset()
                        liftState = LiftState.LIFT_DUMP_MEDIUM
                    }
                    if(gp2.checkToggle(Gamepad.Button.RIGHT_BUMPER))
                    {
                        outtake.holdCone()
                        outtake.openSlider()
                        liftState = LiftState.LIFT_EXTEND_UP
                    }
                    if(gp2.checkToggle(Gamepad.Button.B))
                    {
                        outtake.holdCone()
                        outtake.openMidSlider()
                        liftState = LiftState.LIFT_EXTEND_MEDIUM
                    }
                    /*  if(gp2.checkToggle(Gamepad.Button.X))
                     {
                          outtake.holdCone()
                          outtake.openLowSlider()
                          liftState = LiftState.LIFT_EXTEND_LOW
                      } */
                }

                LiftState.LIFT_UP -> {
                    if(gp2.checkToggle(Gamepad.Button.LEFT_BUMPER))
                    {
                        outtake.holdCone()
                        isReleased = false
                        outtake.closeServoRotation()
                        /*outtake.interArm()
                        sleep(300)*/
                        outtake.closeArm()
                        liftTimer.reset()
                        liftState = LiftState.LIFT_DUMP_UP
                    }
                    if(gp2.checkToggle(Gamepad.Button.RIGHT_BUMPER))
                    {
                        outtake.holdCone()
                        outtake.openSlider()
                        liftState = LiftState.LIFT_EXTEND_UP
                    }
                    if(gp2.checkToggle(Gamepad.Button.B))
                    {
                        outtake.holdCone()
                        outtake.openMidSlider()
                        liftState = LiftState.LIFT_EXTEND_MEDIUM
                    }
                    /* if(gp2.checkToggle(Gamepad.Button.X))
                      {
                          outtake.holdCone()
                          outtake.openLowSlider()
                          liftState = LiftState.LIFT_EXTEND_LOW
                      } */
                }

                LiftState.LIFT_DUMP_LOW -> {
                    if(liftTimer.seconds() >= DUMP_TIME_LOW)
                    {
                        outtake.closeSlider()
                        liftState = LiftState.LIFT_RETRACT
                    }
                }

                LiftState.LIFT_DUMP_MEDIUM -> {
                    if(liftTimer.seconds() >= DUMP_TIME_MEDIUM)
                    {
                        outtake.closeSlider()
                        liftState = LiftState.LIFT_RETRACT
                    }
                }

                LiftState.LIFT_DUMP_UP -> {
                    if(liftTimer.seconds() >= DUMP_TIME_UP)
                    {
                        outtake.closeSlider()
                        liftState = LiftState.LIFT_RETRACT
                    }
                }

                LiftState.LIFT_RETRACT -> {
                    liftState = LiftState.LIFT_START
                }

                else -> {
                    liftState = LiftState.LIFT_START
                }
            }

            if(gp2.checkToggle(Gamepad.Button.A))
            {
                isReleased = if(!isReleased) {
                    outtake.releaseCone()
                    true
                } else {
                    outtake.holdCone()
                    false
                }
            }

            if(gp2.checkToggle(Gamepad.Button.DPAD_UP))
                outtake.interArm()
            if(gp1.checkToggle(Gamepad.Button.DPAD_UP))
                outtake.releaseArm()
            if(gp2.checkToggle(Gamepad.Button.Y))
                outtake.stop()
            if(gp2.checkToggle(Gamepad.Button.DPAD_DOWN)) {
                outtake.closeArm()
                outtake.closeServoRotation()
            }
            outtake.printPosition(telemetry)
            if(gp2.checkToggle(Gamepad.Button.DPAD_LEFT))
                outtake.GoToDown()
            if(gp2.checkToggle(Gamepad.Button.DPAD_RIGHT))
                outtake.GoToUp()
            if(gp2.checkToggle(Gamepad.Button.X))
                outtake.GoToLowJunction()
            telemetry.update()
            if(gp2.checkToggle(Gamepad.Button.DPAD_UP))
                outtake.GoDownSlider()
            if(gp2.checkToggle(Gamepad.Button.Y))
                outtake.pentruTheo()
        }
    }

    ///The direction in which the robot is translating
    private val direction: Double
        get() {
            val x = -gamepad2.left_stick_x.toDouble()  // +
            val y = +gamepad2.left_stick_y.toDouble() // -

            return atan2(y, x) / Math.PI * 180.0 - 90.0
        }

    /// Rotation around the robot's Z axis.
    private val rotation: Double
        get() = -gamepad2.right_stick_x.toDouble()  // -

    /// Translation speed.
    private val speed: Double
        get() {
            val x = gamepad2.left_stick_x.toDouble() //+
            val y = gamepad2.left_stick_y.toDouble() //+

            return Math.sqrt((x * x) + (y * y))
        }
}
*/
