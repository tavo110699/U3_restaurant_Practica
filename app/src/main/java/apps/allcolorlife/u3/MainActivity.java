package apps.allcolorlife.u3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Asignar();
        list();

    }

    public void Asignar(){

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        // gracias a la id, sabemos que item es el que se oprime, en este caso usamos un switch
        switch (item.getItemId())
        {
            case R.id.idMenuMainSearch:
                //presiono em item1
                Toast.makeText(this,"Buscar",Toast.LENGTH_LONG).show();

                return true;
            case R.id.idMenuMainContact:
                AlertContact();
                //presiono em item2
                return true;
            case R.id.idMenuMainAbout:
                //presiono em item3
                AlertAbout();
                return true;
            case R.id.idMenuMainExit:
                //presiono em item4
                AlertExit();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);

    }

    public void  list(){

        ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();

        datos.add(new Lista_entrada(R.drawable.pizza_image, "Pizza", "Creado a partir de un pan chato, con forma de disco, amasado con agua, sal, levadura y harina. Dicho pan se cubre con salsa de tomate y, tras una primera etapa en el horno, se le añade queso y casi cualquier otro ingrediente que uno desee."));
        datos.add(new Lista_entrada(R.drawable.burguer_image, "Hamburguesa", " hamburguesa de ternera con cebolla confitada, agregando queso manchego y oaxacaacompañado de sus papas fritas y refresco embotellado"));
        datos.add(new Lista_entrada(R.drawable.hotdog_image, "Hot Dog", " salchicha tipo Frankfurt frita servida en un pan con forma alargada acompañada de aderezo, salsa de tomate, mostaza y cebolla"));
        datos.add(new Lista_entrada(R.drawable.sandwich_image, "Club Sandwich", "Emparedado de 2 niveles preparado con Pollo deshebrado, jamon, queso oaxaca y tomate acompañado de papas a la francesa, ensalada verde y Agua fresca de sabor Jamaica"));
        datos.add(new Lista_entrada(R.drawable.tacos_image, "Tacos", "Tortillas de maiz acompañadas de su carne marinada al pastor a base de chiles, achiote, jugo de piña y algunas especias"));
        datos.add(new Lista_entrada(R.drawable.quesadilla_image, "Quesadillas", "Tortilla de maiz doblada por la mitad rellenas de queso tipo Oaxaca y jugosa carne de res de primera."));
        datos.add(new Lista_entrada(R.drawable.boneless_image, "Boneless", "Pechuga de pollo cubierta en una crujiente cubierta casera, su tamaño es más que generoso para satisfacer tú apetito. Las papas corte recto se transforman a especiales al agregarles un baño de queso cheddar y el chili con la receta de la casa. Nuestro “famoso” elote frito es una delicia, se cubre de ruffles, parmesano, chipotle y perejil."));
        datos.add(new Lista_entrada(R.drawable.chili_image, "Chili con queso", "Aperitivo realizado queso Velveeta mezclado con chile jalapeño acompañado de sus tostadas tipo totopos."));

        lista = (ListView) findViewById(R.id.idListViewItems);
        lista.setAdapter(new Lista_adaptador(this, R.layout.entrada, datos){
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((Lista_entrada) entrada).get_textoEncima());

                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior);
                    if (texto_inferior_entrada != null)
                        texto_inferior_entrada.setText(((Lista_entrada) entrada).get_textoDebajo());

                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
                    if (imagen_entrada != null)
                        imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());
                }
            }
        });
// se agrego en automatico AdapterView. puede que mande error
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion);

                CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
                Toast toast = Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG);
                toast.show();
            }
        });


    }

    public void AlertExit(){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("¿Desea Salir?")
                .setMessage("Se perderan todas tus configuraciones de compras")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        finish();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void AlertContact(){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Contacto")
                .setMessage("Llama al restaurante con los numeros telefonicos: " +
                        "\n7820166126 \n7399085987 " +
                        "\n\no al correo electronico:" +
                        "\nblorem@blorem.com" +
                        "\n \n Uno de nuestro equipo te atendera con mucho gusto")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        dialog.cancel();
                    }
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    public void AlertAbout(){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Acerca de")
                .setMessage("aplicacion desarrollada por:" +
                        "\nGustavo Martinez Licona" +
                        "\n\nMateria: \nDesarrollo y aplicacion para dispositivos moviles")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        dialog.cancel();
                    }
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}


