package com.example.popularlibraries.converterJpegToPng

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import io.reactivex.rxjava3.core.Single
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeUnit

class ConverterJpegToPng(
    private val context: Context
) {

    private fun loadJpeg(uri: Uri): Single<ImageDecoder.Source> {
        return Single.create {
            try {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                it.onSuccess(source)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    private fun decodePic(imageDecoderSource: ImageDecoder.Source): Single<Bitmap> {
        return Single.create {
            try {
                val bitmap = ImageDecoder.decodeBitmap(imageDecoderSource)
                it.onSuccess(bitmap)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    private fun convertJpegToPngAndSaveIt(bitmap: Bitmap, fileName: String): Single<Bitmap> {
        return Single.create {
            val file = File(context.cacheDir, fileName)
            file.createNewFile()

            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()

            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(byteArray)
            fileOutputStream.flush()
            fileOutputStream.close()

            it.onSuccess(bitmap)
        }
    }


    fun convert(uri: Uri): Single<Bitmap> {
        return loadJpeg(uri = uri)
            .delay(5, TimeUnit.SECONDS)
            .flatMap {
                decodePic(imageDecoderSource = it)
            }
            .delay(5, TimeUnit.SECONDS)
            .flatMap {
                convertJpegToPngAndSaveIt(it, "newPic.png")
            }

    }
}