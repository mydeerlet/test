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
        binding.button2.setOnClickListener {
            val controller = Navigation.findNavController(it)
            controller.navigate(R.id.action_titleFragment_to_quesitionFragment)
        }
        return binding.root
    }


}
