package org.firstinspires.ftc.teamcode.autonomy

import com.acmerobotics.dashboard.config.Config
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.teamcode.OpenCV.AprilTagDetectionPipeline
import org.firstinspires.ftc.teamcode.Subsystems.TeamElementDetection.TeamElementSubsystem
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive
import org.openftc.apriltag.AprilTagDetection
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory
import java.util.Locale

@Config
abstract class AutoBase : org.firstinspires.ftc.teamcode.OpMode() {
    val drive: SampleMecanumDrive by lazy {
        SampleMecanumDrive(hardwareMap)
    }

    val intakePower = -1.0

    val seen = false
    var aprilTagDetectionPipeline: AprilTagDetectionPipeline? = null

    val FEET_PER_METER = 3.28084

    val fx = 578.272
    val fy = 578.272
    val cx = 402.145
    val cy = 221.506

    val tagsize = 0.166

    val left = 11
    val mid = 19
    val right = 14

    var element_zone = 1

    private var teamElementDetection: TeamElementSubsystem? = null

    var togglePreview = true


    override fun preInit() {


        telemetry.addData("Object Creation", "Start")
        telemetry.update()

        teamElementDetection = TeamElementSubsystem(hardwareMap)

        telemetry.addData("Object Creation", "Done")
        telemetry.update()


    }

    var tagOfInterest: AprilTagDetection? = null

    var curAlliance = "red"
    override fun preInitLoop() {


    }


        /**
         * Camera related code
         * -------------------------------------------------------------
         * */

        val webcam: OpenCvCamera by lazy {
            val cameraMonitorViewId = hardwareMap.appContext.resources.getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.packageName)
            OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName::class.java, "Webcam 1"), cameraMonitorViewId)
        }



}