package uz.gita.appbuilderadmin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.appbuilderadmin.presentation.screens.register.RegisterDirection
import uz.gita.appbuilderadmin.presentation.screens.register.RegisterDirectionImpl
import uz.gita.appbuilderadmin.presentation.screens.user_ui.UserUIContract
import uz.gita.appbuilderadmin.presentation.screens.user_ui.UserUIDirection
import uz.gita.appbuilderadmin.presentation.screens.users.UsersContract
import uz.gita.appbuilderadmin.presentation.screens.users.UsersDirection
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {

    @Binds
    fun bindRegisterDirection(impl : RegisterDirectionImpl) : RegisterDirection

    @Binds
    fun bindUsersDirection(impl: UsersDirection): UsersContract.Direction

    @Binds
    fun bindUserUIDirection(impl: UserUIDirection): UserUIContract.Direction

}