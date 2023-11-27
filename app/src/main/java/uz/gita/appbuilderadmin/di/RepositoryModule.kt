package uz.gita.appbuilderadmin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.appbuilderadmin.data.repository.RepositoryImpl
import uz.gita.appbuilderadmin.domain.repository.Repository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @[Binds Singleton]
    fun bindRepository(impl : RepositoryImpl) : Repository
}