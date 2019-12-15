package com.self.admin.bootcamp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.self.admin.bootcamp.R
import com.self.admin.bootcamp.databinding.FragmentGameOverScreenBinding

/**
 * A simple [Fragment] subclass.
 */
class GameOverScreenFragment : Fragment() {

    private lateinit var binding: FragmentGameOverScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game_over_screen,
            container,
            false
        )

        binding.newGame.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameOverScreenFragment_to_gameScreenFragment)
        }

        return binding.root
    }


}
