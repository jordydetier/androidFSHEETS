package com.example.android.forzasheets.ui.playerDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.android.forzasheets.MainActivity
import com.example.android.forzasheets.R
import com.example.android.forzasheets.databinding.FragmentPlayerDetailsBinding
import com.example.android.forzasheets.models.player.detail.PlayerDetail


class PlayerDetailsFragment : Fragment() {

    /**
     * Binding object to access the fragment's layout
     */
    private lateinit var binding: FragmentPlayerDetailsBinding

    /**
     * Id of the player to get the correct data from the API
     */
    private lateinit var playerId: String
    /**
     * Image-URL to show the team crest of the team the player plays for
     */
    private lateinit var teamLogo: String
    /**
     * ViewModel which contains the business-logic
     */
    private lateinit var viewModel: PlayerDetailsViewModel

    /**
     * Boolean to check if a loading spinner should be shown or not
     */
    private var flag: Boolean = false


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
        (this.activity as MainActivity).supportActionBar!!.title = "Player Details"
        playerId = PlayerDetailsFragmentArgs.fromBundle(requireArguments()).playerId
        teamLogo = PlayerDetailsFragmentArgs.fromBundle(requireArguments()).teamLogo


        val viewModelFactory = PlayerDetailsViewModelFactory(playerId)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(PlayerDetailsViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_player_details,
            container,
            false
        )
        binding.progressBarPlayerDetail.visibility = View.VISIBLE
        getPlayerDetails()
        return binding.root
    }

    /**
     * Retrieves a PlayerDetail object and gives that to the bindElements function. Uses Higher order functions to validate success and error
     *
     */
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
                binding.progressBarPlayerDetail.visibility = View.GONE

            }
        )
    }

    /**
     * Binds all the elements with data from the chosen player
     *
     */
    private fun bindElements(player: PlayerDetail) {
        binding.progressBarPlayerDetail.visibility = View.GONE
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