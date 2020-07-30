package com.example.android.forzasheets.ui.teamDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.android.forzasheets.MainActivity
import com.example.android.forzasheets.R
import com.example.android.forzasheets.database.ForzaSheetsDatabase
import com.example.android.forzasheets.databinding.FragmentTeamDetailBinding
import com.example.android.forzasheets.helper.InflateDatabase
import com.example.android.forzasheets.models.team.Team


class TeamDetailFragment : Fragment() {

    private lateinit var binding: FragmentTeamDetailBinding
    private lateinit var viewModel: TeamDetailsViewModel
    private lateinit var flags: BooleanArray
    private lateinit var teamId: String
    private lateinit var teamLogo: String
    private lateinit var adapter: TeamDetailsPlayersAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var teamForRoom: Team

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (this.activity as MainActivity).supportActionBar!!.title = "Team Details"
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_team_detail,
            container,
            false
        )
        flags = booleanArrayOf(false, false)
        teamId = TeamDetailFragmentArgs.fromBundle(requireArguments()).teamId

        val viewModelFactory = TeamDetailsViewModelFactory(teamId)
        viewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(TeamDetailsViewModel::class.java)


        layoutManager = LinearLayoutManager(
            this.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.playersList.layoutManager = layoutManager
        adapter =
            TeamDetailsPlayersAdapter(mutableListOf(), TeamDetailsPlayersAdapter.OnClickListener {
                if (null != it) {
                    this.findNavController()
                        .navigate(
                            TeamDetailFragmentDirections.actionTeamDetailFragmentToPlayerDetailsFragment(
                                it,
                                teamLogo
                            )
                        )
                }
            })
        binding.playersList.adapter = adapter

        binding.progressBar.visibility = View.VISIBLE
        getTeamDetails()
        getAllPlayersFromTeam()
        return binding.root
    }

    private fun bindElements(team: Team) {
        binding.teamName.text = team.name
        binding.teamCountry.text = team.country
        binding.teamFounded.text = team.founded.toString()
        binding.teamVenueCapacity.text = team.venueCapacity.toString()
        binding.teamVenueName.text = team.venueName
        binding.teamVenueCity.text = team.venueCity
        teamLogo = team.logo
        Glide.with(this)
            .load(team.logo)
            .transform(CenterCrop())
            .into(binding.teamLogo)
    }

    private fun getTeamDetails() {
        viewModel.getTeamDetails(
            viewModel.selectedTeamId.value!!,
            onSuccess = { team ->
                bindElements(team)
                flags[0] = true
                if (flags.all { f -> f }) {
                    binding.progressBar.visibility = View.GONE
                }

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

    private fun getAllPlayersFromTeam() {
        viewModel.getAllPlayersFromTeam(
            teamId,
            onSuccess = { players ->
                val playersWithoutNull =
                    players.filter { playerTeam -> playerTeam.position != null }
                adapter.appendPlayers(playersWithoutNull)
                flags[1] = true
                if (flags.all { f -> f }) {
                    binding.progressBar.visibility = View.GONE
                }
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
}