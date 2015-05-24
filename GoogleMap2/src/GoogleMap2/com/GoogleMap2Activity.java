package GoogleMap2.com;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GoogleMap2Activity extends MapActivity {
	private MapView map;  // �ŧi google map ����
    private MapController mapController; // �ŧi google map �����
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        map = (MapView) findViewById(R.id.map) ; //���o google map ����        
        map.setBuiltInZoomControls(true);  //�a���Y��M�즲     
    	map.setTraffic(false); //�]�w�a���˥ܼҦ��G�@��a��  
    	map.setSatellite(true); //�ìP�a��
    	
    	mapController = map.getController(); 
    	mapController.setZoom(17); //�]�w��j���v1(�a�y)-21(��)
    	mapController.animateTo(Taipei101); //�����I��  �x�_ 101    
    }

	protected static final int MENU_Traffic =Menu.FIRST ;
	protected static final int MENU_Satellite =Menu.FIRST +1;
	protected static final int MENU_Taipei101 = Menu.FIRST+2;
	protected static final int MENU_SunMoonLake = Menu.FIRST+3;
	protected static final int MENU_Kaushong = Menu.FIRST+4;
    // set menu
    public boolean onCreateOptionsMenu(Menu menu){
    	menu.add(0,MENU_Traffic,0,"�@��a��");
    	menu.add(0,MENU_Satellite,1,"�ìP�a��");
        menu.add(0,MENU_Taipei101, 2,"�x�_ 101");
        menu.add(0,MENU_SunMoonLake, 3,"����");
        menu.add(0,MENU_Kaushong, 4,"�����]��");
    	return true;    	
    }
    
    //�]�w�a�Ϯy�Э�:�n��,�g��
  	GeoPoint Taipei101 = new GeoPoint(
            (int) (25.033194 * 1000000),(int) (121.564837 * 1000000));
	GeoPoint SunMoonLake = new GeoPoint(
	     (int) (23.862696 * 1000000),(int) (120.904211 * 1000000));
 	GeoPoint Kaushong = new GeoPoint(
            (int) (22.633428 * 1000000),(int) (120.302368 * 1000000));
 	
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
	    	case MENU_Traffic:
	    		map.setSatellite(false); //�@��a��
	    		break;
	    	case MENU_Satellite:
	    	    map.setSatellite(true); //�ìP�a��
	    	    break;
	    	case MENU_Taipei101:    //�x�_ 101
	    		mapController.animateTo(Taipei101); 
	    	    break;
	    	case MENU_SunMoonLake:  //����
	    		mapController.animateTo(SunMoonLake); 
	    	    break;
	    	case MENU_Kaushong:     //�������X�]��
	    		mapController.animateTo(Kaushong);
	    	    break;
    	}
    	return true;
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}