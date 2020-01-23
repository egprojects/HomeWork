package ru.egprojects.androidlab.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_task_details.view.*
import kotlinx.coroutines.*
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.model.AppDatabase
import ru.egprojects.androidlab.model.Task
import ru.egprojects.androidlab.model.TaskDao
import java.util.*
import kotlin.coroutines.CoroutineContext

class TaskDetailsFragment : Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Default
    private lateinit var taskDao: TaskDao
    private var task: Task? = null
    private var taskLoader: Deferred<Task>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) taskLoader = async {
            taskDao.getById(arguments!!.getInt(ARG_TASK_ID))
        }
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.title_task_details)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        taskDao = AppDatabase(context).taskDao()
        taskLoader?.start()
    }

    @ExperimentalCoroutinesApi
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_task_details, container, false)?.apply {
        task = taskLoader?.getCompleted()
        task?.apply {
            et_task_details_title.setText(title)
            et_task_details_date.setText(date.toString())
            et_task_details_desc.setText(description)
            cb_task_details_is_done.isChecked = isDone
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_task_details, menu)
        menu.findItem(R.id.action_done).setOnMenuItemClickListener {
            launch {
                view?.apply {
                    val title = et_task_details_title.text.toString()
                    val date = Date(et_task_details_date.text.toString())
                    val desc = et_task_details_desc.text.toString()
                    val isDone = cb_task_details_is_done.isChecked
                    if (task == null) {
                        task = Task(0, title, desc, date, isDone)
                        taskDao.insert(task!!)
                    } else {
                        task!!.apply {
                            this.title = title
                            description = desc
                            this.date = date
                            this.isDone = isDone
                        }
                        taskDao.update(task!!)
                    }
                }
            }
            activity?.onBackPressed()

            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
        coroutineContext.cancel()
    }

    companion object {
        private const val ARG_TASK_ID = "taskId"

        fun newInstance(taskId: Int?) = TaskDetailsFragment().apply {
            if (taskId != null) arguments = Bundle().apply {
                putInt(ARG_TASK_ID, taskId)
            }
        }
    }

}
