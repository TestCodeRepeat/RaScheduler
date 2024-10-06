package com.rascheduler.www.project

import org.koin.core.annotation.Factory

@Suppress("ACTUAL_WITHOUT_EXPECT")
@Factory
actual class PingService : IPingService {

    override suspend fun ping(message: String): String {
        println(message)
        return "Hello world from server! -- your message: $message"
    }
}
