package com.example.tabu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tabu.databinding.ScoreScreenBinding
/*
 * Fragment für den ScoreScreen
 * Der Endpunktestand nach Zeitablauf wird hier angezeigt
 *
 * Die zugehörige UI befindet sich unter res/layout/score_screen.xml
 * und kann dort in der Design Ansicht betrachtet werden.
 * Hier muss NICHTS angepasst werden
 */
class ScoreScreen : Fragment() {
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ScoreScreenBinding = DataBindingUtil.inflate(  // Hier
            inflater,
            R.layout.score_screen,
            container,
            false
        )

        val scoreFragmentArgs by navArgs<ScoreScreenArgs>()

        viewModelFactory = ScoreViewModelFactory(scoreFragmentArgs.score)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ScoreViewModel::class.java)

        binding.scoreViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.eventPlayAgain.observe(this, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreScreenDirections.actionRestart())
                viewModel.onPlayAgainComplete()
            }
        })

        return binding.root
    }
}
