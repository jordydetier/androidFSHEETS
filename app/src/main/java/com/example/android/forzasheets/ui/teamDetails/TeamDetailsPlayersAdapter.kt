package com.example.android.forzasheets.ui.teamDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.forzasheets.R
import com.example.android.forzasheets.models.player.team.PlayerTeam
import com.example.android.forzasheets.models.standing.Standings

/**
 * Standings' Recyclerview Adapter
 *
 * This adapter is used to load individual list-items
 *
 * @param players the list of players which needs to be loaded
 * @param onClickListener object used to know when and which a PlayerTeam object is clicked
 *
 */
class TeamDetailsPlayersAdapter(
    private var players: MutableList<PlayerTeam>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<TeamDetailsPlayersAdapter.TeamDetailsPlayerViewHolder>() {

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     */
    override fun getItemCount() = players.size

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     */
    override fun onBindViewHolder(holder: TeamDetailsPlayerViewHolder, position: Int) {
        val item = players[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item.id.toString())
        }
        holder.bind(item)
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent
     * an item.
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamDetailsPlayerViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.team_detail_player_item, parent, false)
        return TeamDetailsPlayerViewHolder(view)
    }


    /**
     * Adds a list of [PlayerTeam] to the current list and sorts it by position
     */
    fun appendPlayers(players: List<PlayerTeam>) {
        players.forEach { player ->
            when (player.position) {
                "Goalkeeper" -> {
                    player.positionNumber = 1
                }
                "Defender" -> {
                    player.positionNumber = 2
                }
                "Midfielder" -> {
                    player.positionNumber = 3
                }
                "Attacker" -> {
                    player.positionNumber = 4
                }
                else -> player.positionNumber = 5
            }
        }
        this.players.addAll(players.sortedBy { it.positionNumber })
        notifyItemRangeInserted(
            this.players.size,
            players.size.minus(1)
        )
    }

    /**
     * Used by the Adapter to host individual items
     *
     * @param itemView represents the view of the adapter list item
     */
    inner class TeamDetailsPlayerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val playerPosition: TextView = itemView.findViewById(R.id.player_position)
        private val playerName: TextView = itemView.findViewById(R.id.player_name)
        private val playerAge: TextView = itemView.findViewById(R.id.player_age)
        private val playerNationality: TextView = itemView.findViewById(R.id.player_nationality)
        fun bind(item: PlayerTeam) {
            val playerPositionShort: String
            when (item.position) {
                "Goalkeeper" -> {
                    playerPositionShort = "GK"
                }
                "Defender" -> {
                    playerPositionShort = "DEF"
                }
                "Midfielder" -> {
                    playerPositionShort = "MID"
                }
                "Attacker" -> {
                    playerPositionShort = "ATT"
                }
                else -> playerPositionShort = ""

            }
            playerPosition.text = playerPositionShort
            playerName.text = item.playerName
            playerAge.text = item.age.toString()
            playerNationality.text = item.nationality
        }
    }

    /**
     *
     *  used to know when and which a Standings object is clicked
     */
    class OnClickListener(val clickListener: (playerId: String) -> Unit) {
        fun onClick(playerId: String) = clickListener(playerId)
    }
}