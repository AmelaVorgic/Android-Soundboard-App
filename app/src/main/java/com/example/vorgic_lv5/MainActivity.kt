package com.example.vorgic_lv5

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.vorgic_lv5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private lateinit var mSoundPool: SoundPool
    private var mLoaded: Boolean = false
    var mSoundMap: HashMap<Int, Int> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.loadSounds()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dog.setOnClickListener { playSound(R.raw.dog) }
        binding.baby.setOnClickListener { playSound(R.raw.baby) }
        binding.guitar.setOnClickListener { playSound(R.raw.guitar)}
    }

private fun loadSounds() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.mSoundPool = SoundPool.Builder().setMaxStreams(10).build()
            } else {
                this.mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
            }
            this.mSoundPool.setOnLoadCompleteListener { _, _, _ -> mLoaded = true }
            this.mSoundMap[R.raw.dog] = this.mSoundPool.load(this, R.raw.dog, 1)
            this.mSoundMap[R.raw.baby] = this.mSoundPool.load(this, R.raw.baby, 1)
            this.mSoundMap[R.raw.guitar] = this.mSoundPool.load(this, R.raw.guitar, 1)
        }

    fun playSound(selectedSound: Int) {
        val soundID = this.mSoundMap[selectedSound] ?: 0
        this.mSoundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }

}
