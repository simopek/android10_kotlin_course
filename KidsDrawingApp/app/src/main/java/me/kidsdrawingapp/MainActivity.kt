package me.kidsdrawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_brush_size.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}