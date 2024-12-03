package com.example.baseproject.presentation

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.baseproject.R

fun Context.lpAlert(setup: Dialog.() -> Unit) {
    val dialog =
        Dialog(this, R.style.AlertDialogSlideAnim).apply {
            setContentView(R.layout.layout_custom_alert_dialog)
            window?.let {
                it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                it.setGravity(Gravity.CENTER)
            }
        }
    setup(dialog)
    dialog.show()
}

fun Dialog.title(title: String) {
    this.findViewById<TextView>(R.id.tvTitleAlert)?.apply {
        text = title
    }
}

/**
 * This setup will not change font of message.
 */
fun Dialog.message(msg: String) {
    this.findViewById<TextView>(R.id.tvMsgAlert)?.apply {
        text = msg
    }
}

/**
 * This is exception for only message. Need change font in this case.
 * This case will hidden title message.
 * @Font : Roboto
 */
fun Dialog.notification(msg: String) {
    this.findViewById<TextView>(R.id.tvTitleAlert)?.visibility = View.GONE
    this.findViewById<TextView>(R.id.tvMsgAlert)?.apply {
        text = msg
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            typeface = ResourcesCompat.getFont(context, R.font.roboto_bold)
        }
    }
}

fun Dialog.positiveSetup(
    name: String? = null,
    click: () -> Unit,
) {
    this.findViewById<Button>(R.id.positiveButton).apply {
        name?.let { vl -> text = vl }
        setOnClickListener {
            click.invoke()
            this@positiveSetup.dismiss()
        }
    }
}

fun Dialog.negativeSetup(
    name: String? = null,
    click: () -> Unit,
) {
    this.findViewById<Button>(R.id.negativeButton).apply {
        name?.let { vl ->
            visibility = View.VISIBLE
            text = vl
        } ?: run {
            visibility = View.GONE
        }
        setOnClickListener {
            click.invoke()
            this@negativeSetup.dismiss()
        }
    }
}
