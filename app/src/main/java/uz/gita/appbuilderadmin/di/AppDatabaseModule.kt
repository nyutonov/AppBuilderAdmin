package uz.gita.appbuilderadmin.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.appbuilderadmin.data.source.local.room.AppDatabase
import uz.gita.appbuilderadmin.data.source.local.room.VisibilityDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {

    @[Provides Singleton]
    fun provideAppDatabase(@ApplicationContext context : Context) : AppDatabase =
        Room.databaseBuilder(context , AppDatabase::class.java , "database")
            .build()

    @[Provides Singleton]
    fun provideVisibilityDao(appDatabase: AppDatabase) : VisibilityDao = appDatabase.getVisibilityDao()

}