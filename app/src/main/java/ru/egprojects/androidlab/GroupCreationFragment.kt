package ru.egprojects.androidlab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_group_creation.view.*

class GroupCreationFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_group_creation, container, false).apply {
            btn_page5.setOnClickListener {
                val fragment = GroupFragment.newInstance(
                        et_page5_field1.text.toString(),
                        et_page5_field2.text.toString(),
                        et_page5_field3.text.toString()
                )
                activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.nav_host_fragment, fragment)
                        ?.addToBackStack(null)
                        ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        ?.commit()
            }
        }
    }
}