package io.github.zinoviy23.stack.deps.psi

import com.intellij.psi.tree.IElementType
import io.github.zinoviy23.stack.deps.HsPackageLanguage

class HsPackageTokenType(debugName: String) : IElementType(debugName, HsPackageLanguage.INSTANCE) {
    override fun toString(): String = "HsPackageTokenType.${super.toString()}"
}
