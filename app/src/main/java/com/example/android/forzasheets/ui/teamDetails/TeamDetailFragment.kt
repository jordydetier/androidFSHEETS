package com.example.android.forzasheets.ui.teamDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.android.forzasheets.MainActivity
import com.example.android.forzasheets.R
import com.example.android.forzasheets.databinding.FragmentTeamDetailBinding
import com.example.android.forzasheets.models.team.Team


class TeamDetailFragment : Fragment() {

    /**
     * Binding object to access the fragment's layout
     */
    private lateinit var binding: FragmentTeamDetailBinding

    /**
     * ViewModel which contains the business-logic
     */
    private lateinit var viewModel: TeamDetailsViewModel

    /**
     * Array of booleans to check if a loading spinner should be shown or not
     */
    private lateinit var flags: BooleanArray

    /**
     * Id of the team to get the correct data from the API
     */
    private lateinit var teamId: String

    /**
     * Image-URL to show the team crest of the team
     */
    private lateinit var teamLogo: String

    /**
     * Holds a reference to the StandingsAdapter
     */
    private lateinit var adapter: TeamDetailsPlayersAdapter

    /**
     * Holds a reference to the LinearLayoutManager
     */
    private lateinit var layoutManager: LinearLayoutManager

    /**
     * Inflates the fragment's view, overridden from Fragment.java
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI
     */
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
        viewModel = ViewModelProvider(
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
                if (it != null) {
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

        binding.progressBarTeamDetail.visibility = View.VISIBLE
        getTeamDetails()
        getAllPlayersFromTeam()
        return binding.root
    }

    /**
     * Binds all the elements with data from the chosen team
     *
     */
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

    /**
     * Retrieves a Team object and gives that to the bindElements function. Uses Higher order functions to validate success and error
     *
     */
    private fun getTeamDetails() {
        viewModel.getTeamDetails(
            viewModel.selectedTeamId.value!!,
            onSuccess = { team ->
                bindElements(team)
                flags[0] = true
                if (flags.all { f -> f }) {
                    binding.progressBarTeamDetail.visibility = View.GONE
                }

            },
            onError = {
                Toast.makeText(
                    this.context,
                    getString(R.string.error_fetch_standings),
                    Toast.LENGTH_SHORT
                ).show()
                binding.progressBarTeamDetail.visibility = View.GONE

            }
        )
    }

    /**
     * Retrieves a list of players from the selected team and appends those to the adapter. Uses Higher order functions to validate success and error
     *
     */
    private fun getAllPlayersFromTeam() {
        viewModel.getAllPlayersFromTeam(
            teamId,
            onSuccess = { players ->
                val playersWithoutNull =
                    players.filter { playerTeam -> playerTeam.position != null }
                adapter.appendPlayers(playersWithoutNull)
                flags[1] = true
                if (flags.all { f -> f }) {
                    binding.progressBarTeamDetail.visibility = View.GONE
                }
            },
            onError = {
                Toast.makeText(
                    this.context,
                    getString(R.string.error_fetch_standings),
                    Toast.LENGTH_SHORT
                ).show()
                binding.progressBarTeamDetail.visibility = View.GONE

            }
        )
    }
}