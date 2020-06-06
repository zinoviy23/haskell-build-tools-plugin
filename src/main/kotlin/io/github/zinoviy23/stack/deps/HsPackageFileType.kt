package io.github.zinoviy23.stack.deps

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class HsPackageFileType : LanguageFileType(HsPackageLanguage.INSTANCE) {
    companion object {
        @JvmStatic
        val INSTANCE = HsPackageFileType()
    }

    override fun getIcon(): Icon = AllIcons.FileTypes.Unknown

    override fun getName(): String = "Haskell Package File"

    override fun getDefaultExtension(): String = "haskellpackagefile"

    override fun getDescription(): String = ""
}
