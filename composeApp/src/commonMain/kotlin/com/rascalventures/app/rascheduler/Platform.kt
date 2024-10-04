package com.rascalventures.app.rascheduler

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform