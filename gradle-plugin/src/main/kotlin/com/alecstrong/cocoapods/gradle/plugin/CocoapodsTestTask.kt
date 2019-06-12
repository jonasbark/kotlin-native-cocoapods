package com.alecstrong.cocoapods.gradle.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

open class CocoapodsTestTask : DefaultTask() {
  internal lateinit var target: KotlinNativeTarget

  @Input var device: String = "iPhone 8"

  @TaskAction
  fun performTest() {
    val binary = target.binaries.getTest(NativeBuildType.DEBUG).outputFile
    project.exec { exec ->
      exec.commandLine("xcrun", "simctl", "spawn", device, binary.absolutePath)
    }
  }
}
