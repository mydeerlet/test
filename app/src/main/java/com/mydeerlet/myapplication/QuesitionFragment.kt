package com.mydeerlet.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateViewModelFactory

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.mydeerlet.myapplication.databinding.FragmentQuesitionBinding
import com.mydeerlet.myapplication.my_view_model.MyViewModel
import java.lang.StringBuilder


/**
 * A simple [Fragment] subclass.
 */
class QuesitionFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_quesition, container, false)


        val myViewModel = ViewModelProviders.of(requireActivity(),SavedStateViewModelFactory(activity!!.application ,this))
            .get(MyViewModel::class.java)
        myViewModel.generator()


        val binding = DataBindingUtil.inflate<FragmentQuesitionBinding>(
            inflater,
            R.layout.fragment_quesition,
            container,
            false
        )

        binding.date = myViewModel
        binding.lifecycleOwner = requireActivity()


        val builder = StringBuilder()


        val listener = View.OnClickListener {

            when (it.id) {
                R.id.button0 -> builder.append("0")
                R.id.button1 -> builder.append("1")
                R.id.button2 -> builder.append("2")
                R.id.button3 -> builder.append("3")
                R.id.button4 -> builder.append("4")
                R.id.button5 -> builder.append("5")
                R.id.button6 -> builder.append("6")
                R.id.button7 -> builder.append("7")
                R.id.button8 -> builder.append("8")
                R.id.button9 -> builder.append("9")
                R.id.buttonClear -> builder.setLength(0)
            }

            if (builder.isEmpty()){
                binding.textView9.text = getString(R.string.input_indicator)
            }else{
                binding.textView9.text = builder.toString()
            }
        }

        binding.button0.setOnClickListener(listener)
        binding.button1.setOnClickListener(listener)
        binding.button2.setOnClickListener(listener)
        binding.button3.setOnClickListener(listener)
        binding.button4.setOnClickListener(listener)
        binding.button5.setOnClickListener(listener)
        binding.button6.setOnClickListener(listener)
        binding.button7.setOnClickListener(listener)
        binding.button8.setOnClickListener(listener)
        binding.button9.setOnClickListener(listener)
        binding.buttonClear.setOnClickListener(listener)


        binding.buttonSubimt.setOnClickListener {
            if (builder.toString().isEmpty()){
                Toast.makeText(requireContext(),"请输入结果",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (builder.toString().toInt() == myViewModel.getAnswer().value){
                myViewModel.answerCorrect()
                builder.setLength(0)
                binding.textView9.text = getString(R.string.answer_correct_message)
            }else{
                val container = Navigation.findNavController(it)
                if (myViewModel.win_flag){
                    container.navigate(R.id.action_quesitionFragment_to_winFragment)
                    myViewModel.win_flag = false
                    myViewModel.save()
                }else{
                    container.navigate(R.id.action_quesitionFragment_to_loseFragment)
                }
            }
        }
        return binding.root
    }
}
