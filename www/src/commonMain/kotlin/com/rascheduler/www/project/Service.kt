package com.rascheduler.www.project

import io.kvision.annotations.KVService

@KVService
interface IPingService {
    suspend fun ping(message: String): String
}
