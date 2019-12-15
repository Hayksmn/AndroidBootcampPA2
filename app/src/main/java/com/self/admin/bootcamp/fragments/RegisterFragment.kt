package com.self.admin.bootcamp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.self.admin.bootcamp.Player
import com.self.admin.bootcamp.R
import com.self.admin.bootcamp.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val player: Player = Player()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register,
            container,
            false
        )

        binding.player = player

        binding.startButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_registerFragment_to_gameScreenFragment)
        }
        return binding.root
    }


}
