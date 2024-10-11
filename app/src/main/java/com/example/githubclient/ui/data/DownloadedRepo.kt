package com.example.githubclient.ui.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.githubclient.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class DownloadedRepo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val repoName: String
)

@Dao
interface RepoDao {
    @Query("SELECT * FROM downloaded_repos")
    suspend fun getAll(): List<DownloadedRepo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: DownloadedRepo)
}

@Database(entities = [DownloadedRepo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}