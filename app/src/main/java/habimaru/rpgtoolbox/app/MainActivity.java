package habimaru.rpgtoolbox.app;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements
        ActionBar.OnNavigationListener {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
    Ficha ficha = new Ficha();
    LanzaDados lanzaDados = new LanzaDados();


    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    /**
     * Método método onCreate, que nos genera el spinner
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        final String[] dropdownValues = getResources().getStringArray(R.array.dropdown);

        // Specify a SpinnerAdapter to populate the dropdown list.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(actionBar.getThemedContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1,
                dropdownValues);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(adapter, this);

        // use getActionBar().getThemedContext() to ensure
        // that the text color is always appropriate for the action bar
        // background rather than the activity background.
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    /**
     * Decidimos cuál de nuestros fragmentos cargamos
     * @param position
     * @param id
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        // When the given dropdown item is selected, show its contents in the
        // container view.
        Bundle args;
    switch (position){
        case 0:

            args = new Bundle();
            //args.putInt(Fragment.ARG_SECTION_NUMBER, position + 1);
            ficha.setArguments(args);
            getFragmentManager().beginTransaction()
                    .replace(R.id.scrollView, ficha).commit();
            break;
        case 1:

        args = new Bundle();
        //args.putInt(Fragment.ARG_SECTION_NUMBER, position + 1);
        lanzaDados.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.scrollView, lanzaDados).commit();
        break;
        default:

    }
        return true;
    }

    public static class LanzaDados extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_lanza_dados, container, false);
        }
    }
    public static class Ficha extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_ficha, container, false);
        }
    }


    /*****************
     *
     *      MÉTODOS ONCLICK DEL LANZADOR DE DADOS
     *
     */
    /**
     * Genera una serie de números aleatorios en función de las opciones
     * @param v
     */
    public void lanzar(View v){
        int ndados = Integer.parseInt(((TextView) findViewById(R.id.numDados)).getText().toString());
        int ncaras = Integer.parseInt(((TextView) findViewById(R.id.numCaras)).getText().toString());
        String str="Resultados:";
        int rand;
        for(int i=1;i<=ndados;i++){
            rand=(int)(Math.random()*ncaras)+1;

            str=str+"\nTirada "+String.valueOf(i)+": "+String.valueOf(rand);
        }
        TextView t = (TextView) findViewById(R.id.resultadosDados);
        t.setText(str);
    }

    public void menosDados(View v){
        TextView dados = (TextView) findViewById(R.id.numDados);
        if(Integer.parseInt(dados.getText().toString())>1){
        dados.setText(String.valueOf(Integer.parseInt(dados.getText().toString())-1));}
    }

    public void masDados(View v){
        TextView dados = (TextView) findViewById(R.id.numDados);

        if(Integer.parseInt(dados.getText().toString())<201){

        dados.setText(String.valueOf(Integer.parseInt(dados.getText().toString())+1));}
    }

    public void masCaras(View v){
        TextView caras = (TextView) findViewById(R.id.numCaras);
        caras.setText(String.valueOf(Integer.parseInt(caras.getText().toString())+1));
    }

    public void menosCaras(View v){
        TextView caras = (TextView) findViewById(R.id.numCaras);
        if(Integer.parseInt(caras.getText().toString())>1){
            caras.setText(String.valueOf(Integer.parseInt(caras.getText().toString())-1));}
    }


    public void masExp(View v){
        TextView exp = (EditText) findViewById(R.id.textoExp);
        exp.setText(String.valueOf(Integer.parseInt(exp.getText().toString())+1));
    }
    public void menosExp(View v){
        TextView exp = (EditText) findViewById(R.id.textoExp);
        if(Integer.parseInt(exp.getText().toString())>0){
            exp.setText(String.valueOf(Integer.parseInt(exp.getText().toString())-1));}
    }

}
