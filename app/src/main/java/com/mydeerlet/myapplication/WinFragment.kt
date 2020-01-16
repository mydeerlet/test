package com.mydeerlet.myapplication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import com.mydeerlet.myapplication.databinding.FragmentWinBinding
import com.mydeerlet.myapplication.my_view_model.MyViewModel

/**
 * A simple [Fragment] subclass.
 */
class WinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_win, container, false)


        val myViewModel = ViewModelProviders.of(requireActivity(),
            SavedStateViewModelFactory(activity!!.application ,this)
        )
            .get(MyViewModel::class.java)


        val binding = DataBindingUtil.inflate<FragmentWinBinding>(inflater,R.layout.fragment_win,container,false)
        binding.date = myViewModel
        binding.lifecycleOwner = requireActivity()


        binding.button10.setOnClickListener {
          Navigation.findNavController(it).navigate(R.id.action_winFragment_to_titleFragment)
        }
        return  binding.root
    }


}
