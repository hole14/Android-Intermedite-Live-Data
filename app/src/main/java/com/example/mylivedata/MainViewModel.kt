package com.example.mylivedata

import android.annotation.SuppressLint
import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

@SuppressLint("SuspiciousIndentation")
class MainViewModel: ViewModel() {
    companion object{
        private const val ONE_SECOND = 1000
    }
    private val militialTime = SystemClock.elapsedRealtime()
    private val melapsedTime = MutableLiveData<Long?>()

    init {
     val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - militialTime) / 1000
                melapsedTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }
    fun getElapsedTime(): LiveData<Long?>{
        return melapsedTime
    }
}