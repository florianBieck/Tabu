package com.example.tabu

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import java.util.*

class GameViewModel: ViewModel() {

    val wordIndex: MutableLiveData<Int> by lazy {
        val data = MutableLiveData<Int> ()
        data.value = 0
        return@lazy data
    }

    val word: MutableLiveData<String> by lazy {
        val data = MutableLiveData<String> ()
        data.value = words[wordIndex.value!!]
        return@lazy data
    }

    val score: MutableLiveData<Int> by lazy {
        val data = MutableLiveData<Int> ()
        data.value = 0
        return@lazy data
    }

    val words: MutableList<String> = listOf("Hund", "Taschenrechner", "Tasse", "Elmo",
        "Lösung", "Flasche", "Kiteboard", "Funktional", "Korn", "Frechheit").shuffled() as MutableList<String>

    val time: MutableLiveData<Long> by lazy {
        val data = MutableLiveData<Long> ()
        data.value = 0
        timer.start()
        return@lazy data
    }

    private val timeToDie: Long = 5 * 1000
    private val timer = object: CountDownTimer(timeToDie, 1000) {
        override fun onFinish() {
            gameFinished.postValue(true)
        }

        override fun onTick(millisUntilFinished: Long) {
            time.postValue(millisUntilFinished)
        }
    }

    val gameFinished: MutableLiveData<Boolean> by lazy {
        val data = MutableLiveData<Boolean> ()
        data.value = false
        return@lazy data
    }

    fun onGameFinishedComplete(){
        timer.cancel()
    }
}