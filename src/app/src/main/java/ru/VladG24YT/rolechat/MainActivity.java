package ru.VladG24YT.rolechat;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import android.media.MediaPlayer;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private ProgressBar progressbar1;
	private LinearLayout linear1;
	private ImageView imageview1;
	private TextView textview1;
	
	private Intent ActivityChanger = new Intent();
	private TimerTask Delay;
	private MediaPlayer BackgroundMusic;
	private AlertDialog.Builder Warning;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview1 = (TextView) findViewById(R.id.textview1);
		Warning = new AlertDialog.Builder(this);
	}
	private void initializeLogic() {
		BackgroundMusic = MediaPlayer.create(getApplicationContext(), R.raw.intro);
		ActivityChanger.setClass(getApplicationContext(), AuthActivity.class);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/creditsib.ttf"), 0);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		BackgroundMusic.start();
		Warning.setTitle("Условия Пользования Приложением");
		Warning.setMessage("Нажимая кнопку \"Да, я согласен\", вы соглашаетесь со следующими условиями:\n\n1.) Данное Приложение МОЖЕТ нарушать Авторские Права/Copyright. Если вы являетесь Правообладателем, просьба написать Разработчику на почту: vg07102004@gmail.com\n\n2.) Данное Приложение находится в стадии InfDev (Infinite Development), следовательно Разработчик не обязан выпускать обновления максимально часто или в принципе выпускать их.\n\n3.) Главным условием пользования этим Приложением является подписка на группу \"Филиал Содружества Библиотеки Странников (ФСБС)\" (vk.com/wanderers-library-concord) в Социальной Сети ВКонтакте.\n\n4.) В случае обнаружения Багов, Эксплоитов, Глюков и/или Ошибок вы обязуетесь написать об этом Разработчику (vk.com/vladg24yt)\n\n5.) Разработчик в праве изменить эти Условия в любой момент времени, уповестив всех Пользователей об обновлении Условий Пользования");
		Warning.setPositiveButton("Да, я согласен", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				Delay = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								startActivity(ActivityChanger);
								finish();
							}
						});
					}
				};
				_timer.schedule(Delay, (int)(10000));
			}
		});
		Warning.setNegativeButton("Нет, я не согласен", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		Warning.create().show();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		BackgroundMusic.pause();
	}
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
