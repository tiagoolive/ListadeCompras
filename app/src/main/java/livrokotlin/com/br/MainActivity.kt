package livrokotlin.com.br

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Implementação do adaptador
        val produtosAdapter = ProdutoAdapter(this)


        //definindo o adaptador na lista
        list_view_produtos.adapter = produtosAdapter

        btn_adicionar.setOnClickListener{
            //Criando a intent explícita
            val intent = Intent(this, CadastroActivity::class.java)

            //iniciando a atividade
            startActivity(intent)
        }

        list_view_produtos.setOnItemLongClickListener{ adapterView: AdapterView<*>, view, position: Int, id: Long ->

            //buscando o item clicado
            val item = produtosAdapter.getItem(position)

            //removendo o item clicado da lista
            produtosAdapter.remove(item)

            //retorno indicando que o click foi realizado com sucesso
            true
        }


    }
    override fun onResume() {
        super.onResume()

        val adapter =list_view_produtos.adapter as ProdutoAdapter

        adapter.clear()
        adapter.addAll(produtosGlobal)

        val soma = produtosGlobal.sumByDouble { it.valor * it.quantidade }
        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        txt_total.text = "TOTAL: ${ f.format(soma)}"
    }



}
