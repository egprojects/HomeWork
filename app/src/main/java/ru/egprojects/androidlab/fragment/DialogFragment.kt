package ru.egprojects.androidlab.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import ru.egprojects.androidlab.R

class DialogFragment(private val callback: DialogCallback) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false).apply {
            tv_dialog_title.text = getString(R.string.dialog_title_add_anime)
            btn_dialog_ok.setOnClickListener {
                val posData = et_dialog_pos.text.toString()
                val index = if (posData.isEmpty()) 0 else posData.toInt()
                callback.onSubmit(
                        index - 1,
                        et_dialog_title.text.toString(),
                        et_dialog_genre.text.toString(),
                        et_poster_poster.text.toString(),
                        et_dialog_desc.text.toString()
                )
                dismiss()
            }
            btn_dialog_cancel.setOnClickListener { dismiss() }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_NoActionBar)
    }

    @FunctionalInterface
    interface DialogCallback {
        fun onSubmit(pos: Int, title: String, genre: String, posterUrl: String, desc: String)
    }

}
