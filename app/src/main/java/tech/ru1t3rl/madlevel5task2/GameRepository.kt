package tech.ru1t3rl.madlevel5task2

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GameRepository(context: Context) {
    private var gameDao: GameDao

    init {
        val gameDatabase = GameDatabase.getDatabase(context)
        gameDao = gameDatabase!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }

    suspend fun  deleteAllGames(){
        gameDao.deleteAllGames()
    }
}