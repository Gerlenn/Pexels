package com.app.pexels.util

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun String.encodeUrl() = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())