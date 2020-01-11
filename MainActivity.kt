package com.igotit.opencv1

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.igotit.opencv1.extensions.*
import kotlinx.android.synthetic.main.activity_main.*
import org.opencv.android.OpenCVLoader
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private val imageBitmap by lazy { (ContextCompat.getDrawable(this,R.drawable.cat) as BitmapDrawable).bitmap }
    private var counter =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // setSupportActionBar(toolbar)
        OpenCVLoader.initDebug()
        //applyGrayScale()
        imageBtn.setOnClickListener {
            counter++
            counter = Random.nextInt(1,100)
            if (counter %7 == 0)
                applyGrayScale()
            else if (counter %7 == 1)
                applyThreshold()
            else if (counter %7 == 2)
                applyCannyEdge()
            else if (counter %7 == 3)
                applyAdaptiveThreshold()
            else if (counter %7 == 4)
                applyGaussianBlur()
            else
                resetImage()

        }
    }

    private fun imageFilter(){

    }
    private fun applyGrayScale(){
        val mat =  Mat()
       mat.toGray(imageBitmap)
       image.setImageBitmap(mat.toBitmap())
       mat.release()
    }
    private fun applyCannyEdge(){
        val mat = Mat()
        mat.canny(imageBitmap){ image.setImageBitmap(it)}
    }
    private fun applyGaussianBlur(){
        val mat = Mat()
        mat.gaussianBlur(imageBitmap) { image.setImageBitmap(it) }
        mat.release()
    }
    private fun applyThreshold() {
        val mat = Mat()
        mat.threshold(imageBitmap) { image.setImageBitmap(it) }
    }
    private fun applyAdaptiveThreshold() {
        val mat = Mat()
        mat.adaptiveThreshold(imageBitmap) { image.setImageBitmap(it) }
    }
    private fun resetImage() {
        image.setImageBitmap(imageBitmap)
    }
}
