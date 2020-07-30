package com.example.android.forzasheets.ui.teamDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.forzasheets.R
import com.example.android.forzasheets.models.player.team.PlayerTeam

class TeamDetailsPlayersAdapter(
    private var players: MutableList<PlayerTeam>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<TeamDetailsPlayersAdapter.TeamDetailsPlayerViewHolder>() {

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: TeamDetailsPlayerViewHolder, position: Int) {
        val item = players[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item.id.toString())
        }
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamDetailsPlayerViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.team_detail_player_item, parent, false)
        return TeamDetailsPlayerViewHolder(view)
    }

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

    class OnClickListener(val clickListener: (playerId: String) -> Unit) {
        fun onClick(playerId: String) = clickListener(playerId)
    }
}