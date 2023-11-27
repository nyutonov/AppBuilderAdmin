package uz.gita.appbuilderadmin.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VisibilityEntity::class] , version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getVisibilityDao() : VisibilityDao

}