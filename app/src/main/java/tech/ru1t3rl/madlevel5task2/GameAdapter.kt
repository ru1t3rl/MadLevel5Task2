package tech.ru1t3rl.madlevel5task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.ru1t3rl.madlevel5task2.databinding.ItemGameBinding

class GameAdapter(private val games: List<Game>):
        RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemGameBinding.bind(itemView)

        fun dataBind(game: Game){
            binding.tvDate.text = game.date.toString()
            binding.tvTitle.text = game.title
            binding.tvPlatform.text = game.platform
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(games[position])
    }
}