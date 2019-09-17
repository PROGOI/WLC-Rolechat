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
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.ClipData;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.OnProgressListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import java.io.File;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.view.View;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class SettingsActivity extends AppCompatActivity {
	
	public final int REQ_CD_PROFILEPHOTO = 101;
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private ArrayList<String> Themes = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private ProgressBar progress;
	private TextView info;
	private LinearLayout navigation;
	private TextView textview1;
	private Spinner spinner1;
	private TextView textview3;
	private ImageView profile;
	private TextView textview4;
	private EditText edittext3;
	private Button button3;
	private TextView textview5;
	private EditText edittext2;
	private Button button2;
	private TextView textview6;
	private EditText edittext1;
	private Button button1;
	private ImageView feed;
	private ImageView chats;
	private ImageView notifications;
	private ImageView additional_menu;
	
	private Intent ActivityChanger = new Intent();
	private AlertDialog.Builder Confirmation;
	private Intent ProfilePhoto = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference WanderersProfiles = _firebase_storage.getReference("Wanderers/Profiles");
	private OnSuccessListener<UploadTask.TaskSnapshot> _WanderersProfiles_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _WanderersProfiles_download_success_listener;
	private OnSuccessListener _WanderersProfiles_delete_success_listener;
	private OnProgressListener _WanderersProfiles_upload_progress_listener;
	private OnProgressListener _WanderersProfiles_download_progress_listener;
	private OnFailureListener _WanderersProfiles_failure_listener;
	private ObjectAnimator Animator = new ObjectAnimator();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.settings);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		progress = (ProgressBar) findViewById(R.id.progress);
		info = (TextView) findViewById(R.id.info);
		navigation = (LinearLayout) findViewById(R.id.navigation);
		textview1 = (TextView) findViewById(R.id.textview1);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		textview3 = (TextView) findViewById(R.id.textview3);
		profile = (ImageView) findViewById(R.id.profile);
		textview4 = (TextView) findViewById(R.id.textview4);
		edittext3 = (EditText) findViewById(R.id.edittext3);
		button3 = (Button) findViewById(R.id.button3);
		textview5 = (TextView) findViewById(R.id.textview5);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		button2 = (Button) findViewById(R.id.button2);
		textview6 = (TextView) findViewById(R.id.textview6);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		button1 = (Button) findViewById(R.id.button1);
		feed = (ImageView) findViewById(R.id.feed);
		chats = (ImageView) findViewById(R.id.chats);
		notifications = (ImageView) findViewById(R.id.notifications);
		additional_menu = (ImageView) findViewById(R.id.additional_menu);
		Confirmation = new AlertDialog.Builder(this);
		ProfilePhoto.setType("image/*");
		ProfilePhoto.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_WanderersProfiles_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				progress.setVisibility(View.VISIBLE);
				info.setVisibility(View.VISIBLE);
				info.setText("Загрузка на сервер...");
				progress.setProgress((int)_progressValue);
			}
		};
		
		_WanderersProfiles_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				info.setVisibility(View.VISIBLE);
				progress.setVisibility(View.VISIBLE);
				progress.setProgress((int)_progressValue);
				info.setText("Загрузка с сервера...");
			}
		};
		
		_WanderersProfiles_upload_success_listener = new OnSuccessListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(UploadTask.TaskSnapshot _param1) {
				final String _downloadUrl = _param1.getDownloadUrl().toString();
				progress.setVisibility(View.INVISIBLE);
				info.setText(_downloadUrl);
				info.setVisibility(View.INVISIBLE);
				SketchwareUtil.showMessage(getApplicationContext(), "Загрузка на сервер завершена");
			}
		};
		
		_WanderersProfiles_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				progress.setVisibility(View.INVISIBLE);
				info.setText(String.valueOf((long)(_totalByteCount)));
				info.setVisibility(View.INVISIBLE);
				SketchwareUtil.showMessage(getApplicationContext(), "Загрузка с сервера завершена");
			}
		};
		
		_WanderersProfiles_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_WanderersProfiles_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				progress.setVisibility(View.INVISIBLE);
				info.setText(_message);
				info.setVisibility(View.INVISIBLE);
				SketchwareUtil.showMessage(getApplicationContext(), _message);
			}
		};
	}
	private void initializeLogic() {
		Themes.add((int)(1), "Alexylva University");
		Themes.add((int)(2), "Ambrose Restraunts");
		Themes.add((int)(3), "Anderson Robotics");
		Themes.add((int)(4), "Are We Cool Yet?");
		Themes.add((int)(5), "The Black Queen");
		Themes.add((int)(6), "The Chaos Insurgency");
		Themes.add((int)(7), "The Chicago Spirit");
		Themes.add((int)(8), "The Church of the Broken God");
		Themes.add((int)(9), "Church of the Second Hytoth");
		Themes.add((int)(10), "Deer College");
		Themes.add((int)(11), "Dr. Wondertainment");
		Themes.add((int)(12), "The Factory");
		Themes.add((int)(13), "The Fifth Church");
		Themes.add((int)(14), "Gamers Against Weed");
		Themes.add((int)(15), "The Global Occult Coalition");
		Themes.add((int)(16), "GRU Division \"P\"");
		Themes.add((int)(17), "Herman Fuller's Circus of the Disquieting");
		Themes.add((int)(18), "The Horizon Initiative");
		Themes.add((int)(19), "IJAMEA");
		Themes.add((int)(20), "Manna Charitable Foundation");
		Themes.add((int)(21), "Marshall, Carter, and Dark Ltd.");
		Themes.add((int)(22), "\"Nobody\"");
		Themes.add((int)(23), "Office For The Reclamation of Islamic Artifacts");
		Themes.add((int)(24), "Oneroi Collective");
		Themes.add((int)(25), "Parawatch");
		Themes.add((int)(26), "Prometheus Labs Inc.");
		Themes.add((int)(27), "Sarkic Cults");
		Themes.add((int)(28), "The Serpent's Hand/Wanderer's Library");
		Themes.add((int)(29), "Shark Punching Center");
		Themes.add((int)(30), "The Three Moons Initiative");
		Themes.add((int)(31), "Unusual Incidents Unit");
		Themes.add((int)(32), "Winston's Wildlife Solutions");
		Themes.add((int)(33), "SCP Foundation");
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, Themes));
		spinner1.setSelection((int)(28));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_PROFILEPHOTO:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				if (_filePath.size() == 1) {
					profile.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(1)), 1024, 1024));
					WanderersProfiles.child(Uri.parse(_filePath.get((int)(1))).getLastPathSegment()).putFile(Uri.fromFile(new File(_filePath.get((int)(1))))).addOnSuccessListener(_WanderersProfiles_upload_success_listener).addOnFailureListener(_WanderersProfiles_failure_listener).addOnProgressListener(_WanderersProfiles_upload_progress_listener);
				}
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		progress.setVisibility(View.INVISIBLE);
		info.setVisibility(View.INVISIBLE);
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
