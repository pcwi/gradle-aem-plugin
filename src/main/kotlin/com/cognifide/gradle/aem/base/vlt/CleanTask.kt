package com.cognifide.gradle.aem.base.vlt

import com.cognifide.gradle.aem.api.AemDefaultTask
import com.cognifide.gradle.aem.internal.Formats
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

open class CleanTask : AemDefaultTask() {

    companion object {
        val NAME = "aemClean"
    }

    @Internal
    private val runner = VltRunner(project)

    init {
        description = "Clean checked out JCR content."

        beforeExecuted(CheckoutTask.NAME) { runner.cleanBeforeCheckout() }
    }

    @TaskAction
    fun clean() {
        runner.cleanAfterCheckout()
        notifier.default("Cleaned JCR content", "Directory: ${Formats.rootProjectPath(config.contentPath, project)}")
    }

}