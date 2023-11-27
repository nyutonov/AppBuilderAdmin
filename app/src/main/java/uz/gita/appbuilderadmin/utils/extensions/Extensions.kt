package uz.gita.appbuilderadmin.utils.extensions

import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.appbuilderadmin.presentation.screens.constructor.ConstructorContract

suspend fun <T> Task<QuerySnapshot>.getAllSync(mapper: (DocumentSnapshot) -> T): Result<List<T>> {
    val deferred = CompletableDeferred<Result<List<T>>>()
    addOnSuccessListener {
        val ls = it.documents.map { mapper(it) }
        deferred.complete(Result.success(ls))
    }
        .addOnFailureListener { deferred.complete(Result.failure(it)) }
    return deferred.await()
}

fun <T> Task<QuerySnapshot>.getAll(mapper: (DocumentSnapshot) -> T): Flow<Result<List<T>>> = flow {
    emit(getAllSync(mapper))
}
