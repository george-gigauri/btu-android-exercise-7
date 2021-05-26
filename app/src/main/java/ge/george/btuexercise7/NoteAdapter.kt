package ge.george.btuexercise7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val listener: OnNoteDeleteListener
) : ListAdapter<String, NoteAdapter.VH>(diffUtil) {

    inner class VH(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textNote: TextView = itemView.findViewById(R.id.tvNote)
        val btnDelete: ImageView = itemView.findViewById(R.id.ivDelete)

        fun bind(text: String) {
            textNote.text = text
            btnDelete.setOnClickListener { listener.onDelete(text) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnNoteDeleteListener {
        fun onDelete(text: String)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }
}