package uz.gita.appbuilderadmin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.appbuilderadmin.domain.usecases.AddUserUseCase
import uz.gita.appbuilderadmin.domain.usecases.GetAllUserUseCase
import uz.gita.appbuilderadmin.domain.usecases.impl.AddUserUseCaseImpl
import uz.gita.appbuilderadmin.domain.usecases.impl.GetAllUserUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindAddUserUseCase(impl : AddUserUseCaseImpl) : AddUserUseCase

    @Binds
    fun bindGetAllUserUseCase(impl : GetAllUserUseCaseImpl) : GetAllUserUseCase

}