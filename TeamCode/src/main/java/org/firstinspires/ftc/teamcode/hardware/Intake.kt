package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.hardware.rev.RevTouchSensor
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.TouchSensor

import org.firstinspires.ftc.robotcore.external.Telemetry
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.util.*
import kotlin.math.absoluteValue
import com.qualcomm.robotcore.hardware.DistanceSensor


/**
 * intake subsystem.
 *
 * This class controls the hardware for placing freight
 */

class Intake(hwMap: HardwareMap) {
    companion object {
        //SERVOS
        const val intakeClawOpenPosition = 0.80
        const val intakeClawHoldPosition = 0.30


    }
    var isOpen:Boolean = false
    //val touchSensor = hwMap.get(RevTouchSensor::class.java,"touchSensor") ?: throw Exception("Failed to find RevTouchSensor touchSensor")

    var intakePosition: Int = 0



    //servos
  //  private val intakeSliderServoRight = hwMap.servo["intakeSliderServoRight"] ?: throw Exception("Failed to find servo intakeSliderServoRight")
  //  private val intakeSliderServoLeft = hwMap.servo["intakeSliderServoLeft"] ?: throw Exception("Failed to find servo intakeSliderServoLeft")
   // private val intakeServoClaw = hwMap.servo["intakeServoClaw"] ?: throw Exception("Failed to find servo intakeServoClaw")

    init {


        intakePosition = 0



    }


   /* fun releaseIntakePixel() {
        isOpen = true
        intakeServoClaw.position = intakeClawOpenPosition
    }

    fun holdIntakePixel() {
        isOpen = false
        intakeServoClaw.position = intakeClawHoldPosition
    }

    fun toggleIntakePixel() {
        if (isOpen) {
            holdIntakePixel()
        } else {
            releaseIntakePixel()
        }
    }
    */












}
