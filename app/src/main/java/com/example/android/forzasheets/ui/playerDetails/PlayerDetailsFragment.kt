package com.example.android.forzasheets.ui.playerDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.android.forzasheets.MainActivity
import com.example.android.forzasheets.R
import com.example.android.forzasheets.databinding.FragmentPlayerDetailsBinding
import com.example.android.forzasheets.models.player.detail.PlayerDetail


class PlayerDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPlayerDetailsBinding
    private lateinit var playerId: String
    private lateinit var teamLogo: String
    private lateinit var viewModel: PlayerDetailsViewModel
    private var flag: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (this.activity as MainActivity).supportActionBar!!.title = "Player Details"
        playerId = PlayerDetailsFragmentArgs.fromBundle(requireArguments()).playerId
        teamLogo = PlayerDetailsFragmentArgs.fromBundle(requireArguments()).teamLogo


        val viewModelFactory = PlayerDetailsViewModelFactory(playerId)
        viewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(PlayerDetailsViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_player_details,
            container,
            false
        )
        // Inflate the layout for this fragment
        binding.progressBar.visibility = View.VISIBLE
        getPlayerDetails()
        return binding.root
    }


    private fun getPlayerDetails() {
        viewModel.getPlayerDetails(
            viewModel.selectedPlayerId.value!!,
            onSuccess = { player ->
                flag = true
                Glide.with(this)
                    .load(teamLogo)
                    .transform(CenterCrop())
                    .into(binding.playerDetailTeamLogo)
                bindElements(player)
            },
            onError = {
                Toast.makeText(
                    this.context,
                    getString(R.string.error_fetch_standings),
                    Toast.LENGTH_SHORT
                ).show()
                binding.progressBar.visibility = View.GONE

            }
        )
    }

    private fun bindElements(player: PlayerDetail) {
        binding.progressBar.visibility = View.GONE
        binding.playerDetailFirstName.text = player.firstName
        binding.playerDetailLastName.text = player.lastName
        binding.playerDetailNationality.text = player.nationality
        binding.playerDetailBirthdate.text = player.birthDate
        if (player.height != null) {
            binding.playerDetailHeight.text = player.height
        } else {
            binding.playerDetailHeight.text = R.string.no_data.toString()
        }
        if (player.weight != null) {
            binding.playerDetailWeight.text = player.weight
        } else {
            binding.playerDetailWeight.text = R.string.no_data.toString()
        }
        binding.playerDetailPosition.text = player.position
        binding.playerDetailAge.text = player.age.toString()
        if (player.position == ("Goalkeeper")) {
            binding.ball.setImageResource(R.drawable.save)
            binding.shoe.setImageResource(R.drawable.goal_conceded)
            binding.playerDetailGoals.text = player.goals.saves.toString()
            binding.playerDetailAssists.text = player.goals.conceded.toString()
        } else {
            binding.playerDetailGoals.text = player.goals.total.toString()
            binding.playerDetailAssists.text = player.goals.assists.toString()
        }
        binding.playerDetailYellow.text = player.cards.yellow.toString()
        binding.playerDetailRed.text = player.cards.red.toString()
        binding.playerDetailAppearances.text = player.games.appearances.toString()
        binding.playerDetailMinutes.text = player.games.minutesPlayed.toString()
    }


}