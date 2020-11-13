package tech.ru1t3rl.madlevel5task2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.ru1t3rl.madlevel5task2.databinding.FragmentGameBacklogBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameBacklogFragment : Fragment() {
    private lateinit var binding: FragmentGameBacklogBinding

    private val viewModel: GameViewModel by viewModels()

    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBacklogBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddGame.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_AddGameFragment)
        }

        initRv()

        observeAddGameResult()
    }

    private fun initRv() {
        binding.rvGames.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvGames.adapter = gameAdapter

        createItemTouchHelper().attachToRecyclerView(binding.rvGames)
    }

    private fun observeAddGameResult() {
        viewModel.games.observe(viewLifecycleOwner, { games ->
            this.games.clear()
            this.games.addAll(games)
            gameAdapter.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        viewModel.deleteGame(games[position])
                    }
                }
            }
        }

        return ItemTouchHelper(callback)
    }
}