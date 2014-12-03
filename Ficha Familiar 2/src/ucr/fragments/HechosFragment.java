package ucr.fragments;

import java.util.ArrayList;

import ucr.database.SQLiteAdapter;
import ucr.ff.R;
import ucr.tools.Validation;


import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

@SuppressWarnings("unused")
public class HechosFragment extends Fragment{

	private View view;

	private TextView txtTituloHechos;
	private TextView txtCodPer;
	private TextView txtEspec;
	private TextView txtSaludAct;
	private TextView txtTipoViol;
	private TextView txtFechaPlan;
	private TextView txtTipoPlan;
	private TextView txtFechaPap;
	private TextView txtEstPap;
	private TextView txtFechaEmb;
	private TextView txtFechaVac;
	private TextView txtTipoVac;
	private TextView txtListaVacRec;
	private TextView txtObservaciones;

    private static int codPer;
    private static String fechaPlan;
    private static String fechaPap;
    private static String observ;
    private static String fechaUltReg;
    private static String saludActual;
    private static String tipoPlan;
    private static String tipoViol;
    private static String espec;

    private SQLiteAdapter adapter;
	
	EditText editCodPer;
	EditText editEspec;
	EditText editFechaPlan;
	EditText editFechaPap;
	EditText editFechaEmb;
	EditText editFechaVac;
	EditText editObservaciones;
	
	Spinner spinnerSaludAct;
	Spinner spinnerTipoViol;
	Spinner spinnerTipoPlan;
	Spinner spinnerEstPap; //no esta en la tabla condsaludm
	Spinner spinnerTipoVac;
	Spinner spinnerListaVacRec;


    private static String tipo_Estados[] = {"B","R","M"};
    private static String tipo_Violencia[] = {"N","F","P"};
    private static String tipo_Planificacion[] = {"N","D","P","I","C"};

//	Button  butAgVac;
//	Button  butElimVac;
	Button  searchButton;
    Button  insertButton;
	Button  updateButton;

    private Validation validation;
//    private boolean preloaded_data;
    private ContentValues values;

	private ListView viewListaVacunas;
	
	public void createTextViews() {
		txtTituloHechos = (TextView) view.findViewById(R.id.txtTituloHechos);
		txtCodPer = (TextView) view.findViewById(R.id.txtCodPer);
		txtEspec = (TextView) view.findViewById(R.id.txtEspec);
		txtSaludAct = (TextView) view.findViewById(R.id.txtSaludAct);
		txtTipoViol = (TextView) view.findViewById(R.id.txtTipoViol);
		txtFechaPlan = (TextView) view.findViewById(R.id.txtFechaPlan);
		txtTipoPlan = (TextView) view.findViewById(R.id.txtTipoPlan);
		txtFechaPap = (TextView) view.findViewById(R.id.txtFechaPap);
		txtEstPap = (TextView) view.findViewById(R.id.txtEstPap);
		txtFechaEmb = (TextView) view.findViewById(R.id.txtFechaEmb);
		txtObservaciones = (TextView) view.findViewById(R.id.txtObservaciones);				
	}
	
	private void createSpinners() {
		spinnerSaludAct = (Spinner) view.findViewById(R.id.spinnerSaludAct);
        ArrayAdapter<CharSequence> lista_SaludAct = 
        		ArrayAdapter.createFromResource(view.getContext(),R.array.condSalud,android.R.layout.simple_spinner_item);
        lista_SaludAct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSaludAct.setAdapter(lista_SaludAct);	  
        spinnerSaludAct.setSelection(0, true);		
		
		spinnerTipoViol = (Spinner) view.findViewById(R.id.spinnerTipoViol);
        ArrayAdapter<CharSequence> lista_TipoViol = 
        		ArrayAdapter.createFromResource(view.getContext(),R.array.violIntrafamiliar,android.R.layout.simple_spinner_item);
        lista_TipoViol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoViol.setAdapter(lista_TipoViol);	  
        spinnerTipoViol.setSelection(0, true);
		
		spinnerTipoPlan = (Spinner) view.findViewById(R.id.spinnerTipoPlan);
        ArrayAdapter<CharSequence> lista_TipoPlan = 
        		ArrayAdapter.createFromResource(view.getContext(),R.array.planificacion,android.R.layout.simple_spinner_item);
        lista_TipoViol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoPlan.setAdapter(lista_TipoPlan);	  
        spinnerTipoPlan.setSelection(0, true);
        spinnerTipoPlan.setEnabled(false);
		
		spinnerEstPap = (Spinner) view.findViewById(R.id.spinnerEstPap);
        ArrayAdapter<CharSequence> lista_EstPap = 
        		ArrayAdapter.createFromResource(view.getContext(),R.array.resultPap,android.R.layout.simple_spinner_item);
        lista_TipoViol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstPap.setAdapter(lista_EstPap);	  
        spinnerEstPap.setSelection(0, true);
        spinnerEstPap.setEnabled(false);
		
		//spinnerTipoVac = (Spinner) view.findViewById(R.id.spinnerTipoVac);
	}
	
	private void spinnerFunctions() {
		spinnerSaludAct.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerSaludAct.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});	
		
		
		spinnerTipoViol.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerTipoViol.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});	
		
		spinnerTipoPlan.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerTipoPlan.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});	
		
		spinnerEstPap.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				spinnerEstPap.setBackgroundColor(getResources().getColor(R.color.lightorange));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});	
	}
	
	private void createEditTexts() {
		editCodPer = (EditText) view.findViewById(R.id.editCodPer);
		editEspec =  (EditText) view.findViewById(R.id.editEspec);
		editFechaPlan =  (EditText) view.findViewById(R.id.editFechaPlan);
		editFechaPlan.setEnabled(false);
		editFechaPap =  (EditText) view.findViewById(R.id.editFechaPap);
		editFechaPap.setEnabled(false);
		editObservaciones = (EditText) view.findViewById(R.id.editObservaciones);
	}
	
	private void editTextFunctions() {
		editEspec.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					editEspec.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (editEspec.getText().toString().isEmpty()) {
						editEspec.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						editEspec.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
		
		editObservaciones.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					editObservaciones.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (editObservaciones.getText().toString().isEmpty()) {
						editObservaciones.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						editObservaciones.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
	}
	
	private void createButtons() {
		
		//butAgVac = (Button) view.findViewById(R.id.butAgVac);
		//butElimVac = (Button) view.findViewById(R.id.butElimVac);
//		searchButton =  (Button) view.findViewById(R.id.searchButton);
		insertButton =  (Button) view.findViewById(R.id.insertButton);
		updateButton =  (Button) view.findViewById(R.id.updateButton);
	}
	
	public HechosFragment() {
		super();		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Prueba", "HechosFragment");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.hechos_tab, null);
        adapter = new SQLiteAdapter(view.getContext());
		createWidgets();
		spinnerFunctions();
		buttonFunctions();
		return view;
	}
	
	
	public ArrayList<String> demo() {
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.add("DD/MM/AAAA");
		tmp.add("Vacuna aplicada en ese día");
		return tmp;
	}
	
	public ArrayAdapter<ArrayList<String>> datos(int valor) {
		ArrayAdapter<ArrayList<String>> tmp = new ArrayAdapter<ArrayList<String>>(view.getContext(), android.R.layout.simple_list_item_1);
		for (int i = 0; i < valor; ++i) {
			ArrayList<String> tmp1 = new ArrayList<String>();
			tmp1 = demo();
			tmp.add(tmp1);
		}
		return tmp;
	}
	
	public void createListView() {
		viewListaVacunas = (ListView) view.findViewById(R.id.viewListaVacunas);
		ArrayAdapter<ArrayList<String>> array = new ArrayAdapter<ArrayList<String>>(view.getContext(), android.R.layout.simple_list_item_1);
		array = datos(7);
		viewListaVacunas.setAdapter(array);
		viewListaVacunas.setSelection(0);
		viewListaVacunas.setBackgroundColor(80);
	}
	
	public void createWidgets() {
		createTextViews();
		createEditTexts();
		createListView();
		createSpinners();
		createButtons();
		editTextFunctions();
	}
	
	public void buttonFunctions() {
		
//		butAgVac.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				Toast.makeText(getActivity(), "Funciona la inserción...", Toast.LENGTH_SHORT).show();		
//			}			
//		});
//		
//		searchButton.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				Toast.makeText(getActivity(), "Funciona la búsqueda...", Toast.LENGTH_SHORT).show();		
//			}			
//		});
//		
//		insertButton.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
////				String latitud = editLat.getText().toString();
////				Toast.makeText(getActivity(), latitud, 5).show();
//			}			
//		});
		
		updateButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				String latitud = editLat.getText().toString();
//				Toast.makeText(getActivity(), latitud, 5).show();
			}			
		});

        /*
        searchButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Validation validacion = new Validation();
                if(!validacion.isEmpty(editCodPer.getText().toString())){
                    int codPersona = Integer.parseInt(editCodPer.getText().toString());
                   //String sentencia = "select * from condsaludm, condsalud " +
                           // "WHERE condsaludm.consec = condsalud.consec " +
                            //"GROUP BY condsaludm.consec", null);
                    String sentencia = "select * from condsaludm where consec = "+codPersona;
                    adapter.open();
                    Cursor resultado = adapter.select(sentencia);
                    if(resultado.getCount()==0){
                        Toast.makeText(view.getContext(),"No existen entradas en la base de datos para el codigo persona",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(view.getContext(),"Hasta aqui va correcto",Toast.LENGTH_LONG).show();
                    }
                    adapter.close();

                }else{
                    Toast.makeText(view.getContext(),"Debe ingresar un codigo de persona para hacer la consulta",Toast.LENGTH_LONG).show();
                }
            }
        });
*/
        insertButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Validation validation = new Validation();
                if ((codPer != 0)) {
                    long exito = -1;
                    Toast.makeText(view.getContext(), codPer, Toast.LENGTH_SHORT).show();
                    //setValues();
                    ContentValues valores_insertar = new ContentValues();
                    /*************************************************************************************************/
                    /*  Revisar el otro metodo que hacer un Content values y lo llena para ver cual de los dos es el */
                    /*  correcto                                                                                     */
                    /*************************************************************************************************/
                    valores_insertar.put("fechaPlan",fechaPlan);
                    valores_insertar.put("fechaPap",fechaPap);
                    valores_insertar.put("observaciones",observ);
                    valores_insertar.put("tipoPlan",tipoPlan);
                    adapter.open();
                    exito = adapter.insert("condsaludm",valores_insertar);
                    adapter.close();
                    if(exito==-1){
                        Toast.makeText(view.getContext(),"No se han insertado correctamente los datos en la base de datos",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(view.getContext(),"Datos insertados correctamente", Toast.LENGTH_LONG).show();
                    }

                    valores_insertar.put("saludActual",saludActual);
                    valores_insertar.put("tipoViolencia",tipoViol);
                    valores_insertar.put("especialista",espec);
                    adapter.open();
                    exito = adapter.insert("condsalud",valores_insertar);
                    adapter.close();
                    if(exito==-1){
                        Toast.makeText(view.getContext(),"No se han insertado correctamente los datos en la base de datos",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(view.getContext(),"Datos insertados correctamente", Toast.LENGTH_LONG).show();
                    }



                } else {
                    Toast.makeText(view.getContext(),  "Datos de condsalud y condsaludm insertados", Toast.LENGTH_SHORT).show();
                }
            }
        });
		
	}

    private void setValues() {
        values = new ContentValues();
        values.put("codPer", getCodPer());
        //condsaludm
        values.put("fechaPlan", editFechaPlan.getText().toString());
        values.put("fechaPap", editFechaPap.getText().toString());
        values.put("observ", editObservaciones.getText().toString());
        values.put("tipoPlan",tipo_Planificacion[spinnerTipoPlan.getSelectedItemPosition()]);
        //condsalud
        values.put("saludActual",tipo_Estados[spinnerSaludAct.getSelectedItemPosition()]);
        values.put("tipoViolencia",tipo_Violencia[spinnerTipoViol.getSelectedItemPosition()]);
        values.put("espec", editEspec.getText().toString());


    }



    public int getCodPer() {
        int res = 0;
        try {
            res = Integer.parseInt(editCodPer.getText().toString());
        } catch (Exception e) {
            res = -1;
        }
        return res;
    }

    public void store() {
		
	}
	
	public void load() {
		
	}


}
