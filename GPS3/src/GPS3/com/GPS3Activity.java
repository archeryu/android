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
	private String[] locTitle = {"�x�_���G","�n�뿤�G","�������G"};
	private String[] locSnippet = {"�x�_ 101","����","���X�]��"};
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
        
        map = (MapView) findViewById(R.id.map) ; //���o google map ����        
        map.setBuiltInZoomControls(true);  // �a���Y��M�즲     
        map.setSatellite(true); // �ìP�a��    	
    	
        mapController = map.getController(); 
        mapController.setZoom(17); //�]�w��j���v        
        
        List<Overlay> overlays = map.getOverlays(); // �إ߹ϼh�� List �M��
    	
        LocationOverlay = new MyLocationOverlay(this, map);
        LocationOverlay.runOnFirstFix(new Runnable() {
        	@Override
            public void run(){
            	mapController.animateTo(LocationOverlay.getMyLocation());
            }
        });     
        overlays.add(LocationOverlay); // �N�w��ϼh�[�J�ϼh�M�椤
        
        // �إߦa�йϼh �üХܹϥ�       
        Drawable icon=getResources().getDrawable(resIds[0]);
        icon.setAlpha(120);	// �z����(0~255)
        icon.setBounds(0, 0, icon.getMinimumWidth(), icon.getMinimumHeight());
        myMarkOverlay = new LandMarkOverlay(icon); 
        // �[�J�a�Ъ���
        for (int i=0;i<3;i++){	         
	        double dLat = locPoint[i][0];	// �n��
	        double dLon = locPoint[i][1];;	// �g��
	        GeoPoint gp = new GeoPoint((int)(dLat * 1e6), (int)(dLon * 1e6));
	        OverlayItem overlayitem = new OverlayItem(gp, locTitle[i], locSnippet[i]);
	    	myMarkOverlay.addMyOverlayItem(overlayitem); // �a�Ъ���[�J ��m�B���D�B������r
        }
        overlays.add(myMarkOverlay); // �N�a�йϼh�[�J�ϼh�M�椤
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
	// �إߥ\���
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
	    		map.setSatellite(false); // �@��a��
	    		break;
	    	case MENU_Satellite:
	    	    map.setSatellite(true); // �ìP�a��
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