package GoogleMap1.com;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import android.os.Bundle;

public class GoogleMap1Activity extends MapActivity {
	private MapView map;  //�ŧi google map ����
    private MapController mapController; //�ŧi google map �����
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        map = (MapView) findViewById(R.id.map) ; //���o google map ����        

        mapController = map.getController();
        mapController.setZoom(17); //�]�w��j���v1(�a�y)-21(��)
        
        //�]�w�a�Ϯy�Э�:�n��,�g��
      	GeoPoint SunMoonLake = new GeoPoint(
      	     (int) (23.862696 * 1000000),(int) (120.904211 * 1000000));         
     	mapController.animateTo(SunMoonLake); //�����I--����              
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}