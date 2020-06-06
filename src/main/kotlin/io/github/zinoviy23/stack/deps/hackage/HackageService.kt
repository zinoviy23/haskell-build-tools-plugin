package io.github.zinoviy23.stack.deps.hackage

import com.intellij.notification.NotificationGroup
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

interface HackageService {
    fun getPackagesByText(text: String): Iterable<PackageName>

    val notificationGroup: NotificationGroup

    companion object {
        fun getInstance(project: Project): HackageService = project.service()
    }

    data class PackageName(val name: String)
}
