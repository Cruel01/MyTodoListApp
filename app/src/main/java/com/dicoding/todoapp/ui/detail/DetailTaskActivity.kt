package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.ui.list.TaskViewModel
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action

        val taskId = intent.getIntExtra(TASK_ID, -1)

        if (taskId == -1) {
            finish()
            return
        }

        val factory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, factory).get(DetailTaskViewModel::class.java)

        detailTaskViewModel.setTaskId(taskId)

        detailTaskViewModel.task.observe(this, Observer { task ->
            if (task != null) {
                updateUI(task)
            } else {
                finish()
            }
        })

        val delete: Button = findViewById(R.id.btn_delete_task)
        delete.setOnClickListener {
            detailTaskViewModel.deleteTask()
            finish()
        }

    }

    private fun updateUI(task: Task) {
        val tvTitle: TextView = findViewById(R.id.detail_ed_title)
        val tvDescription: TextView = findViewById(R.id.detail_ed_description)
        val tvDueDate: TextView = findViewById(R.id.detail_ed_due_date)

        tvTitle.text = task.title
        tvDescription.text = task.description
        tvDueDate.text = DateConverter.convertMillisToString(task.dueDateMillis)
    }
}