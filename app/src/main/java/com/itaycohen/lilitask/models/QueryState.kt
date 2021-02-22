package com.itaycohen.lilitask.models

sealed class QueryState {
    object Idle: QueryState()
    object Success: QueryState()
    object Running: QueryState()
    data class Failure(val errMsg: String?): QueryState()
}