package kr.ac.kumoh.s20171268.s25w04mvvm
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _counterState = mutableStateOf(CounterModel(0))
    val counterState: State<CounterModel> = _counterState

    fun setCounter(newValue: Int) {
        _counterState.value = CounterModel(newValue)
    }
}