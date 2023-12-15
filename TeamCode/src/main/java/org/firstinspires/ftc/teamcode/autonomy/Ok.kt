package org.firstinspires.ftc.teamcode.autonomy;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.util.Angle;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
@Autonomous
class Ok : LinearOpMode() {
    private val drive = SampleMecanumDrive(hardwareMap)
    private val xyP = 1.0
    private val headingP = 1.0

    override fun runOpMode() {
        waitForStart()
        while (opModeIsActive()) {
            lockTo(Pose2d(0.0, 0.0,0.0))
            drive.update()
        }
    }

    private fun lockTo(targetPos: Pose2d) {
        val currPos=drive.poseEstimate
        val difference = targetPos.minus(currPos)
        val xy = difference.vec().rotated(-currPos.heading)
        val heading = Angle.normDelta(targetPos.heading) - Angle.normDelta(currPos.heading)
        drive.setWeightedDrivePower(Pose2d(xy.times(xyP), heading * headingP))
    }
}