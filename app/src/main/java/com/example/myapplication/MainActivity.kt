package com.example.myapplication
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.myapplication.dao.UsuarioDao
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var db:AppDatabase
    private lateinit var usuarioDao: UsuarioDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ---  ---

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "db_usuarios"
        ).fallbackToDestructiveMigration().build()
        binding.buttonCadastrar.setOnClickListener() {
            val primeiroNome = binding.nameLabelTxt.text.toString()
            val sobrenone = binding.sobrenomeLabelTxt.text.toString()
            val usuario = Usuario(0, primeiroNome, sobrenone)
            lifecycleScope.launch {
                usuarioDao.inserirUsuario(usuario)
                Log.i("DB", "Cadastrado com Sucesso!")
            }
            binding.nameLabelTxt.text.clear();
            binding.sobrenomeLabelTxt.text.clear();

            binding.btnListaUsers.setOnClickListener() {
                lifecycleScope.launch {
                    val listaUsuarios = withContext(Dispatchers.IO) {
                        usuarioDao.buscarTodos()
                    }
                    listaUsuarios.forEach() {
                        Log.i("Usuário: ", "${it.uid} - Nome Completo: ${it.primeiroNome + it.ultimoNome}")
                    }
                }
            }
        }

    }
}