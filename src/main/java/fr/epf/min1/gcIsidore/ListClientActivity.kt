package fr.epf.min1.gcIsidore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import fr.epf.min1.gcIsidore.data.ClientDatabase
import fr.epf.min1.gcIsidore.model.Client
import fr.epf.min1.gcIsidore.model.Gender
import kotlinx.android.synthetic.main.activity_list_client.*
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory



class ListClientActivity : AppCompatActivity() {

    lateinit var clients : MutableList<Client>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_client)

        clients_recyclerview.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

//        val clients = Client.all()

    }

    override fun onStart() {
        super.onStart()
        val database = Room
            .databaseBuilder(this, ClientDatabase::class.java, "db")
            .build()

        val clientDAO = database.getClientDAO()

        runBlocking {
            clients = clientDAO.getAllClients().toMutableList()
            clients_recyclerview.adapter = ClientAdapter(clients)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_clients, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_client_action ->{
                val intent = Intent(this, AddClientActivity::class.java)
                startActivity(intent)
            }

            R.id.sync_action -> {
                synchro()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun synchro() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(RandomUserService::class.java)

        runBlocking {
            val result = service.getClients()
            Log.d("EPF","$result")
            val results = result.results
            results.map {
                Client(null,
                    it.name.last,
                    it.name.first,
                    it.email,
                    20,
                    "",
                    true,
                    if(it.gender == "female") Gender.Woman else Gender.Man
                )
            }.map {
                clients.add(it)
            }

            clients_recyclerview.adapter?.notifyDataSetChanged()

        }
    }
}