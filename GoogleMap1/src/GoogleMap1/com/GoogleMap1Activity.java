package GoogleMap1.com;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import android.os.Bundle;

public class GoogleMap1Activity extends MapActivity {
	private MapView map;  //宣告 google map 物件
    private MapController mapController; //宣告 google map 控制物件
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        map = (MapView) findViewById(R.id.map) ; //取得 google map 元件        

        mapController = map.getController();
        mapController.setZoom(17); //設定放大倍率1(地球)-21(街景)
        
        //設定地圖座標值:緯度,經度
      	GeoPoint SunMoonLake = new GeoPoint(
      	     (int) (23.862696 * 1000000),(int) (120.904211 * 1000000));         
     	mapController.animateTo(SunMoonLake); //中心點--日月潭              
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}