package com.rascheduler.www.project

import com.rascheduler.shared.domain.SharedRepository
import org.koin.core.annotation.Factory

@Suppress("ACTUAL_WITHOUT_EXPECT")
@Factory
actual class PingService : IPingService {

    val sharedRepository = SharedRepository()

    override suspend fun ping(message: String): String {
        println(message)
        return "Hello world from server! -- your message: $message"
    }

    override suspend fun helloFromShared(): String {
        return sharedRepository.hello()
    }


}
