package com.self.admin.bootcamp.articleInfo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ArticleInfo::class],
    version = 1,
    exportSchema = false
)
abstract class ArticleRoomDatabase : RoomDatabase() {

    abstract val articleInfoDao: ArticleInfoDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleRoomDatabase? = null

        fun getInstance(context: Context): ArticleRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    println("Creating database")
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleRoomDatabase::class.java,
                        "article_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}