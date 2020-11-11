package com.shindra.arrakis.cache

import android.content.Context
import androidx.room.*
import com.shindra.arrakis.bo.PersistentCacheWrapper
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import java.util.*

@Dao
interface PersistentDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wrapper : PersistentCacheWrapper)

    @Delete
    fun delete(wrapper : PersistentCacheWrapper)

    @Query("SELECT * FROM PersistentCacheWrapper WHERE `key` == :key")
    fun getPersistentWrapper(key: String): Single<PersistentCacheWrapper>

}

@Database(entities = [PersistentCacheWrapper::class], version = 1)
@TypeConverters(Converters::class)
abstract class PersistentRoom : RoomDatabase(){

    companion object{
        fun buildRoomDataBase(context: Context) : PersistentRoom{
            return Room.databaseBuilder(context,PersistentRoom::class.java,"PersistentDb").build()
        }
    }

    abstract fun getPersistentWrapperDao() : PersistentDao

}


class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}