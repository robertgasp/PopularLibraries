package com.example.popularlibraries.converterJpegToPng

import android.Manifest
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.popularlibraries.databinding.ActivityConverterJpegToPngBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ConverterJpegToPngActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConverterJpegToPngBinding

    private val converter by lazy {
        ConverterJpegToPng(this)
    }

    private var convertImageDisposable: Disposable? = null

    private val convertJpeg =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            it?.let {
                showPic(it)
            }
        }

    private val permission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        when {
            it -> {
                convertJpeg.launch(arrayOf("image/*"))
            }
            !shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                Log.d("Deny", "No permission Manifest.permission.WRITE_EXTERNAL_STORAGE")
            }
            else -> {
                Log.d("Deny", "No permission Manifest.permission.WRITE_EXTERNAL_STORAGE")
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConverterJpegToPngBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConvertJpegToPng.setOnClickListener {
            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.d("Deny", "No permission Manifest.permission.WRITE_EXTERNAL_STORAGE")
            } else {
                permission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }

        binding.btnCancel.setOnClickListener {
            convertImageDisposable?.dispose()
            convertImageDisposable = null
            binding.btnCancel.visibility = View.GONE
        }
    }

    private fun showPic(uri: Uri) = with(binding) {
        btnCancel.visibility = View.VISIBLE
        convertImageDisposable = converter.convert(uri)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    loadedJpegPic.setImageBitmap(it)
                    textSuccess.visibility = View.VISIBLE
                    btnCancel.visibility = View.GONE
                },
                {
                    Log.d("RxJava", "Error:Image Converter: $it")
                }
            )
    }

}