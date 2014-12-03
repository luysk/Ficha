package ucr.fragments;


import ucr.database.SQLiteAdapter;
import ucr.ff.R;
import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class DenunciaFragment extends Fragment {

	private View view;
	private EditText id;
	private EditText descripcion;
	private DatePicker fecha;
	private EditText denunciante;
	private EditText telDenunciante;
	private EditText asignada;
	private EditText longitud;
	private EditText latitud;


    private Button boton_insertar;
    private Button boton_actualizar;

    private SQLiteAdapter adapter;

	
	
	public DenunciaFragment() {
		super();
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Prueba", "DenunciaFragment");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.denuncias_tab, null);
		
		createWidgets();
        crearBotones();
        crearFecha();
		buttonFunctions();
		editTextFunctions();
        adapter = new SQLiteAdapter(view.getContext());
		return view;
	}



    private void crearFecha(){
        fecha = (DatePicker)view.findViewById(R.id.datePicker);
    }

	private void createEditTexts() {
		id = (EditText) view.findViewById(R.id.ID);
		descripcion = (EditText) view.findViewById(R.id.descripcion);
		denunciante = (EditText) view.findViewById(R.id.denunciante);
		telDenunciante = (EditText) view.findViewById(R.id.telDenunciante);
		asignada = (EditText) view.findViewById(R.id.asignada);
		longitud = (EditText) view.findViewById(R.id.longitud);
		latitud = (EditText) view.findViewById(R.id.latitud);
	}


    private void crearBotones(){
        boton_insertar = (Button)view.findViewById(R.id.insertarDenucia);
        boton_actualizar = (Button)view.findViewById(R.id.actualizarDenuncia);
    }
	private void editTextFunctions() {
		id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					id.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (id.getText().toString().isEmpty()) {
						id.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						id.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});

		descripcion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					descripcion.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (descripcion.getText().toString().isEmpty()) {
						descripcion.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						descripcion.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});

		/*fecha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					fecha.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (fecha.getText().toString().isEmpty()) {
						fecha.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						fecha.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});	    */

		denunciante.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					denunciante.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (denunciante.getText().toString().isEmpty()) {
						denunciante.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						denunciante.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
		
		telDenunciante.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					telDenunciante.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (telDenunciante.getText().toString().isEmpty()) {
						telDenunciante.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						telDenunciante.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
		
		asignada.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					asignada.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (asignada.getText().toString().isEmpty()) {
						asignada.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						asignada.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});	    

		longitud.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					longitud.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (longitud.getText().toString().isEmpty()) {
						longitud.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						longitud.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
		
		latitud.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == true) {
					latitud.setBackgroundColor(getResources().getColor(R.color.lightblue));  
				} else {
					if (latitud.getText().toString().isEmpty()) {
						latitud.setBackgroundColor(getResources().getColor(R.color.white));  
					} else {
						latitud.setBackgroundColor(getResources().getColor(R.color.lightorange));  
					}	        	
				}
			}
		});
	}
	
	public void createWidgets() {
		createEditTexts();
	}


	private void buttonFunctions() {
		boton_insertar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    System.out.println("-------------");
                    String id_denun = id.getText().toString();
                    if(!id_denun.isEmpty()) {
                        String sql = "select * from denuncia where coddenuncia = "+id_denun+";";
                        adapter.open();
                        Cursor resultado = adapter.select(sql);
                        if(resultado.getCount()==0) {
                            String desc = descripcion.getText().toString();
                            if (!desc.isEmpty()){
                                String denun = denunciante.getText().toString();
                                if(!denun.isEmpty()){
                                    String telefono = telDenunciante.getText().toString();
                                    if(!telefono.isEmpty()){
                                        String responsable = asignada.getText().toString();
                                        if(!responsable.isEmpty()) {
                                            String longitud_text = longitud.getText().toString();
                                            String latitud_text = latitud.getText().toString();
                                            if (!latitud_text.isEmpty() && !longitud_text.isEmpty()) {
                                                ContentValues valores = new ContentValues();
                                                valores.put("coddenuncia", id_denun);
                                                valores.put("descripcion", desc);
                                                valores.put("Nombredenunciante", denun);
                                                valores.put("teldenunciante", telefono);
                                                int dia = fecha.getDayOfMonth();
                                                int mes = fecha.getMonth();
                                                int anyo = fecha.getYear();
                                                valores.put("fechadenuncia", dia + "/" + mes + "/" + anyo);
                                                valores.put("longitud", longitud_text);
                                                valores.put("latitud", latitud_text);
                                                valores.put("responsableden",responsable);
                                                adapter.insert("denuncia", valores);
                                                Toast.makeText(view.getContext(),"Datos insertados correctamente",Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(view.getContext(), "Ingrese la latitud y la longitud", Toast.LENGTH_LONG).show();
                                            }
                                        }else{
                                            Toast.makeText(view.getContext(),"Ingrese a un responsable para la denuncia",Toast.LENGTH_LONG).show();
                                        }
                                    }else{
                                        Toast.makeText(view.getContext(),"Favor ingresar el telefono del denunciante",Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(view.getContext(),"Debe ingresar un codigo de denuncuiante",Toast.LENGTH_LONG).show();
                                }
                        }else{
                            Toast.makeText(view.getContext(),"Debe ingresar una descripción",Toast.LENGTH_LONG).show();
                        }
                        }else{
                            Toast.makeText(view.getContext(),"El codigo de denuncia ingresado ya está en uso",Toast.LENGTH_LONG).show();
                        }
                        adapter.close();
                    }else{
                        Toast.makeText(view.getContext(),"Debe ingresar un codigo de denuncia válido",Toast.LENGTH_LONG).show();
                    }
            }
        });

        boton_actualizar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
	}

	public void store() {
		
	}
	
	public void load() {
		
	}

}
