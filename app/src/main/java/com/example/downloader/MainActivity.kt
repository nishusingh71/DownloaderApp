
package com.example.downloader

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        val inputLink = findViewById<EditText>(R.id.editLink)
        val downloadButton = findViewById<Button>(R.id.downloadButton)
        val imageList = ArrayList<SlideModel>()
        imageList.add(
            SlideModel(
                R.drawable.pic2,
                "Download  Video by link.",
                ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                R.drawable.pic1,
                "Download image by link.",
                ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                R.drawable.pic4, "Download Mp3",
                ScaleTypes.CENTER_CROP)
        )

        imageSlider.setImageList(imageList)
        downloadButton.setOnClickListener {
            val link = inputLink.text.toString()
            val request = DownloadManager.Request(Uri.parse(link))
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            request.setTitle("Downloading Start......")
            request.setDescription("Download here")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                request.allowScanningByMediaScanner()
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            }
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS, "${System.currentTimeMillis()}"
            )
            val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)
        }

    }
}