package GPS1.com;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class GPS1Activity extends MapActivity implements LocationListener{
	private MapView map;
    private MapController mapController;
    private LocationManager locMgr;
	String bestProv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        map = (MapView) findViewById(R.id.map) ; //取得 google map 元件        
        map.setBuiltInZoomControls(true);  // 地圖縮放和拖曳     
        map.setSatellite(true); // 設定地圖檢示模式：衛星地圖   
        
        mapController = map.getController(); 
        mapController.setZoom(17); //設定放大倍率1(地球)-21(街景)
        locMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        
        Criteria criteria = new Criteria();
        bestProv = locMgr.getBestProvider(criteria, true);   
    }
    
	@Override
	public void onLocationChanged(Location location) {
		GeoPoint gp=new GeoPoint(
                (int) (location.getLatitude() * 1000000),
                (int) (location.getLongitude() * 1000000) );
    	mapController.animateTo(gp);   // 指定地圖現在位置   
        String x="緯=" + Double.toString(location.getLatitude());
        String y="經=" + Double.toString(location.getLongitude());
        Toast.makeText(GPS1Activity.this,x + "\n\r" + y, Toast.LENGTH_LONG).show();
	}
 	
	@Override
	protected void onResume() {	
		super.onResume();
		locMgr.requestLocationUpdates(bestProv, 60000, 1, this);
	}
	
	@Override
	protected void onPause() {	
		super.onPause();
		locMgr.removeUpdates(this);
	}
	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Criteria criteria = new Criteria();
        bestProv = locMgr.getBestProvider(criteria, true);
	}
	
	@Override
	public void onProviderDisabled(String provider) {	
	}
	
	@Override
	public void onProviderEnabled(String provider) {
	}	
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}