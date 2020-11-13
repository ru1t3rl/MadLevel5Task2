package tech.ru1t3rl.madlevel5task2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GameDao {
    @Query("SELECT * FROM gameTable")
    fun getAllGames(): LiveData<List<Game>>

    @Insert
    suspend fun insterGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Update
    suspend fun updateGame(game: Game)
}