package tech.ru1t3rl.madlevel5task2

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import tech.ru1t3rl.madlevel5task2.databinding.FragmentAddGameBinding
import java.time.LocalDate

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {
    private lateinit var binding: FragmentAddGameBinding

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddGameBinding.inflate(layoutInflater)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabSave.setOnClickListener {
            onSaveGame()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onSaveGame() {
        val title = binding.etTitle.text.toString()

        val date = LocalDate.of(
            binding.etYear.text.toString().toIntOrNull()!!,
            binding.etMonth.text.toString().toIntOrNull()!!,
            binding.etDay.text.toString().toIntOrNull()!!
        )
        Log.i("date", date.toString())

        val platform = binding.etPlatform.text.toString()

        if (title.isNotBlank() && platform.isNotBlank()) {
            viewModel.insertGame(Game(title, platform, date))

            findNavController().navigate(R.id.action_AddGameFragment_to_HomeFragment)
        } else {
            Toast.makeText(
                activity,
                R.string.empty_field,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}