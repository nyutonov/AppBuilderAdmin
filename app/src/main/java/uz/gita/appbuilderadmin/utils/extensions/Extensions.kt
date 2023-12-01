package uz.gita.appbuilderadmin.utils.extensions

import android.widget.Toast
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.appbuilderadmin.app.App

fun <T> CollectionReference.getAllLive(mapper: (DocumentSnapshot) -> T): Flow<Result<List<T>>> =
    callbackFlow {
        val listenerRegistration = addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(Result.failure(error)).isSuccess
                return@addSnapshotListener
            }

            snapshot?.let {
                val data = snapshot.documents.map { documentSnapshot ->
                    mapper(documentSnapshot)
                }
                trySend(Result.success(data)).isSuccess
            }
        }

        awaitClose { listenerRegistration.remove() }
    }

fun myToast(message: String) {
    Toast.makeText(App.instance, message, Toast.LENGTH_LONG).show()
}