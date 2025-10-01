package kr.ac.kumoh.s20171268.s25w04mvvm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {
    //private val _counterState = mutableStateOf(CounterModel(0))
    private val _counterState = MutableStateFlow(CounterModel(0))

    //val counterState: State<CounterModel> = _counterState
    val counterState: StateFlow<CounterModel> = _counterState

    fun addCounter() {
        viewModelScope.launch {
            _counterState.update { currentModel ->
                CounterModel(count = currentModel.count + 1)
            }
        }
    }

    fun subCounter() {
        _counterState.value = CounterModel(_counterState.value.count - 1)
    }

    fun resetCounter() {
        _counterState.value = CounterModel(0)
    }
}