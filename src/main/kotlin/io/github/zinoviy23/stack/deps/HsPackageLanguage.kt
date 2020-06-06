package io.github.zinoviy23.stack.deps

import com.intellij.lang.InjectableLanguage
import com.intellij.lang.Language

class HsPackageLanguage : Language("Haskell package dependency"), InjectableLanguage {
    companion object {
        val INSTANCE = HsPackageLanguage()
    }
}
