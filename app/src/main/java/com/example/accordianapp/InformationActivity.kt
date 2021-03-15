package com.example.accordianapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.VideoView

class InformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        //added code for video
//        val videoView = findViewById<View>(R.id.videoView) as VideoView //casting to VideoView is not Strictly required above API level 26
//        videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.videoclip) //set the path of the video that we need to use in our VideoView
//        videoView.start() //start() method of the VideoView class will start the video to play

//        val mediaController = MediaController(this)
//        //link mediaController to videoView
//        //link mediaController to videoView
////        mediaController.setAnchorView(videoView)
//        //allow mediaController to control our videoView
//        //allow mediaController to control our videoView
//        videoView.setMediaController(mediaController)
//        //ends here
    }
}