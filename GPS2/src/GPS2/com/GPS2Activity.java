package GPS2.com;

import java.util.List;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import android.os.Bundle;

public class GPS2Activity extends MapActivity {
	private MapView map;
    private MapController mapController;
    private MyLocationOverlay LocationOverlay;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        map = (MapView) findViewById(R.id.map) ; //���o google map ����        
        map.setBuiltInZoomControls(true);  // �a���Y��M�즲     
        map.setSatellite(true); // �ìP�a��    	
    	
        mapController = map.getController(); 
        mapController.setZoom(17); //�]�w��j���v
        
        List<Overlay> overlays = map.getOverlays(); // �إ߹ϼh�� List �M��
        
        LocationOverlay = new MyLocationOverlay(this, map);
        LocationOverlay.runOnFirstFix(new Runnable(){
        	@Override
            public void run(){  
            	mapController.animateTo(LocationOverlay.getMyLocation());
            }
        });     
        overlays.add(LocationOverlay); // �N�w��ϼh�ϥܥ[�J�ϼh�M�椤
    }
   
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		LocationOverlay.enableMyLocation(); 
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		LocationOverlay.disableMyLocation(); 
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}