package ru.egprojects.androidlab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_group.view.*

class GroupFragment : Fragment() {
    companion object {
        private const val ARG_FIELD1 = "field1"
        private const val ARG_FIELD2 = "field2"
        private const val ARG_FIELD3 = "field3"

        fun newInstance(field1: String, field2: String, field3: String) = GroupFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_FIELD1, field1)
                putString(ARG_FIELD2, field2)
                putString(ARG_FIELD3, field3)
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_group, container, false).apply {
            tv_page6_field1.text = arguments?.getString(ARG_FIELD1)
            tv_page6_field2.text = arguments?.getString(ARG_FIELD2)
            tv_page6_field3.text = arguments?.getString(ARG_FIELD3)
        }
    }
}