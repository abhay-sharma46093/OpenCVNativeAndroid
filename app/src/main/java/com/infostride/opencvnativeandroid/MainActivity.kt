package com.infostride.opencvnativeandroid

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.infostride.opencvnativeandroid.databinding.ActivityMainBinding
import java.lang.Float.max

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    private lateinit var binding: ActivityMainBinding

    external fun flipImage(bitmapIn: Bitmap, bitmapOut: Bitmap)
    external fun applyBlur(bitmapIn: Bitmap, bitmapOut: Bitmap, sigma: Float)

    var srcBitmap: Bitmap? = null
    var dstBitmap: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        srcBitmap = BitmapFactory.decodeResource(this.resources, R.drawable.photography)
        dstBitmap = srcBitmap!!.copy(srcBitmap!!.config, true)
        binding.imageView.setImageBitmap(dstBitmap)

        binding.sldSigma.setOnSeekBarChangeListener(this)
    }

    private fun doBlur() {
        val sigma = max(0.1F, binding.sldSigma.progress / 10F)
        this.applyBlur(srcBitmap!!, dstBitmap!!, sigma)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        this.doBlur()
    }

    fun flipButtonClicked(view: View) {
        this.flipImage(srcBitmap!!, srcBitmap!!)
        this.doBlur()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}


    companion object {

        init {
            System.loadLibrary("opencvnativeandroid")
        }
    }
}