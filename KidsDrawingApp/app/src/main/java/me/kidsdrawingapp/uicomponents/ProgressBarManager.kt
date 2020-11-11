package me.kidsdrawingapp.uicomponents

import android.view.View
import android.widget.ProgressBar

class ProgressBarManager(private val progressBar: View, private vararg val views: View) {

    private fun setViewsVisibility(showProgressBar: Boolean) {

        for (_view in views) {
            _view.visibility = if (showProgressBar) View.INVISIBLE else View.VISIBLE
        }
        progressBar.visibility = if (showProgressBar) View.VISIBLE else View.INVISIBLE
    }

    fun showProgressBar() = setViewsVisibility(true)
    fun hideProgressBar() = setViewsVisibility(false)


}