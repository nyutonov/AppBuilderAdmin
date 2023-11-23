package uz.gita.appbuilderadmin.utils.navigator

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.SharedFlow

typealias navArgs = Navigator.() ->Unit
interface NavigationHandler {
    val navigationFlow:SharedFlow<navArgs>
}