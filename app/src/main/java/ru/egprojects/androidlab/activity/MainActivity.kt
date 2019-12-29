package ru.egprojects.androidlab.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.fragment.TaskDetailsFragment
import ru.egprojects.androidlab.fragment.TaskInterface
import ru.egprojects.androidlab.fragment.TaskListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = TaskListFragment(object : TaskInterface {
            override fun showDetails(taskId: Int?) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, TaskDetailsFragment.newInstance(taskId))
                        .addToBackStack(null)
                        .commit()
            }
        })
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
    }

}
