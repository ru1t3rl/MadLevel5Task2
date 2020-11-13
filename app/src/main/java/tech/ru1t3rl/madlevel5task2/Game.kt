package tech.ru1t3rl.madlevel5task2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "gameTable")
data class Game (
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "platform")
    var platform: String,

    @ColumnInfo(name = "date")
    var date: LocalDate,

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)