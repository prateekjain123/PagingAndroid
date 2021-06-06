package com.pn.appentustest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pn.appentustest.data.ImagesData
import com.pn.appentustest.repository.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(private val repository: ImagesRepository) : ViewModel() {

    fun apiData(): Flow<PagingData<ImagesData>> {
        return repository.getResults().cachedIn(viewModelScope)
    }
}