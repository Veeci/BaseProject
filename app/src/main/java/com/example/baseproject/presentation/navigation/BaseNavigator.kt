package com.example.baseproject.presentation.navigation

import androidx.core.bundle.Bundle
import com.example.baseproject.domain.viewmodel.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

open class BaseNavigator : BaseViewModel(), BaseRouter {
    val navigation = Channel<NavigationEvent>(Channel.RENDEZVOUS)

    val receive: ReceiveChannel<NavigationEvent> get() = navigation

    fun sendEvent(navigationEvent: NavigationEvent) {
        launchCoroutine {
            navigation.send(navigationEvent)
        }
    }

    fun offNavScreen(
        action: Int,
        extras: android.os.Bundle = android.os.Bundle(),
        isFinished: Boolean = false,
    ) {
        launchCoroutine {
            navigation.send(
                NextScreen(
                    action,
                    extras.apply {
                        putBoolean("isFinished", isFinished)
                    },
                ),
            )
        }
    }

    fun offNavScreen(
        nextScreen: NextScreen,
        isFinished: Boolean = false,
    ) {
        launchCoroutine {
            navigation.send(
                nextScreen.copy(
                    extras =
                        nextScreen.extras.apply {
                            putBoolean("isFinished", isFinished)
                        },
                ),
            )
        }
    }

    override fun onNextScreen(
        action: Int,
        extras: android.os.Bundle,
    ): Boolean {
        offNavScreen(action, extras)
        return true
    }

    override fun onPopScreen(
        action: Int?,
        inclusive: Boolean?,
        saveState: Boolean?,
    ): Boolean {
        sendEvent(PopScreen(action = action ?: -1, inclusive = inclusive, saveState = saveState))
        return true
    }

    override fun backToHome(
        action: Int,
        extras: Bundle?,
    ) {
        sendEvent(BackToHome(action, extras))
    }

    override fun openDeeplink(
        action: Int,
        extras: Bundle?,
    ) {
        sendEvent(NavigateWithDeeplink(action, extras))
    }

    override fun onShareFile(extras: Bundle?) {
        sendEvent(ShareFile(extras = extras))
    }

    override fun gotoComingSoon(
        action: Int,
        extras: Bundle?,
    ) {
        sendEvent(ComingSoon(action, extras))
    }

    override fun onSessionTimeout(
        action: Int,
        extras: Bundle?,
    ) {
        sendEvent(SessionTimeout(action, extras))
    }

    override fun onNoInternet(
        action: Int,
        extras: android.os.Bundle?,
    ) {
        sendEvent(NoInternet(action, extras))
    }

    override fun onInvalidLocalTime(
        action: Int,
        extras: android.os.Bundle?,
    ) {
        sendEvent(InvalidLocalTime(action, extras))
    }

    override fun onOtherErrorDefault(
        action: Int,
        extras: Bundle?,
    ) {
        sendEvent(OtherError(action, extras))
    }

    override fun onErrorEvent(message: String?) {
        ErrorEvent(message)
    }

    override fun onPermissionResultEvent(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int,
    ) {
        sendEvent(PermissionResultEvent(requestCode, permissions, grantResults, deviceId))
    }

    override fun notImplemented() {
        sendEvent(NotImplementedYet)
    }

    override fun notRecognized() {
        sendEvent(NavigationEvent())
    }
}
