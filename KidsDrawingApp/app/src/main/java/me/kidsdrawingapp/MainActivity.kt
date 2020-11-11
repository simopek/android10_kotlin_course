package me.kidsdrawingapp

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_brush_size.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.kidsdrawingapp.uicomponents.ProgressBarManager
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var selectedColorBrushImageButton: ImageButton? = null
    private val LOG_TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedColorBrushImageButton = this.brushColorSelectorLayout[0] as ImageButton
        selectedColorBrushImageButton!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selected_palette))

        brushSelectorDialogButton.setOnClickListener {
            showBrushSelectorDialog()
        }

        galleryImageSelectorButton.setOnClickListener {
            if (isReadExternalStorageAllowed()) {

                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, GALLERY_IMAGES_REQUEST_CODE)
            } else {
                requestStoragePermission()
            }
        }

        undoButton.setOnClickListener {
            onUndoButtonClick()
        }

        saveButton.setOnClickListener {
            onSaveButtonClick()
        }

        drawingView.setBrushSize(10f)
    }

    private fun onSaveButtonClick() {

        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/png"
            putExtra(Intent.EXTRA_TITLE, "new image")
        }
        startActivityForResult(intent, NEW_IMAGE_REQUEST_CODE)
    }

    private fun showBrushSelectorDialog() {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_brush_size)
        dialog.setTitle("Brush selector")

        dialog.smallBrushSelector.setOnClickListener {
            drawingView.setBrushSize(5f)
            dialog.dismiss()
        }

        dialog.mediumBrushSelector.setOnClickListener {
            drawingView.setBrushSize(20f)
            dialog.dismiss()
        }

        dialog.largeBrushSelector.setOnClickListener {
            drawingView.setBrushSize(40f)
            dialog.dismiss()
        }

        dialog.show()
    }

    fun onColorBrushImageButtonClick(view: View) {

        if (view != selectedColorBrushImageButton) {

            Log.d(LOG_TAG, "selected color brush button")

            val btn = view as ImageButton
            // we read the "tag" attribute
            val btnColor = btn.tag.toString()

            Log.d(LOG_TAG, "the color is $btnColor")

            // we update the drawing view's color
            drawingView.setPaintColor(btnColor)
            // we update the image of the buttons: the new selected and the old selected
            btn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selected_palette))
            selectedColorBrushImageButton!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.normal_palette))
            selectedColorBrushImageButton = btn
        }
    }

    private fun requestStoragePermission() {

        // this check is useful to show some educational message if Android thinks it's the case
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).toString())) {
            Toast.makeText(this, "The access to the external storage is needed to read and write images", Toast.LENGTH_SHORT)
                .show()
        }
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == STORAGE_PERMISSION_CODE) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "The access to the external storage HAS BEEN GRANTED", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "The access to the external storage HAS BEEN DENIED", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun isReadExternalStorageAllowed(): Boolean {

        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_IMAGES_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            try {

                if (data != null && data.data != null) {

                    drawingViewBackgroundImage.visibility = View.VISIBLE
                    drawingViewBackgroundImage.setImageURI(data.data)
                } else {
                    Toast.makeText(this, "Invalid image file", Toast.LENGTH_SHORT)
                        .show()
                }

            } catch (e: Exception) {
                Log.e(ACTIVITY_TAG, e.localizedMessage, e)
            }
        } else if (requestCode == NEW_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            data?.data?.also { uri ->

                val fd = this.contentResolver.openFileDescriptor(uri, "w")
                fd?.let {
                    saveImage(fd)
                }
            }
        }
    }

    private fun saveImage(fileDescriptor: ParcelFileDescriptor) {

        if (fileDescriptor == null) {

            Toast.makeText(this, "Invalid image file", Toast.LENGTH_SHORT)
                .show()
            return
        }

        GlobalScope.launch(Dispatchers.Main) {
            fileDescriptor.use {
                drawingView.writeImageTo(
                    ProgressBarManager(progressBarLayout, drawingViewFrameLayout, brushColorSelectorLayout, actionButtonsLayout), it)
            }
        }
    }

    private fun onUndoButtonClick() {
        drawingView.undoLastPath()
    }
    

    companion object {
        private const val STORAGE_PERMISSION_CODE = 1
        private const val GALLERY_IMAGES_REQUEST_CODE = 2
        private const val NEW_IMAGE_REQUEST_CODE = 3
        private const val ACTIVITY_TAG = "MainActivity"
    }
}