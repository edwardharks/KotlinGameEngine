package com.edwardharker.kge.util

import kotlin.js.Date

actual fun getCurrentTimeMillis() = Date.now().toLong()