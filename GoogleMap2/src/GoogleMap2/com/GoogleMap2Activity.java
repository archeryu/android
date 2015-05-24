package GoogleMap2.com;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GoogleMap2Activity extends MapActivity {
	private MapView map;  // 宣告 google map 物件
    private MapController mapController; // 宣告 google map 控制物件
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        map = (MapView) findViewById(R.id.map) ; //取得 google map 元件        
        map.setBuiltInZoomControls(true);  //地圖縮放和拖曳     
    	map.setTraffic(false); //設定地圖檢示模式：一般地圖  
    	map.setSatellite(true); //衛星地圖
    	
    	mapController = map.getController(); 
    	mapController.setZoom(17); //設定放大倍率1(地球)-21(街景)
    	mapController.animateTo(Taipei101); //中央點為  台北 101    
    }

	protected static final int MENU_Traffic =Menu.FIRST ;
	protected static final int MENU_Satellite =Menu.FIRST +1;
	protected static final int MENU_Taipei101 = Menu.FIRST+2;
	protected static final int MENU_SunMoonLake = Menu.FIRST+3;
	protected static final int MENU_Kaushong = Menu.FIRST+4;
    // set menu
    public boolean onCreateOptionsMenu(Menu menu){
    	menu.add(0,MENU_Traffic,0,"一般地圖");
    	menu.add(0,MENU_Satellite,1,"衛星地圖");
        menu.add(0,MENU_Taipei101, 2,"台北 101");
        menu.add(0,MENU_SunMoonLake, 3,"日月潭");
        menu.add(0,MENU_Kaushong, 4,"高雄夜市");
    	return true;    	
    }
    
    //設定地圖座標值:緯度,經度
  	GeoPoint Taipei101 = new GeoPoint(
            (int) (25.033194 * 1000000),(int) (121.564837 * 1000000));
	GeoPoint SunMoonLake = new GeoPoint(
	     (int) (23.862696 * 1000000),(int) (120.904211 * 1000000));
 	GeoPoint Kaushong = new GeoPoint(
            (int) (22.633428 * 1000000),(int) (120.302368 * 1000000));
 	
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
	    	case MENU_Traffic:
	    		map.setSatellite(false); //一般地圖
	    		break;
	    	case MENU_Satellite:
	    	    map.setSatellite(true); //衛星地圖
	    	    break;
	    	case MENU_Taipei101:    //台北 101
	    		mapController.animateTo(Taipei101); 
	    	    break;
	    	case MENU_SunMoonLake:  //日月潭
	    		mapController.animateTo(SunMoonLake); 
	    	    break;
	    	case MENU_Kaushong:     //高雄六合夜市
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