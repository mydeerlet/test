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
import com.mydeerlet.myapplication.databinding.FragmentLoseBinding
import com.mydeerlet.myapplication.databinding.FragmentWinBinding
import com.mydeerlet.myapplication.my_view_model.MyViewModel

/**
 * A simple [Fragment] subclass.
 */
class LoseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val myViewModel = ViewModelProviders.of(requireActivity(),
            SavedStateViewModelFactory(activity!!.application ,this)
        )
            .get(MyViewModel::class.java)


        val binding = DataBindingUtil.inflate<FragmentLoseBinding>(
            inflater,
            R.layout.fragment_lose,
            container,
            false
        )
        binding.date = myViewModel
        binding.lifecycleOwner = requireActivity()


        binding.button10.setOnClickListener {
            val controller = Navigation.findNavController(it)
            controller.navigate(R.id.action_loseFragment_to_titleFragment)
        }

        return binding.root
    }

}
