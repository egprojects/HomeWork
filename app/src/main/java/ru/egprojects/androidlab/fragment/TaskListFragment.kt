package ru.egprojects.androidlab.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_task_list.view.*
import kotlinx.coroutines.*
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.adapter.TaskListAdapter
import ru.egprojects.androidlab.model.AppDatabase
import ru.egprojects.androidlab.model.Task
import ru.egprojects.androidlab.model.TaskDao
import kotlin.coroutines.CoroutineContext

class TaskListFragment(private val taskInterface: TaskInterface) : Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Default
    private lateinit var taskDao: TaskDao
    private val adapter = TaskListAdapter({ showDetails(it.id) }, { deleteTask(it) })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        taskDao = AppDatabase(context).taskDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.title_task_list)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_task_list, container, false)?.apply {
        updateData()
        rv_task_list.layoutManager = LinearLayoutManager(context)
        rv_task_list.adapter = adapter
        fab_task_list_add.setOnClickListener { showDetails(null) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_task_list, menu)
        menu.findItem(R.id.action_delete).setOnMenuItemClickListener {
            launch {
                taskDao.deleteAll()
                updateData()
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
        coroutineContext.cancel()
    }

    private fun updateData() = launch {
        adapter.submitList(
                taskDao.getAll()
        )
    }

    private fun showDetails(id: Int?) {
        taskInterface.showDetails(id)
    }

    private fun deleteTask(task: Task) {
        launch {
            taskDao.delete(task)
            updateData()
        }
    }

}
