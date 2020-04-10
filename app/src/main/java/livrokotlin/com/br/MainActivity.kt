package livrokotlin.com.br

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Implementação do adaptador
        val produtosAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        //definindo o adaptador na lista
        list_view_produtos.adapter = produtosAdapter

        //definição do ouvinte do botão
        btn_inserir.setOnClickListener{

            //pegando o valor digitado pelo usuário
            val produto = txt_produto.text.toString()

            //enviando o item para oa lista
            //produtosAdapter.add(produto)

            //verificando se o usuário digitou algum valor
            if(produto.isNotEmpty()) {
                //enviando o item para a lista
                produtosAdapter.add(produto)

                //limpando a caixa de texto
                txt_produto.text.clear()

            }else{
                txt_produto.error = "Preencha um valor"
            }
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
}
