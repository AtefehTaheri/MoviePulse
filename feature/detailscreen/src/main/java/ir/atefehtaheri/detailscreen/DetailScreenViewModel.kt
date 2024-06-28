package ir.atefehtaheri.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.detailitem.repository.DetailItemRepository
import ir.atefehtaheri.detailscreen.UiState.DetailMovieState
import ir.atefehtaheri.detailscreen.UiState.DetailTvShowState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val detailItemRepository: DetailItemRepository
    ) : ViewModel() {


    private val _detailMovie = MutableStateFlow(DetailMovieState())
    val detailMovie = _detailMovie.asStateFlow()

    private val _detailTvShow = MutableStateFlow(DetailTvShowState())
    val detailTvShow = _detailTvShow.asStateFlow()

    private val _errorState =
        MutableStateFlow("")
    val errorState = _errorState.asStateFlow()

    fun getDetailMovie(movieid:String) {
        viewModelScope.launch {

            _detailMovie.update {
                it.copy(loading = true)
            }
            val response = detailItemRepository.getDetailMovie(movieid)
            when (response) {
                is ResultStatus.Failure ->
                    updateError(response.exception_message)


                is ResultStatus.Success -> {
                    clearError()
                    _detailMovie.update {
                        it.copy(response.data,false)

                    }
                }
            }
        }
    }

    fun getDetailTvShow(tvshowid:String) {
        viewModelScope.launch {

            _detailTvShow.update {
                it.copy(loading = true)
            }
            val response = detailItemRepository.getDetailTvShow(tvshowid)
            when (response) {
                is ResultStatus.Failure ->
                    updateError(response.exception_message)


                is ResultStatus.Success -> {
                    clearError()
                    _detailTvShow.update {
                        it.copy(response.data,false)

                    }
                }
            }
        }
    }

    private fun updateError(error: String) {
        if (_errorState.value == "") {
            _errorState.update {
                error
            }
        }
    }

    private fun clearError() {
            _errorState.update {
                ""
            }
    }

}

