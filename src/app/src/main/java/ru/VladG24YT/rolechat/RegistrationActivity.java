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
import android.support.v7.widget.Toolbar;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;

public class RegistrationActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private double i = 0;
	private String nickname = "";
	private HashMap<String, Object> TMP_WandererProfile = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> TMP_WanderersDB = new ArrayList<>();
	
	private LinearLayout linear1;
	private TextView textview1;
	private EditText nick;
	private EditText email;
	private EditText pass;
	private EditText pass_rep;
	private Button register;
	
	private Intent ActivityChanger = new Intent();
	private FirebaseAuth Wanderers;
	private OnCompleteListener<AuthResult> _Wanderers_create_user_listener;
	private OnCompleteListener<AuthResult> _Wanderers_sign_in_listener;
	private OnCompleteListener<Void> _Wanderers_reset_password_listener;
	private DatabaseReference WanderersDB = _firebase.getReference("Wanderers/Tickets");
	private ChildEventListener _WanderersDB_child_listener;
	private SharedPreferences local_profile;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.registration);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		textview1 = (TextView) findViewById(R.id.textview1);
		nick = (EditText) findViewById(R.id.nick);
		email = (EditText) findViewById(R.id.email);
		pass = (EditText) findViewById(R.id.pass);
		pass_rep = (EditText) findViewById(R.id.pass_rep);
		register = (Button) findViewById(R.id.register);
		Wanderers = FirebaseAuth.getInstance();
		local_profile = getSharedPreferences("profile.cfg", Activity.MODE_PRIVATE);
		
		nick.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				nick.setHintTextColor(0xFF607D8B);
				nick.setHint("Псевдоним");
			}
		});
		
		email.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				email.setHintTextColor(0xFF607D8B);
				email.setHint("Эл. Почта");
			}
		});
		
		pass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pass.setHintTextColor(0xFF607D8B);
				pass.setHint("Пароль");
			}
		});
		
		pass_rep.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pass_rep.setHintTextColor(0xFF607D8B);
				pass_rep.setHint("Повторите Пароль");
			}
		});
		
		register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!email.getText().toString().equals("")) {
					if (!pass.getText().toString().equals("")) {
						if (pass_rep.getText().toString().equals(pass.getText().toString())) {
							if (!nick.getText().toString().equals("")) {
								if (!(email.getText().toString().equals(local_profile.getString("email", "")) || (email.getText().toString().equals(local_profile.getString("email", "")) && email.getText().toString().equals(local_profile.getString("pass", ""))))) {
									
								}
								else {
									SketchwareUtil.showMessage(getApplicationContext(), "Такой пользователь уже существует");
								}
								Wanderers.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(RegistrationActivity.this, _Wanderers_create_user_listener);
								TMP_WandererProfile.clear();
								TMP_WandererProfile = new HashMap<>();
								TMP_WandererProfile.put("email", email.getText().toString());
								TMP_WandererProfile.put("pass", pass.getText().toString());
								TMP_WandererProfile.put("nick", nick.getText().toString());
								WanderersDB.push().updateChildren(TMP_WandererProfile);
								local_profile.edit().putString("email", email.getText().toString()).commit();
								local_profile.edit().putString("pass", pass.getText().toString()).commit();
								local_profile.edit().putString("nick", nick.getText().toString()).commit();
							}
							else {
								nick.setHintTextColor(0xFFF44336);
								nick.setHint("Это поле не должно быть пустым");
							}
						}
						else {
							pass_rep.setHintTextColor(0xFFF44336);
							pass_rep.setHint("Пароли не совпадают");
						}
					}
					else {
						pass.setHintTextColor(0xFFF44336);
						pass.setHint("Это поле не должно быть пустым");
					}
				}
				else {
					email.setHintTextColor(0xFFF44336);
					email.setHint("Это поле не должно быть пустым");
				}
			}
		});
		
		_WanderersDB_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		WanderersDB.addChildEventListener(_WanderersDB_child_listener);
		
		_Wanderers_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					SketchwareUtil.showMessage(getApplicationContext(), "Добро Пожаловать в Библиотеку, Странник");
					ActivityChanger.setClass(getApplicationContext(), SettingsActivity.class);
					startActivity(ActivityChanger);
					finish();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_Wanderers_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					nickname = FirebaseAuth.getInstance().getCurrentUser().getEmail();
					WanderersDB.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							TMP_WanderersDB = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									TMP_WanderersDB.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							i = 0;
							for(int _repeat19 = 0; _repeat19 < (int)(TMP_WanderersDB.size()); _repeat19++) {
								TMP_WandererProfile = new HashMap<>();
								TMP_WandererProfile = TMP_WanderersDB.get((int)i);
								if (TMP_WandererProfile.get("email").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
									nickname = TMP_WandererProfile.get("nick").toString();
									break;
								}
								else {
									i++;
								}
							}
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
					SketchwareUtil.showMessage(getApplicationContext(), "Приветствую вас снова, ".concat(nickname.concat("!")));
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_Wanderers_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	private void initializeLogic() {
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
