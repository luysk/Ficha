package ucr.fragments;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import ucr.ff.FichaFamiliar;
import ucr.ff.R;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * AGREGADO 14/10/13 
 * */
/*
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import ucr.tools.Preview;
*/
/**
 * FIN AGREGADO 14/10/13 
 * */
public class MapaFragment extends Fragment {


	//private View view;
	
	/**
	 * AGREGADO 14/10/13 
	 * */
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri;
	
	ComponentName service;
	Intent intentMyService;
    BroadcastReceiver receiver;
    String GPS_FILTER = "ucr.action.GET_GPS_LOCATION";
    double latitude;
    double longitude;
    String provider;
   // static GoogleMap map;
    static View view = null;
    
    private EditText editLatitud;
	private EditText editLongitud;
	private TextView txtLatitud;
	private TextView txtLongitud;
	private Button btnBusqCasa;
	private Button btnBusqPersona;
	private Button btnBusqDocumento;
	private Button btnBusqCalendario;
	private Button btnBusqLink;
	private Button btnCamara;
	
    
	/**
	 * FIN AGREGADO 14/10/13 
	 * */
	
	public MapaFragment() {
		super();
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("iniciando", "MapaFragment");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// /////////////////////////////////////////////////////////////////////
				// BEGIN - added by Victor
				
				// this portion will give you the LATITUDEand LONGITUDE values
				
				Context context = (Context)MapaFragment.this.getActivity();
				
				Log.e("MapaFragment-onActivityCreate", "calling getMyLocationServiceStarted...");
				getMyLocationServiceStarted();
 
				// register & define filter for local listener
				IntentFilter myLocationFilter = new IntentFilter(GPS_FILTER);
				receiver = new MyMainLocalReceiver();
				Log.e("MapaFragment-onActivityCreate", "receiver created..." + receiver.toString());
				context.registerReceiver(receiver, myLocationFilter);

				Log.e("MapaFragment-onActivityCreated", "receiver registered...");
				
				//this call will draw a little map showing current location

				//setUpMapIfNeeded();
			
				Log.e("MapaFragment-onActivityCreated", "map created ready...");
				
				// END - added by Victor
				// /////////////////////////////////////////////////////////////////////
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.e("mapa fragment-onCreateView>>>", "creando el view");
		
		if (view == null) {
			view = inflater.inflate(R.layout.mapa_tab, null);
		}else {
			return view;
		}
		
		createWidgets();
		
		buttonFunctions();
		return view;
	}
	
	public void createWidgets() {
		createEditTexts();
		createTextViews();
		createButtons();
	}
	
	public void createButtons(){
		btnBusqCasa = (Button)view.findViewById(R.id.btnBusqCasa);
		onClickCasa();
		btnBusqCasa.setEnabled(true);
		btnBusqPersona = (Button)view.findViewById(R.id.btnBusqPersona);
		onClickPersona();
		
		btnBusqDocumento = (Button)view.findViewById(R.id.btnBusqDocumento);
		onClickDocumento();
		btnBusqCalendario = (Button)view.findViewById(R.id.btnBusqCalendario);
		onClickCalendario();
		btnBusqLink = (Button)view.findViewById(R.id.btnBusqLink);
		onClickLink();
		btnCamara = (Button)view.findViewById(R.id.btnCamara);
		onClickCamara();
	
	}
	
	public void mostrarMsj(String msj){
		Toast.makeText(getActivity(), msj,Toast.LENGTH_SHORT).show();
	}
	public void onClickCasa(){
		btnBusqCasa.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
			// Cedigo a ejecutar al pulsar el boten
				Toast.makeText(getActivity(), "Busqueda por casa",Toast.LENGTH_SHORT).show();
			}
			});
	}
	public void onClickPersona(){
		btnBusqPersona.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
			// Cedigo a ejecutar al pulsar el boten
				Toast.makeText(getActivity(), "Busqueda por persona",Toast.LENGTH_SHORT).show();
			}
			});
	}
	public void onClickDocumento(){
		btnBusqDocumento.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
			// Cedigo a ejecutar al pulsar el boten
				Toast.makeText(getActivity(), "Busqueda por documento",Toast.LENGTH_SHORT).show();
			}
			});
	}
	public void onClickCalendario(){
		btnBusqCalendario.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
			// Cedigo a ejecutar al pulsar el boten
				Toast.makeText(getActivity(), "Busqueda por calendario",Toast.LENGTH_SHORT).show();
			}
			});
	}
	public void onClickLink(){
		btnBusqLink.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
			// Cedigo a ejecutar al pulsar el boten
				Toast.makeText(getActivity(), "Busqueda por enlace",Toast.LENGTH_SHORT).show();
			}
			});
	}
	public void onClickCamara(){
		btnCamara.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
			// Cedigo a ejecutar al pulsar el boten
				//Toast.makeText(getActivity(), "Camara",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

			    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
			    //intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

			    // start the image capture Intent
			    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
			}
			});
	}
	
	private void createEditTexts() {
		editLatitud =  (EditText) view.findViewById(R.id.editLatitud);
		editLongitud =  (EditText) view.findViewById(R.id.editLongitud);
	}
	
	public void createTextViews(){
		txtLatitud = (TextView) view.findViewById(R.id.etiqLatitud);
		txtLongitud = (TextView) view.findViewById(R.id.etiqLongitud);	
	}
	
	
	public void buttonFunctions() {
		
	}
	
	public void store() {
		
	}
	
	public void load() {
		
	}
	
	// /////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////
		
		// /////////////////////////////////////////////////////////////////////
		// BEGIN - added by Victor

		public void getMyLocationServiceStarted() {
			// get background GPS service started
			Context context = (Context) MapaFragment.this.getActivity();

			intentMyService = new Intent(context, ucr.tools.MyGpsService.class);
			
			Log.e("Ficha-getMyLocationServiceStarted", "intentMyService created...");
			service = context.startService(intentMyService);

			Log.e("Ficha-getMyLocationServiceStarted", "service started2...");
		}
	  
	 
		//////////////////////////////////////////////////////////////////////
		// local RECEIVER
		private class MyMainLocalReceiver extends BroadcastReceiver {
			@Override
			public void onReceive(Context localContext, Intent intentFilteredResponse) {
				Log.e("FICHA-FIX-RECEIVED>>>", "................");
				
				latitude = intentFilteredResponse.getDoubleExtra("latitude",-1);
				longitude = intentFilteredResponse.getDoubleExtra("longitude",-1);
			    provider = intentFilteredResponse.getStringExtra("provider");
			    
				Log.e ("GPS Lat    >>>",  Double.toString(latitude));
				Log.e ("GPS Long   >>>",  Double.toString(longitude));			
				Log.e ("GPS Source >>>",  provider);
				
				String msg = provider 
						   + " lat: " + Double.toString(latitude) + " " 
				           + " lon: " + Double.toString(longitude);
				
				Log.e("FICHA-FIX-RECEIVED>>>", msg);
				
				//fill-up TextView-boxes showing Latitude-Longitude 
				try {
					editLatitud.setText(Double.toString(latitude));
					editLongitud.setText(Double.toString(longitude));
					Log.e("FICHA-FIX-RECEIVED-DONE>>>", msg);
				} catch (Exception e) {
					Log.e("FICHA-FIX-RECEIVED-NOT DONE>>>", "---------------------");
				}
			}		
		}//MyMainLocalReceiver
		

		
		@Override
		public void onDestroy() {
			// needed - otherwise you leave behind a 'leaking' service eating
			// your battery - do not remove!
			super.onDestroy();
			try {			
				Context context = (Context) MapaFragment.this.getActivity();
				context.stopService(intentMyService);
				context.unregisterReceiver(receiver);
				
			} catch (Exception e) {
				Log.e ("MapaFragment-DESTROY>>>", e.getMessage() );
			}
			Log.e ("MapaFragment-DESTROY>>>" , "Adios - GPS chip deactivated" );
		}

		// /////////////////////////////////////////////////////////////////
		public void drawGoogleMap(double latitude, double longitude){
//			// this looks good on a big screen		
//			String myGeoCode = "https://maps.google.com/maps?q="
//			           + latitude
//			           + ","
//			           + longitude
//			           + "(You are here!)&iwloc=A&hl=en";
			
			// this looks better on a small screen
			String myGeoCode = "geo:" + latitude 
					         + "," + longitude
					         + "?z=15";

			Intent intentViewMap = new Intent(Intent.ACTION_VIEW,
					   Uri.parse(myGeoCode));
			
			startActivity(intentViewMap);
		}


		// /////////////////////////////////////////////////////////////////////////////////////////
	/*	private void setUpMapIfNeeded() {
			// Do a null check to confirm that we have not already instantiated the map.
			
			Context context = (Context) MapaFragment.this.getActivity();
			
//			MapFragment f = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
//	        if (f != null) 
//	            getFragmentManager().beginTransaction().remove(f).commit();
			
			
			
			if (map == null) {
				map = ((MapFragment) getFragmentManager()
						.findFragmentById(R.id.mapa)).getMap();
				// Check if we were successful in obtaining the map.
				
				
				
				if (map != null) {
					// The Map is verified. It is now safe to manipulate the map.
					map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
					// show the user's location on the map
					map.setMyLocationEnabled(true); 
					// disable indoor maps 
					map.setIndoorEnabled(false); 
					// get location manager service
					// get the last known location
					String LOCATION = android.content.Context.LOCATION_SERVICE;
					Location loc = ((LocationManager) context.getSystemService(LOCATION)) 
							.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER); 
					// center the map view on the user's last known location, zoom= 15 0f 21
					//map.moveCamera(CameraUpdateFactory.newLatLngZoom(
					//		new LatLng(loc.getLatitude(), loc.getLongitude()),
					//		17.0f));

					// set up map UI settings:
					// - enable all gestures - pan, zoom, tilt, rotate
					// - enable compass
					// - enable 'my location' button
					// - enable zoom controls
					UiSettings mapUI = map.getUiSettings();
					mapUI.setAllGesturesEnabled(true);
					mapUI.setCompassEnabled(true);
					mapUI.setMyLocationButtonEnabled(true);
					mapUI.setZoomControlsEnabled(true);

				}
			}
		}//setUpMapIfNeeded
		*/
		
		// END - added by Victor
		// /////////////////////////////////////////////////////////////////////
		
		
		public static final int MEDIA_TYPE_IMAGE = 1;
		public static final int MEDIA_TYPE_VIDEO = 2;

		/** Create a file Uri for saving an image or video */
		private static Uri getOutputMediaFileUri(int type){
		      return Uri.fromFile(getOutputMediaFile(type));
		}

		/** Create a File for saving an image or video */
		private static File getOutputMediaFile(int type){
		    // To be safe, you should check that the SDCard is mounted
		    // using Environment.getExternalStorageState() before doing this.

		    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
		              Environment.DIRECTORY_PICTURES), "MyCameraApp");
		    // This location works best if you want the created images to be shared
		    // between applications and persist after your app has been uninstalled.

		    // Create the storage directory if it does not exist
		    if (! mediaStorageDir.exists()){
		        if (! mediaStorageDir.mkdirs()){
		            Log.d("MyCameraApp", "failed to create directory");
		            return null;
		        }
		    }

		    // Create a media file name
		    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		    File mediaFile;
		    if (type == MEDIA_TYPE_IMAGE){
		        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
		        "IMG_"+ timeStamp + ".jpg");
		    } else if(type == MEDIA_TYPE_VIDEO) {
		        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
		        "VID_"+ timeStamp + ".mp4");
		    } else {
		        return null;
		    }

		    return mediaFile;
		}

}
