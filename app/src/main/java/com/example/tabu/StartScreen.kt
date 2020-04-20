package com.example.tabu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tabu.databinding.StartScreenBinding
import kotlinx.android.synthetic.main.game_screen.view.*
import kotlinx.android.synthetic.main.start_screen.*


/**
 *  Fragment für den Startbildschirm der Anwendung
 *  Die zugehörige UI befindet sich unter res/layout/start_screen.xml
 *  und kann dort in der Design Ansicht betrachtet werden.
 */
class StartScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.start_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        play_button.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_start_to_game)
        }
    }
}

