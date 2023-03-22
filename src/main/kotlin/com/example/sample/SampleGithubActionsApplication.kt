package com.example.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleGithubActionsApplication

fun main(args: Array<String>) {
    runApplication<SampleGithubActionsApplication>(*args)
}
