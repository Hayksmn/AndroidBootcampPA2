package com.self.admin.bootcamp.fragments


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.self.admin.bootcamp.R
import com.self.admin.bootcamp.databinding.FragmentGameScreenBinding
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class GameScreenFragment : Fragment() {

    private lateinit var binding: FragmentGameScreenBinding
    var guess: Int? = null
    private var answer: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game_screen,
            container,
            false
        )

        binding.numberInput.addTextChangedListener(
            object: TextWatcher{
                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(!s.isNullOrBlank()){
                        guess = binding.numberInput.text.toString().toIntOrNull()
                        if (guess != null) {
                            binding.answerButton.isEnabled = true
                        }
                    }else {
                        binding.answerButton.isEnabled = false
                    }
                }

            }
        )


        binding.answerButton.setOnClickListener {
            answer = Random.nextInt(1, 2)
            println(answer)
            if (guess == answer) {
                it.findNavController().navigate(R.id.action_gameScreenFragment_to_winScreenFragment)
            } else {
                it.findNavController()
                    .navigate(R.id.action_gameScreenFragment_to_gameOverScreenFragment)
            }
        }



        return binding.root
    }


}
