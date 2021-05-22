package fr.epf.min1.gcIsidore


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.gcIsidore.model.Client
import fr.epf.min1.gcIsidore.model.Gender
import kotlinx.android.synthetic.main.client_view.view.*

class ClientAdapter(val clients: List<Client>) : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    class ClientViewHolder(val clientView : View) : RecyclerView.ViewHolder(clientView)

    //    class ClientViewHolder extends ViewHolder {
//          private View view;
//          public ClientViewHolder(View view){
//              super(view);
//              this.view = view;
//          }
//    }

    override fun getItemCount()  = clients.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view : View =
            inflater.inflate(R.layout.client_view, parent, false)
        return ClientViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = clients[position]
        holder.clientView.client_name_textview.text =
            "${client.firtsname} ${client.lastname}"

//        val resId = when (client.gender) {
//            Gender.Woman -> R.drawable.woman
//            Gender.Man -> R.drawable.man
//        }

        holder.clientView.client_imageview.setImageResource(client.getPicture())

        holder.clientView.setOnClickListener {
            val intent = Intent(it.context, DetailsClientActivity::class.java)
            intent.putExtra("id", client)
            it.context.startActivity(intent)
        }

    }




}