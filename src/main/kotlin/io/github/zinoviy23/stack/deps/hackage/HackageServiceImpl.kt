package io.github.zinoviy23.stack.deps.hackage

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request

class HackageServiceImpl(val project: Project) : HackageService {
    override val notificationGroup = NotificationGroup(
        "kek",
        NotificationDisplayType.NONE,
        isLogByDefault = true
    )

    private val client = OkHttpClient()
    private val mapper = ObjectMapper().registerKotlinModule()

    private fun getUrl(terms: String): HttpUrl {
        return HackageConstants.SEARCH_URL
            .toHttpUrlOrNull().let {
                LOG.assertTrue(it != null)
                it!!
            }
            .newBuilder()
            .addQueryParameter(HackageConstants.TERMS_PARAM, terms)
            .build()
    }

    override fun getPackagesByText(text: String): Iterable<HackageService.PackageName> {
        val request = Request.Builder()
            .url(getUrl(text))
            .get()
            .addHeader("Accept", "application/json")
            .build()

        client.newCall(request).execute().use { response ->
            val stream = response.body?.byteStream() ?: return emptyList()
            return mapper.readValue<List<HackageService.PackageName>>(stream)
        }
    }

    companion object {
        private val LOG = logger<HackageServiceImpl>()
    }
}
