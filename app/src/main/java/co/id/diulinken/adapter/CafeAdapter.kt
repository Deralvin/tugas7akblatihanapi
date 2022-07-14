package co.id.diulinken.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.id.diulinken.R
import co.id.diulinken.model.Datum

class CafeAdapter(cafe: List<Datum>): RecyclerView.Adapter<CafeAdapter.ViewHolder>() {
    private val cafe = cafe

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_cafe, parent, false)
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val people: Datum = cafe[position]
        holder.peopleName.text = people.nama
        holder.peopleGender.text = people.alamat
        holder.peopleBirthyear.text = people.titikKoordinat.toString()
    }

    override fun getItemCount(): Int {
        return cafe.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val peopleName: TextView = view.findViewById(R.id.people_name)
        val peopleGender: TextView = view.findViewById(R.id.people_gender)
        val peopleBirthyear: TextView = view.findViewById(R.id.people_birthyear)
        val height: TextView = view.findViewById(R.id.height)
        val mass: TextView = view.findViewById(R.id.mass)
        val hairColor: TextView = view.findViewById(R.id.hair_color)
        val skinColor: TextView = view.findViewById(R.id.skin_color)
        val eyeColor: TextView = view.findViewById(R.id.eye_color)
    }
}