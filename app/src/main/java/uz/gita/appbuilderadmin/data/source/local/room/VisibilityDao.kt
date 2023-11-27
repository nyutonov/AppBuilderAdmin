package uz.gita.appbuilderadmin.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VisibilityDao {

    @Query("select * from visibility")
    fun getAllVisibility() : Flow<List<VisibilityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addVisibility(visibilityEntity: VisibilityEntity)


}