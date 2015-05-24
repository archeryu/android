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
        
        map = (MapView) findViewById(R.id.map) ; //取得 google map 元件        
        map.setBuiltInZoomControls(true);  // 地圖縮放和拖曳     
        map.setSatellite(true); // 衛星地圖    	
    	
        mapController = map.getController(); 
        mapController.setZoom(17); //設定放大倍率
        
        List<Overlay> overlays = map.getOverlays(); // 建立圖層的 List 清單
        
        LocationOverlay = new MyLocationOverlay(this, map);
        LocationOverlay.runOnFirstFix(new Runnable(){
        	@Override
            public void run(){  
            	mapController.animateTo(LocationOverlay.getMyLocation());
            }
        });     
        overlays.add(LocationOverlay); // 將定位圖層圖示加入圖層清單中
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