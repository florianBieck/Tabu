package com.example.tabu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

/*
 * Fragment für den UI Screen des Games
 * Hier wird die Logik der Anwendung mit der UI verbunden
 */
class GameScreen : Fragment() {

    private val model: GameViewModel = GameViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.game_screen, container, false)

        model.word.observe(this, Observer { text ->
            view.findViewById<TextView>(R.id.word_text).setText(model.word.value)
        })
        model.score.observe(this, Observer { score ->
            view.findViewById<TextView>(R.id.score_text).setText(model.score.value.toString())
        })

        view.findViewById<Button>(R.id.correct_button).setOnClickListener { v ->
            model.score.postValue(model.score.value?.plus(1))
            nextWord()
        }

        view.findViewById<Button>(R.id.wrong_button).setOnClickListener { v ->
            if (model.score.value!! > 0){
                model.score.postValue(model.score.value?.minus(1))
            }
            nextWord()
        }

        model.gameFinished.observe(this, Observer { finished ->
            if (finished){
                val currentScore = model.score.value?: 0
                val action = GameScreenDirections.actionGameToScore(currentScore)
                findNavController().navigate(action)
                model.onGameFinishedComplete()
            }
        })

        model.time.observe(this, Observer { time ->
            view.findViewById<TextView>(R.id.timer_text).setText((model.time.value?.div(1000)).toString())
        })

        return view
    }

    fun nextWord () {
        if (model.wordIndex.value!! < model.words.size-1) {
            model.wordIndex.postValue(model.wordIndex.value?.plus(1))
        } else {
            model.wordIndex.postValue(0)
        }
        model.word.postValue(model.words[model.wordIndex.value!!])
    }
}