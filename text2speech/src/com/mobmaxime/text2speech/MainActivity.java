package com.mobmaxime.text2speech;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements
		TextToSpeech.OnInitListener {
	/** Called when the activity is first created. */

	private TextToSpeech t2s;
	private Button btnSpeak;
	private EditText txtText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		t2s = new TextToSpeech(this, this);
		btnSpeak = (Button) findViewById(R.id.ok_btn);
		txtText = (EditText) findViewById(R.id.edt_text);

		// button on click event
		btnSpeak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				output();
			}

		});
	}

	@Override
	public void onDestroy() {
		// Don't forget to shutdown t2s!
		if (t2s != null) {
			t2s.stop();
			t2s.shutdown();
		}
		super.onDestroy();
	}

	@Override
	public void onInit(int status) {
		
		Log.d("Status", status + "");
		Log.d("SUCCESS", TextToSpeech.SUCCESS + "");
		

		if (status == TextToSpeech.SUCCESS) {

			int result = t2s.setLanguage(Locale.US); // set for language
			
			Log.d("result", result + "");
			Log.d("LANG_MISSING_DATA", TextToSpeech.LANG_MISSING_DATA + "");
			Log.d("LANG_NOT_SUPPORTED", TextToSpeech.LANG_NOT_SUPPORTED + "");

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("t2s", "This Language is not supported");
			} else {
				btnSpeak.setEnabled(true);
			}

		} else {
			Log.e("t2s", "Initilization Failed!");
		}

	}

	private void output() {
		Log.d("output", "output------>");

		String text = txtText.getText().toString();

		t2s.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
}