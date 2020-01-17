package com.mydeerlet.myapplication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.mydeerlet.myapplication.databinding.FragmentTitleBinding
import com.mydeerlet.myapplication.my_view_model.MyViewModel


/**
 * A simple [Fragment] subclass.
 */

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_title, container, false)

        val myViewModel = ViewModelProviders.of(requireActivity(),SavedStateViewModelFactory(activity!!.application ,this))
            .get(MyViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)

        binding.date = myViewModel
        binding.lifecycleOwner = requireActivity()


        val clicklistener = View.OnClickListener {

            val controller = Navigation.findNavController(it)

            when (it.id) {
                R.id.button1 -> {
                    myViewModel.LEVL = 10
                }
                R.id.button2 -> {
                    myViewModel.LEVL = 20
                }
                R.id.button3 -> {
                    myViewModel.LEVL = 50
                }
                R.id.button4 -> {
                    myViewModel.LEVL = 100
                }
            }
            controller.navigate(R.id.action_titleFragment_to_quesitionFragment)
        }
        binding.button1.setOnClickListener(clicklistener)
        binding.button2.setOnClickListener(clicklistener)
        binding.button3.setOnClickListener(clicklistener)
        binding.button4.setOnClickListener(clicklistener)
        return binding.root
    }


}
