package mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles.costo
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles.fecha
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles.paciente
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles.servicio
import java.io.InputStreamReader

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    val images = intArrayOf(R.drawable.contento,
        R.drawable.contento,
        R.drawable.contento,
        R.drawable.contento,
        R.drawable.contento,
        R.drawable.contento,
        R.drawable.contento,
        R.drawable.contento,
        R.drawable.contento,
        R.drawable.contento,
        R.drawable.contento)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, j: Int) {
            viewHolder.itemTitle.text = paciente[j]
            viewHolder.itemDetail1.text = servicio[j]
            viewHolder.itemDetail2.text = fecha[j]
            viewHolder.itemDetail3.text = costo[j]
            viewHolder.itemImage.setImageResource(images[j])

    }

    override fun getItemCount(): Int {
        return 15
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail1: TextView
        var itemDetail2: TextView
        var itemDetail3: TextView
        init{
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail1 = itemView.findViewById(R.id.item_detal1)
            itemDetail2 = itemView.findViewById(R.id.item_detal2)
            itemDetail3 = itemView.findViewById(R.id.item_detal3)

        }
    }
}