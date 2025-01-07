package ro.si.finnsathii.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinnSathiiMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // Handle FCM messages here
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Handle new FCM token here
    }
}
