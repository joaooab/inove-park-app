package br.com.inove_park_app.ui.home

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.inove_park_app.data.Cost
import br.com.inove_park_app.data.WalletMemory
import java.util.concurrent.TimeUnit


class BottomSheetParkViewModel : ViewModel() {

    companion object {
        const val MAX_VALUE = 24
        const val MIN_VALUE = 1
        private const val MILI_SECONDS = 3600000
        private const val FORMAT_CRONOMETER = "%02d:%02d:%02d"
    }

    private val _cronometer: MutableLiveData<String> = MutableLiveData()
    val cronometer: LiveData<String> = _cronometer

    private val _started: MutableLiveData<Boolean> = MutableLiveData(false)
    val started: LiveData<Boolean> = _started

    private val _total = MutableLiveData<Double>()
    val total = _total

    private val _balance = MutableLiveData<Double>(WalletMemory.wallet.balance)
    val balance = _balance

    private val _cost = MutableLiveData<Double>(WalletMemory.cost)
    val cost = _cost

    private lateinit var countDownTimer: CountDownTimer

    fun start(hours: Int) {
        _started.value = true
        countDownTimer = createCountDownTimer(hours)
        countDownTimer.start()
    }

    private fun createCountDownTimer(hours: Int): CountDownTimer {
        val time = MILI_SECONDS * hours.toLong()
        return object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val hms = convertMilistToTimeString(millisUntilFinished)
                _cronometer.value = hms
            }

            override fun onFinish() {
                _cronometer.value = "done!"
            }
        }
    }

    private fun convertMilistToTimeString(millis: Long): String {
        return String.format(
            FORMAT_CRONOMETER,
            TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(
                    millis
                )
            ),
            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    millis
                )
            )
        )
    }

    fun isStarted(): Boolean {
        return _started.value ?: false
    }

    fun shutDown() {
        _started.value = false
        countDownTimer.cancel()
    }

    fun calculateTotal() {
        val cost = _cost.value ?: 0.0
        val balance = _balance.value ?: 0.0
        val total = balance - cost
        _total.value = total
    }

    fun changeCost(newVal: Int) {
        val newCost = WalletMemory.wallet.balance * newVal
        cost.value = newCost
    }

}