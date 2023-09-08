package com.example.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentAddToDoPopupBinding
import com.example.notesapp.utils.ToDoData
import com.google.android.material.textfield.TextInputEditText


class AddToDoPopupFragment : DialogFragment() {

    private lateinit var mBinding: FragmentAddToDoPopupBinding
    private lateinit var listener: DialogNextBtnClickListener
    private var toDoData: ToDoData? = null


    fun setListener(listener: DialogNextBtnClickListener) {
        this.listener = listener
    }

    companion object {
        const val TAG = "AddToDoPopupFragment"

        @JvmStatic
        fun newInstance(taskId: String, task: String) = AddToDoPopupFragment().apply {
            arguments = Bundle().apply {
                putString("taskId", taskId)
                putString("task", task)

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        mBinding = FragmentAddToDoPopupBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            toDoData = ToDoData(
                arguments?.getString("taskId").toString(),
                arguments?.getString("task").toString()
            )
            mBinding.todoEt.setText(toDoData?.task)
        }


        registerEvents()
    }

    private fun registerEvents() {
        mBinding.todoNextBtn.setOnClickListener {
            val todoTask = mBinding.todoEt.text.toString()
            if (todoTask.isNotEmpty()) {
                if (todoTask == null) {
                    listener?.onSaveTask(todoTask, mBinding.todoEt)
                } else {
                    toDoData!!.task = todoTask
                    listener?.onUpdateTask(toDoData!!, mBinding.todoEt)
                }

            } else {
                Toast.makeText(context, "Please type some task", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.todoClose.setOnClickListener {
            dismiss()
        }
    }

    interface DialogNextBtnClickListener {
        fun onSaveTask(todo: String, todoEt: TextInputEditText)
        fun onUpdateTask(toDoData: ToDoData, todoEt: TextInputEditText)
    }

}