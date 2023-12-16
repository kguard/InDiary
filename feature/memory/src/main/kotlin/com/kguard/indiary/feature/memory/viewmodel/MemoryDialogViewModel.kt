package com.kguard.indiary.feature.memory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.indiary.core.domain.PersonUseCase
import com.kguard.indiary.core.model.DomainPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoryDialogViewModel @Inject constructor(
    private val useCase: PersonUseCase
) : ViewModel() {
    private var _persons = MutableStateFlow<List<DomainPerson>>(emptyList())
    val persons: StateFlow<List<DomainPerson>>
        get() = _persons

    /**
     * Room 에 저당 돼 있는 모든 데이터 호출.
     */
    fun getPersons() {
        viewModelScope.launch {
            _persons.value = useCase.getPersons()
        }
    }
}