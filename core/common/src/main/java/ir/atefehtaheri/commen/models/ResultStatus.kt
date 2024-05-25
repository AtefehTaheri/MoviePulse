package ir.atefehtaheri.commen.models


sealed class ResultStatus<out S>{
    data class Success<S>(val data: S?) : ResultStatus<S>()
    data class Failure(val exception_message: String) : ResultStatus<Nothing>()
}
