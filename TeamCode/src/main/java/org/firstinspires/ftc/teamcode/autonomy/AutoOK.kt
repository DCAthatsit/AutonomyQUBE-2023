
package org.firstinspires.ftc.teamcode.autonomy

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.hardware.Hardware
import org.firstinspires.ftc.teamcode.waitMillis


@Autonomous
@Config
class AutoOK: AutoBase() {
    // asta e dreapta jos si stanga sus terenul nostru dinspre masa cu unelte inspre chestia cu awards
    private val n : Int = 5
    private val startPose = Pose2d(0.0, 0.0, Math.toRadians(0.0))

    /*** Primul Pixel ***/
    private val startDreapta = Vector2d(16.7,-12.7)
    private val startCentru = Vector2d(26.3,-0.2)
    private val startStanga = Vector2d(29.0,0.0)

    /***Parcare***/

    private val parcareStanga = Pose2d(0.6,-44.0,Math.toRadians(85.0))
    private val parcareDreapta = Vector2d(0.6,-44.0,)

    /***Primul Pixel Departe  ***/
    private val startStangaDeparte = Vector2d(14.2,10.8)

    /*** Parcare departe ***/

    private val parcarCentru = Vector2d(6.4,-88.2)





    var ok  = 0
    var ok1 =1
    val i = 1
    override fun preInit()  {
        super.preInit()
        telemetry.addLine("Initializing...")
        telemetry.update()
        drive.poseEstimate = startPose
    }

    override fun Hardware.run() {
        outtake.reverseIntake()
        outtake.openGateAuto()
        if(ok==3) {
            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .lineToConstantHeading(startDreapta)
                            .build()
            )
                outtake.openSlider(0.4)
            waitMillis(4000)
                outtake.closeSlider(0.0)
            waitMillis(200)
            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            //.splineToConstantHeading(finalPose,Math.toRadians(0.0)) sau {
                            .lineToConstantHeading(parcareDreapta)
                            //     si
                            //   }

                            .build()


            )

            val (x, y, heading) = drive.poseEstimate
            telemetry.addData("x", x)
            telemetry.addData("y", y)

            telemetry.addData("heading", heading)
            telemetry.update()
            waitMillis(800)

        }
        else if(ok==2) {
            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .lineToConstantHeading(startCentru)
                            .build()
            )
            outtake.openSlider(0.3)
            waitMillis(3000)
            outtake.closeSlider()
            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            //.splineToConstantHeading(finalPose,Math.toRadians(0.0)) sau {
                            .lineToConstantHeading(parcareDreapta)
                            //     si
                            //   }

                            .build()


            )


        }else if(ok==1) {
            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .lineToConstantHeading(startStanga)
                            .build()
            )

            drive.followTrajectorySequence(
                            drive.trajectorySequenceBuilder(drive.poseEstimate)
                        .turn(Math.toRadians(95.0))
                            .build()
            )
            outtake.openSlider(0.3)
            waitMillis(1000)
            outtake.closeSlider()


            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .lineToLinearHeading(parcareStanga)
                            .build()
            )





            val (x, y, heading) = drive.poseEstimate
            telemetry.addData("x", x)
            telemetry.addData("y", y)

            telemetry.addData("heading", heading)
            telemetry.update()

        }


        /*** Auto Departe ***/
        if(ok1==1)
        {
            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .lineToConstantHeading(startStangaDeparte)
                            .build()
            )
            outtake.openSlider(0.3)
            waitMillis(3000)
            outtake.closeSlider()

            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .back(14.2)
                            .build()
            )

            drive.followTrajectorySequence(
                    drive.trajectorySequenceBuilder(drive.poseEstimate)
                            .turn(Math.toRadians(95.0))
                            .build()
            )
            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .back(97.0)
                            .build()
            )



        }
        else
        if(ok1==2)
        {
            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .forward(26.3)
                            .build()
            )
            outtake.openSlider(0.3)
            waitMillis(3000)
            outtake.closeSlider()

            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .back(26.3)
                            .build()
            )
            drive.followTrajectorySequence(
                    drive.trajectorySequenceBuilder(drive.poseEstimate)
                            .turn(Math.toRadians(95.0))
                            .build()
            )
            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .back(88.0)
                            .build()
            )

        }
        else if (ok1==3)
        {
            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .forward(29.0)
                            .build()
            )
            drive.followTrajectorySequence(
                    drive.trajectorySequenceBuilder(drive.poseEstimate)
                            .turn(Math.toRadians(-95.0))
                            .build()
            )
            outtake.openSlider(0.3)
            waitMillis(3000)
            outtake.closeSlider()

            drive.followTrajectorySequence(
                    drive.trajectorySequenceBuilder(drive.poseEstimate)
                            .turn(Math.toRadians(95.0))
                            .build()
            )

            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .back(28.0)
                            .build()
            )
            drive.followTrajectorySequence(
                    drive.trajectorySequenceBuilder(drive.poseEstimate)
                            .turn(Math.toRadians(95.0))
                            .build()
            )
            drive.followTrajectory(
                    drive.trajectoryBuilder(drive.poseEstimate)
                            .back(88.0)
                            .build()
            )









        }






    }
}