package com.shevy.androidcourse

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.shevy.androidcourse.databinding.ActivityMainBinding
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var volumeSeekBar: SeekBar
    lateinit var timerSeekBar: SeekBar
    lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

        volumeSeekBar = binding.seekBarVolume
        volumeSeekBar.max = maxVolume
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                //TODO("Not yet implemented")
            }
        })
        mediaPlayer = MediaPlayer.create(this, R.raw.music)

        timerSeekBar = binding.seekBarTimer
        timerSeekBar.max = mediaPlayer.duration
        timerSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                //TODO("Not yet implemented")
            }
        })

        Timer().schedule(object : TimerTask() {
            override fun run() {
                timerSeekBar.progress = mediaPlayer.currentPosition
            }
        }, 0, 1000)

        binding.apply {
            playImageView.setOnClickListener {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    playImageView.setImageResource(R.drawable.ic_play)
                } else {
                    mediaPlayer.start()
                    playImageView.setImageResource(R.drawable.ic_pause)
                }
            }

            nextImageView.setOnClickListener {

            }

            previousImageView.setOnClickListener {

            }
        }
    }
}

