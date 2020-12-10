package com.kotlin.tutorial.webfluxdemo.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

//不做泛型擦除,当前实例对象。
inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}