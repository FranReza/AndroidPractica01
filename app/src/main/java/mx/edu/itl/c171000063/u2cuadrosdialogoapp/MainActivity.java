/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*            Clase que despliega distintos cuadros de mensaje y dialgo basico
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Pedro Francisco Reza Jara     171000063
:*  Fecha       : 16/Marzo/2021
:*  Compilador  : Android Studio 4.0.1
:*  Descripci�n: Los diálogos de alerta a presentar serán los siguientes:
:*
:* 	    Toast de duracion corta
:* 	    Toast de duracion larga
:* 	    Mensaje Snack de duración corta
:* 	    Cuadro de dialogo simple
:* 	    Cuadro de dialogo con botón OK
:* 	    Cuadro de dialogo con botón OK y Cancelar
:* 	    Cuadro de dialogo con lista de opciones
:* 	    Cuadro de dialogo con lista de opciones selección única
:* 	    Cuadro de dialogo con lista de opciones selección multiple
:* 	    Cuadro de dialogo con un diseño de usuario y contraseña incrustado
:* 	    Cuadro de dialogo Acerca de
:*
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c171000063.u2cuadrosdialogoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //-----------------------------------------------------------------------------------

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_main);
    }

    //-----------------------------------------------------------------------------------
    //metodo que devuelve un toast corto

    public void btnToastCortoClick( View v ){
        Toast.makeText( this, "Toast corto", Toast.LENGTH_SHORT ).show();
    }

    //-----------------------------------------------------------------------------------
    //metodo que devuelve un toast largo

    public void btnToastLargoClick( View v ){
        Toast.makeText( this, "Toast Largo", Toast.LENGTH_LONG ).show();
    }

    //-----------------------------------------------------------------------------------
    //metodo que devuelve un snack simple

    public void bntSnackClick( View v ){
        LinearLayout linearLayout = ( LinearLayout ) findViewById ( R.id.raiz );
        Snackbar.make( linearLayout, "esto es un snack", Snackbar.LENGTH_SHORT ).show();
    }

    //-----------------------------------------------------------------------------------
    //metodo que devuelve un dialogo basico

    public void btnDialogoBasicoClick( View v ){
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setMessage( "Cuadro de dialogo basico" ).create().show();
    }

    //-----------------------------------------------------------------------------------
    //metodo que devuelve un dialogo basico con boton ok

    public void btnDialogoBasicoOKClick( View v ) {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( "Android" ).setMessage( "Dialogo basico con boton OK" ).setPositiveButton( "Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                mostrartoast( "click en aceptar" );
            }
        }).create().show();
    }

    //-----------------------------------------------------------------------------------
    //metodo que devuelve un dialogo basico con boton ok y cancel

    public void btnDialogoBasicoOKCANCELClick( View v ){
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( "Android" ).setMessage( "Dialogo basico con boton OK y CANCEL" ).setPositiveButton( "Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                mostrartoast( "click en aceptar" );
            }
        }).setNegativeButton( "Boton Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                dialog.dismiss();
            }
        }).create().show();
    }

    //-----------------------------------------------------------------------------------
    //metodo que devuelve un toast simple pasandole de parametro un mensaje string

    public void mostrartoast( String mensaje ){
        Toast.makeText( this, mensaje, Toast.LENGTH_SHORT ).show();
    }

    //-----------------------------------------------------------------------------------
    //metodo que devuelve un dialogo de lista basica, que podemos escoger y devuelve un toast mostrando el resultado

    private CharSequence colores [] = { "verde", "blanco", "colorado" };
    public void btnDialogoListaBasicaClick( View v ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle( "Escoge un color bonito: " ).setItems( colores, new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                mostrartoast( "color seleccionado fue "+ colores[ which ] );
            }
        }).create().show();
    }

    //-----------------------------------------------------------------------------------
    //metodo que devuelve un dialogo de seleccion unica escogiendo un color y mostrando un toast

    int iColorFavorito = 2; //por default es el rojo
    public void btnDialogoListaSeleccionUnicaClick( View v ){
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( "Escoge tu color Favorito: " ).setSingleChoiceItems( colores, iColorFavorito, new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                iColorFavorito = which;
                mostrartoast( "Escogio: "+ colores [ which ] );
            }
        }).setPositiveButton( "Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                mostrartoast( "Nuevo color Favorito seleccionado es: "+ colores[ iColorFavorito ]);
            }
        }).create().show();
    }

    //-----------------------------------------------------------------------------------
    //metodo que devuelve un dialogo de seleccion multiple escogiendo varios colores

    private boolean coloresSeleccionados [] = { false, false, false };
    private ArrayList< String > coloresFavoritos = new ArrayList<>();
    public void btnDialogoListaSeleccionMultipleClick( View v ){
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( "Seleccione sus colores favoritos" ).setMultiChoiceItems( colores, coloresSeleccionados, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if ( isChecked ) {
                    mostrartoast( "Color seleccionado: "+ colores[ which ] );
                    coloresFavoritos.add( colores[ which ].toString() );
                } else {
                    mostrartoast( "Color deseleccionado: "+ colores[which] );
                    coloresFavoritos.remove(colores [ which ].toString());
                }
            }
        }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                mostrartoast("Colores Favoritos"+ coloresFavoritos );
            }
        }).create().show();
    }

    //-----------------------------------------------------------------------------------
    //metodo que devuelve un dialogo con layout incrustado para un inicio de sesion

    private View login_layout;
    private EditText edtUsuario;
    private EditText edtContraseña;
    public void btnDialogoLayoutIncrustadoClick( View v ){
        //obtener la instancia del layout de login y de sus campos usuario y contraseña
        login_layout = getLayoutInflater().inflate(R.layout.login_layout, null);
        edtUsuario = login_layout.findViewById(R.id.edtNombre);
        edtContraseña = login_layout.findViewById(R.id.edtContraseña);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle( "Acceso" ).setIcon( R.drawable.itl ).setView( login_layout ).setPositiveButton("Entrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                mostrartoast("Bienvenido: "+ edtUsuario.getText().toString() +" Pass: "+ edtContraseña.getText().toString());
            }
        }).setNegativeButton("salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                dialog.dismiss(); //cerrar el dialogo sin hacer nada
            }
        }).setCancelable( false ).create().show();
    }

    //-----------------------------------------------------------------------------------
    //metodo que devuelve un dialogo que muestra informacion de la aplicacion, datos del programador etc.

    public void btnAcercaDeClick( View v ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this );
        builder.setTitle( "Acerca de" ).setIcon( R.drawable.itl ).setMessage( "CuadrosDialogoApp v1.0 \n"+
                "TecNM Campus La Laguna \n"+ "Por: Pedro Francisco Reza Jara ( 171000063 ) \n" +
                "( C ) Derechos Reservados 2021. \n"+ "Torreon Coah. Mexico" ).create().show();
    }

    //-----------------------------------------------------------------------------------
}