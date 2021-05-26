package ge.george.btuexercise7

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NoteAdapter.OnNoteDeleteListener {

    private lateinit var etNote: EditText
    private lateinit var btnSave: Button
    private lateinit var btnClear: Button
    private lateinit var rv: RecyclerView
    private val adapter: NoteAdapter = NoteAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        loadData()

        btnSave.setOnClickListener { save() }
        btnClear.setOnClickListener { Util.deleteAll(); loadData() }
    }

    private fun init() {
        Util.getInstance(this)
        etNote = findViewById(R.id.etEnterNote)
        btnSave = findViewById(R.id.btnSave)
        btnClear = findViewById(R.id.btnClear)
        rv = findViewById(R.id.rvNotes)

        rv.adapter = adapter
    }

    private fun loadData() {
        val data = Util.getAll()
        adapter.submitList(data)
        adapter.notifyDataSetChanged()

        Util.listen {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun save() {
        val note = etNote.text.toString()
        if (note.isNotEmpty())
            Util.save(note)
    }

    override fun onDelete(text: String) {
        Util.delete(text)
    }
}