package me.kidsdrawingapp

import android.app.Dialog
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_brush_size.*

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

        drawingView.setBrushSize(10f)
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
}