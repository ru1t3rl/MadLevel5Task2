package tech.ru1t3rl.madlevel5task2

import android.content.Context
import androidx.room.*

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME ="GAME_DATABASE"

        @Volatile
        private var gameRoomDatabaseInstance: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase? {
            if(gameRoomDatabaseInstance == null){
                synchronized(GameDatabase::class.java) {
                    if(gameRoomDatabaseInstance == null) {
                        gameRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            GameDatabase::class.java, DATABASE_NAME
                        ).build()
                    }
                }
            }
            
            return gameRoomDatabaseInstance
        }
    }
}