package GPS3.com;

import java.util.ArrayList;
import java.util.List;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class GPS3Activity extends MapActivity {
	private String[] locTitle = {"台北市：","南投縣：","高雄市："};
	private String[] locSnippet = {"台北 101","日月潭","六合夜市"};
	private Double[][] locPoint = {
		{25.033194,121.564837},{23.862696,120.904211},{22.633428,120.302368}};
	
	private MapView map;
    private MapController mapController;
    private MyLocationOverlay LocationOverlay;
    private LandMarkOverlay myMarkOverlay;   
	int[] resIds = new int[]{ R.drawable.onebit_01, R.drawable.onebit_02,R.drawable.onebit_03};
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
        LocationOverlay.runOnFirstFix(new Runnable() {
        	@Override
            public void run(){
            	mapController.animateTo(LocationOverlay.getMyLocation());
            }
        });     
        overlays.add(LocationOverlay); // 將定位圖層加入圖層清單中
        
        // 建立地標圖層 並標示圖示       
        Drawable icon=getResources().getDrawable(resIds[0]);
        icon.setAlpha(120);	// 透明度(0~255)
        icon.setBounds(0, 0, icon.getMinimumWidth(), icon.getMinimumHeight());
        myMarkOverlay = new LandMarkOverlay(icon); 
        // 加入地標物件
        for (int i=0;i<3;i++){	         
	        double dLat = locPoint[i][0];	// 緯度
	        double dLon = locPoint[i][1];;	// 經度
	        GeoPoint gp = new GeoPoint((int)(dLat * 1e6), (int)(dLon * 1e6));
	        OverlayItem overlayitem = new OverlayItem(gp, locTitle[i], locSnippet[i]);
	    	myMarkOverlay.addMyOverlayItem(overlayitem); // 地標物件加入 位置、標題、說明文字
        }
        overlays.add(myMarkOverlay); // 將地標圖層加入圖層清單中
    }
    
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
     
    @Override
	protected void onResume() {
		super.onResume();
		LocationOverlay.enableMyLocation();
	}
    
	@Override
	protected void onPause() {
		super.onPause();
		LocationOverlay.disableMyLocation();
	}

	protected static final int MENU_Traffic =Menu.FIRST ;
	protected static final int MENU_Satellite =Menu.FIRST +1;
	protected static final int MENU_Taipei101 = Menu.FIRST+2;
	protected static final int MENU_SunMoonLake = Menu.FIRST+3;
	protected static final int MENU_Kaushong = Menu.FIRST+4;
	// 建立功能表
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
	    		map.setSatellite(false); // 一般地圖
	    		break;
	    	case MENU_Satellite:
	    	    map.setSatellite(true); // 衛星地圖
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
    
    private class LandMarkOverlay extends ItemizedOverlay<OverlayItem> {
    	private ArrayList<OverlayItem> myOverlayItems=new ArrayList<OverlayItem>();   
    	public LandMarkOverlay(Drawable icon) {
    		super(icon);
      	}

    	@Override
    	public void draw(Canvas canvas, MapView mapView, boolean shadow) { 
    		super.draw(canvas, mapView, false);
    	}

    	public void addMyOverlayItem(OverlayItem overlayItem) {
    		myOverlayItems.add(overlayItem);  		
    		populate();
    	}
        @Override
        protected OverlayItem createItem(int i) {   
        	return myOverlayItems.get(i);  
        }

        @Override
        public int size() {  
        	return myOverlayItems.size();           
        }

	    @Override
	    protected boolean onTap(int pIndex) {
	        Toast.makeText(GPS3Activity.this,
	        	myOverlayItems.get(pIndex).getTitle()+ myOverlayItems.get(pIndex).getSnippet(),
	            Toast.LENGTH_SHORT).show(); 
	        return true;
	    }
    }
}