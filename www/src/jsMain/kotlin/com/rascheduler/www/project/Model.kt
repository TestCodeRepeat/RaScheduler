package com.rascheduler.www.project

import io.kvision.remote.getService

object Model {

    private val pingService = getService<IPingService>()

    suspend fun pingServer(message: String): String {
        return pingService.ping(message)
    }

}
