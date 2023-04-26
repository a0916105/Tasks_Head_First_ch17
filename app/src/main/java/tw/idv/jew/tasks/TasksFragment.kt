package tw.idv.jew.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tw.idv.jew.tasks.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        //建立資料庫（如果它還不存在）並取得taskDao屬性的參考
        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        //取得viewModel
        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(TasksViewModel::class.java)

        //設定data binding，讓layout可以用它來使用view model的屬性與方法
        binding.viewModel = viewModel

        //設定layout的lifecycleOwner，讓它可以回應live data的改變
        binding.lifecycleOwner = viewLifecycleOwner

        //將adapter加入tasksList recycler view
        //建立adapter
        val adapter = TaskItemAdapter { taskId -> //將lambda傳給adapter
            //按下時執行
            viewModel.onTaskClicked(taskId)
        }
        binding.tasksList.adapter = adapter //將adapter接到recycler view

        //將資料傳給adapter
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                //當工作串列改變時，將新資料傳給adapter的backing list
                adapter.submitList(it)
            }
        })

        viewModel.navigateToTask.observe(viewLifecycleOwner, Observer { taskId ->
            taskId?.let {   //這個let區塊只會在taskId不是null時執行
                val action = TasksFragmentDirections
                    .actionTasksFragmentToEditTaskFragment(taskId)
                this.findNavController().navigate(action)
                viewModel.onTaskNavigated() //將navigateToTask設回null
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}