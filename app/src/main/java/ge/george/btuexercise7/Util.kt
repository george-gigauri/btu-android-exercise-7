package ge.george.btuexercise7

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object Util {

    private var sharedPreferences: SharedPreferences? = null

    fun getInstance(context: Context) {
        sharedPreferences = context.getSharedPreferences("Notes", MODE_PRIVATE)
    }

    val SharedPreferences.editor: SharedPreferences.Editor?
        get() = sharedPreferences?.edit()

    fun listen(onUpdate: (data: List<String>) -> Unit) {
        sharedPreferences?.let {
            it.registerOnSharedPreferenceChangeListener { _, _ -> onUpdate(getAll()) }
        }
    }

    fun save(text: String) {
        val notes = getAll()
        notes.let {
            notes.add(text)
            val editor = sharedPreferences?.editor?.apply {
                putStringSet("my_notes", notes.toSet())
            }
            editor?.apply()
        }
    }

    fun getAll(): MutableList<String> {
        val result = sharedPreferences?.getStringSet("my_notes", setOf())
        return result?.toMutableList() ?: mutableListOf()
    }

    fun delete(text: String) {
        val notes = getAll()
        notes.let {
            it.remove(text)
            val editor = sharedPreferences?.editor?.apply {
                putStringSet("my_notes", notes.toSet())
            }
            editor?.apply()
        }
    }

    fun deleteAll() {
        sharedPreferences?.edit()?.apply {
            clear()
            apply()
        }
    }
}