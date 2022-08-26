package com.example.sqlitetest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sqlitetest.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        /** PRIMEIRA MANEIRA DE SE TRABALHAR COM SQLITE
        // Criar banco de dados com sqlite. Passar nome BD, modo (PRIVATE), e o cursor factory, como é teste, passa-se como 'null'
        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test", MODE_PRIVATE, null);

        // Criar tabela
        sqLiteDatabase.execSQL("CREATE TABLE contacts(name TEXT, phone INTEGER, email TEXT)");

        // Popular a tabela
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('franz kafka', 4599918-2323, 'kafka@email.com');");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('jean paul saertre', 459923-2323, 'saertre@email.com');");
        */

        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test", MODE_PRIVATE, null);

        // Isso não faz sentido para aplicações reais e/ou mais complexas, pois perderei os dados sempre que eu sair do app.
        // Mas, como é teste, ou seja, como esse app serve só para testar o Sqlite com AndroidStudio, então é válido
        String sql = "DROP TABLE IF EXISTS contacts;";
        sqLiteDatabase.execSQL(sql);
        Log.d(TAG, "onCreate: sql is " + sql);

        sql = "CREATE TABLE IF NOT EXISTS contacts(name TEXT, phone INTEGER, email TEXT);";
        sqLiteDatabase.execSQL(sql);
        Log.d(TAG, "onCreate: sql is " + sql);

        sql = "INSERT INTO contacts VALUES('kafka', 4523232323, 'kafka@gmail.com');";
        sqLiteDatabase.execSQL(sql);
        Log.d(TAG, "onCreate: sql is " + sql);

        sql = "INSERT INTO contacts VALUES('tolstoi', 4123232323, 'tolstoi@gmail.com');";
        sqLiteDatabase.execSQL(sql);
        Log.d(TAG, "onCreate: sql is " + sql);



        // Cursor nativo do AndroidStudio
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM contacts", null);

        // A query é, portanto, um cursor. Aqui mandamos realizar a query para o primeiro registro
        // Este método, de acordo com a documentação, (ctrl + q: com o cursor dentro de ()),
        // se não achar a informação requerida, isto é, a query, então retorna falso.
        if (query.moveToFirst()) {
            // Loop para percorrer todas as colunas da table até esgotar a informação
            do {
                String name = query.getString(0);
                int phone = query.getInt(1);
                String email = query.getString(2);
                // Retornar uma simples toaster message para testar o Sqlite
                Toast.makeText(this, "Name = " +name + " phone = " + phone + " email" + email, Toast.LENGTH_LONG).show();
            } while (query.moveToNext());

            // Fechar o cursor query: trata-se de boa prática, pois, libera recursos e é possível, então, realizar o "garbage collector" pelo JVM.
            query.close();
            // Fechar BD: fechar o BD aqui, não é boa prática. Isso normalmente é feito ao fechar o aplicativo para.
            // Porém, como esse código serve para testar o Sqlite, fecho aqui mesmo.
            sqLiteDatabase.close();

        }

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}