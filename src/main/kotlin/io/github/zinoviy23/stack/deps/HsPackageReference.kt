package io.github.zinoviy23.stack.deps

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.ResolveResult

class HsPackageReference(psiElement: PsiElement, textRange: TextRange) :
    PsiReferenceBase.Poly<PsiElement>(psiElement, textRange, false) {

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        TODO("Not yet implemented")
    }
}
