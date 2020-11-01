package com.lupesoft.pruebadeingreso.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lupesoft.pruebadeingreso.data.post.Post
import com.lupesoft.pruebadeingreso.data.user.UserDao
import com.lupesoft.pruebadeingreso.data.user.UserEntity
import com.lupesoft.pruebadeingreso.utilities.DATABASE_NAME

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                /**
                 * Llamado para agregar json de users con el worker
                 */
//                .addCallback(
//                    object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
//                            WorkManager.getInstance(context).enqueue(request)
//                        }
//                    }
//                )
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}