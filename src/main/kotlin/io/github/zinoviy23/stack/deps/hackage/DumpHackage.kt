package io.github.zinoviy23.stack.deps.hackage

import com.intellij.codeInsight.intention.impl.BaseIntentionAction
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.*
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.util.concurrency.AppExecutorUtil
import org.jetbrains.yaml.psi.YAMLFile
import java.util.concurrent.Callable

class DumpHackage : BaseIntentionAction() {
    override fun getText(): String {
        return familyName
    }

    override fun getFamilyName(): String {
        return "Dump Hackage by selection"
    }

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile): Boolean {
        if (file !is YAMLFile || editor?.selectionModel?.hasSelection() != true) return false

        return true
    }

    override fun invoke(project: Project, editor: Editor?, file: PsiFile) {
        val selectionText = editor?.selectionModel?.selectedText ?: return

        ReadAction.nonBlocking(Callable {
            HackageService.getInstance(project).getPackagesByText(selectionText)
                .joinToString(separator = "\n") { it.name }
        })
            .finishOnUiThread(ModalityState.defaultModalityState()) {
                HackageService.getInstance(project).notificationGroup
                    .createNotification(it, NotificationType.INFORMATION)
                    .notify(project)
            }
            .submit(AppExecutorUtil.getAppExecutorService())
    }
}
