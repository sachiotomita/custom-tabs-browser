/*
 * Copyright (c) 2018 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.customtabsbrowser

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.browser.customtabs.CustomTabsService
import androidx.browser.customtabs.CustomTabsSessionToken

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
class CustomTabsConnectionService : CustomTabsService() {
    private val handler = Handler(Looper.getMainLooper())

    override fun warmup(flags: Long): Boolean {
        handler.post {
            WebViewHolder.warmup(this)
        }
        return true
    }

    override fun newSession(sessionToken: CustomTabsSessionToken?): Boolean = true
    override fun mayLaunchUrl(
        sessionToken: CustomTabsSessionToken?,
        uri: Uri?,
        extras: Bundle?,
        otherLikelyBundles: MutableList<Bundle>?
    ): Boolean {
        handler.post {
            WebViewHolder.mayLaunchUrl(uri, otherLikelyBundles)
        }
        return true
    }

    override fun extraCommand(commandName: String?, args: Bundle?): Bundle? = null
    override fun requestPostMessageChannel(
        sessionToken: CustomTabsSessionToken?,
        postMessageOrigin: Uri?
    ): Boolean = false

    override fun postMessage(
        sessionToken: CustomTabsSessionToken?,
        message: String?,
        extras: Bundle?
    ): Int = CustomTabsService.RESULT_FAILURE_DISALLOWED

    override fun validateRelationship(
        sessionToken: CustomTabsSessionToken?,
        relation: Int,
        origin: Uri?,
        extras: Bundle?
    ): Boolean = false

    override fun updateVisuals(
        sessionToken: CustomTabsSessionToken?,
        bundle: Bundle?
    ): Boolean = false
}
