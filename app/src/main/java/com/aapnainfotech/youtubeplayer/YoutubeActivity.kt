package com.aapnainfotech.youtubeplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerView

//https://www.youtube.com/watch?v=wTtDA0YM6Rk
//https://www.youtube.com/watch?v=jWmCcRCU-_w&list=PLillGF-RfqbbgNtw__dGpFqx6AebRcjSm

const val YOUTUBE_VIDEO_ID = "MKaMwWlJqT8"
const val YOUTUBE_PLAYLIST = "PLillGF-RfqbbgNtw__dGpFqx6AebRcjSm"

class YoutubeActivity : YouTubeBaseActivity() , OnInitializedListener {

    val TAG = "YouTubePlayerActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube)
//        val layout = findViewById<ConstraintLayout>(R.id.youtube_activity)

        val layout = layoutInflater.inflate(R.layout.activity_youtube,null) as ConstraintLayout
        setContentView(layout)

        //ADDING WIDGET DYNAMICALLY

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)

    }

    override fun onInitializationSuccess(
        provider : YouTubePlayer.Provider?,
        youTubePlayer : YouTubePlayer?,
        wasRestored : Boolean
    ) {
       Log.d(TAG,"onIntializationSuccess: Provider is ${provider?.javaClass}")
       Log.d(TAG,"onIntializationSuccess: youTubePlayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this,"Initialized Youtube Player successfully",Toast.LENGTH_SHORT).show()

        youTubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
        youTubePlayer?.setPlaybackEventListener(playbackEventListener)

        if (!wasRestored){
            youTubePlayer?.cueVideo(YOUTUBE_VIDEO_ID)
        }else{
            youTubePlayer?.play()
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0

        if (youTubeInitializationResult?.isUserRecoverableError == true){

            youTubeInitializationResult.getErrorDialog(this , REQUEST_CODE).show()

        }else{
            val errorMessage = "There was an error initializing the YoutubePlayer ($youTubeInitializationResult)"
            Toast.makeText(this , errorMessage , Toast.LENGTH_LONG).show()
        }

    }

    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener{
        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity,"Good ! Video is playing! ",Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
           Toast.makeText(this@YoutubeActivity,"Video is paused! ",Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
            Toast.makeText(this@YoutubeActivity,"Video stopped !",Toast.LENGTH_SHORT).show()
        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onSeekTo(p0: Int) {

        }

    }

    private val playerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener{
        override fun onLoading() {

        }

        override fun onLoaded(p0: String?) {

        }

        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity,"Don't click the ad to make the video creator rich !",Toast.LENGTH_SHORT).show()
        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity,"Video has started !",Toast.LENGTH_SHORT).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity,"Congratulations ! you have completed the video !",Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }

    }
}