package com.aapnainfotech.youtubeplayer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_standalone.*

class StandaloneActivity : AppCompatActivity() ,View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        playVideo_btn.setOnClickListener(this)
        playList_btn.setOnClickListener(this)


    }

    override fun onClick(view: View) {
       val intent = when(view.id){
           R.id.playVideo_btn -> YouTubeStandalonePlayer.createVideoIntent(
               this,getString(R.string.GOOGLE_API_KEY), YOUTUBE_VIDEO_ID ,0 ,true ,false) // autoplay but not in the lightBoxMode
           R.id.playList_btn -> YouTubeStandalonePlayer.createPlaylistIntent(
               this,getString(R.string.GOOGLE_API_KEY), YOUTUBE_PLAYLIST, 0 , 0 ,true ,true) // autoplay in the lightBoxMode
           else -> throw IllegalArgumentException("Undefined button Clicked !")
       }
        startActivity(intent)
    }
}