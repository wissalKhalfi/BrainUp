package mobile.esprit.brainup.MemoryGame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import mobile.esprit.brainup.R;

public class MemoryView extends GridView {


	int moves =0;
	Context context;

	MediaPlayer Score_increased;

	// Debugging
    private static final String TAG = "MemoryView";
    private static final boolean D = true;
	
    private enum State {
    	NONE_UNCOVERED, ONE_UNCOVERED, TWO_UNCOVERED,
    }
    private static final int COVER_DELAY = 500; //ms
    int IMAGE_PAIRS = 4;


	private static String IMAGES_DIR = "memory_images";
	private static String COVER_IMAGE = "cover.png";
	int level;
    private State mCurrentState = State.NONE_UNCOVERED;
    private static MemoryItem mFirstUncovered = null;
    private static MemoryItem mSecondUncovered = null;
    
	private int mImageCount;
	private int mPairsUncovered = 0;
	
	private List<MemoryItem> mImageItems;
	private MemoryAdapter mMemoryAdapter;
	private OnGameFinishedListener mListener;

	public MemoryView(Context context) {
		super(context);
		this.context=context;
		init();
	}


	public MemoryView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		init();
	}

	public MemoryView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context=context;
		init();

	}
	
	public void setOnGameFinishedListener(OnGameFinishedListener listener) {
		mListener = listener;
	}
	
	private void init() {

		SharedPreferences sharedpreferences=getContext().getApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
		level=sharedpreferences.getInt("memorylevelplaying",1);
		Log.d(TAG, "init: level"+level);
		switch (level){
			case 1:
				IMAGE_PAIRS = 4;
				mImageCount = IMAGE_PAIRS;
				break;
			case 2:
				IMAGE_PAIRS = 8;
				mImageCount = IMAGE_PAIRS;
				break;
			case 3:
				IMAGE_PAIRS = 12;
				mImageCount = IMAGE_PAIRS;
				break;
			case 4:
				IMAGE_PAIRS = 16;
				mImageCount = IMAGE_PAIRS;
				break;
		}
		enumerateImages();
		
		mMemoryAdapter = new MemoryAdapter(getContext(), mImageItems, loadImage(COVER_IMAGE));
		setAdapter(mMemoryAdapter);
		setGravity(Gravity.FILL);
        setOnItemClickListener(mItemClickListener);
	}
	
	private void reset() {
		
		mPairsUncovered = 0;
		
		init();
	}
	
	private void enumerateImages() {
		AssetManager asm = getContext().getAssets();

		try {
			String[] images = asm.list(IMAGES_DIR);
			
			if (images.length < mImageCount) {
				if (D) { Log.e(TAG, "Not enough images for all fields, reducing fields."); }
				mImageCount = images.length;
			}
			
			List<String> imageNames = new ArrayList<String>();
			
			mImageItems = new ArrayList<MemoryItem>();
			
			Random rand = new Random();

			while (imageNames.size() < mImageCount) {
				int index = rand.nextInt(images.length);

				String oneImage = IMAGES_DIR + "/" + images[index];

				if (!imageNames.contains(oneImage)) {
					imageNames.add(oneImage);
					mImageItems.add(new MemoryItem(images[index], loadImage(oneImage)));
					mImageItems.add(new MemoryItem(images[index], loadImage(oneImage)));
				}
			}

			Collections.shuffle(mImageItems);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Bitmap loadImage(String path) {
		try {
			return BitmapFactory.decodeStream(getContext().getAssets().open(path));
		} catch (IOException e) {
			return null;
		}
	}

	private OnItemClickListener mItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {

			moves++;
			TextView _moves= (TextView)  ((MemoryActivity)context).findViewById(R.id.movesmemory);
			_moves.setText("Moves : "+moves);



			MemoryItem item = (MemoryItem) parent.getAdapter().getItem(position);
			
			if (D) {
				Log.d(TAG, "onItemClick: position: " + position + ", id: " + id + ", v: " + view); }
			
			
			if ( State.NONE_UNCOVERED == mCurrentState && item.active() ) {

				mCurrentState = State.ONE_UNCOVERED;
				mFirstUncovered = item;
				mMemoryAdapter.uncover(mFirstUncovered.getPosition());

			} else if (State.ONE_UNCOVERED == mCurrentState && item.active() && position != mFirstUncovered.getPosition()) {
				mCurrentState = State.TWO_UNCOVERED;
				
				mSecondUncovered = item;
				mMemoryAdapter.uncover(mSecondUncovered.getPosition());
				
				if (!mSecondUncovered.isPair(mFirstUncovered)) {
					// cover again after delay
					
					postDelayed(new Runnable() {
						
						@Override
						public void run() {
							
							if (D) { Log.d(TAG, "postRunnable: running"); }
							
							synchronized (MemoryView.this) {
								mMemoryAdapter.cover(mFirstUncovered.getPosition());
								mFirstUncovered = null;
								mMemoryAdapter.cover(mSecondUncovered.getPosition());
								mSecondUncovered = null;
								
								mCurrentState = State.NONE_UNCOVERED;	
							}
						}
					}, COVER_DELAY);
					
				} else {
					 // leave both uncovered
					mFirstUncovered.deactivate();
					mFirstUncovered = null;
					
					mSecondUncovered.deactivate();
					mSecondUncovered = null;
					
					mCurrentState = State.NONE_UNCOVERED;
					
					mPairsUncovered++;


					TextView _score= (TextView)  ((MemoryActivity)context).findViewById(R.id.scorememory);
					_score.setText("Score : "+mPairsUncovered);


					Score_increased = MediaPlayer.create(context,R.raw.claping);
					Score_increased.start();
					
					if (mPairsUncovered >= mImageCount) {
						// game finished
						memoryFinished();
					}
				}
			} else if (!(State.TWO_UNCOVERED == mCurrentState)) { 
				// check needed to prevent multicoverup while waiting for cover_delay
				if (null != mFirstUncovered) {
					mMemoryAdapter.cover(mFirstUncovered.getPosition());
					mFirstUncovered = null;
				}
				
				mCurrentState = State.NONE_UNCOVERED;
			}
			
			if (D) { Log.d(TAG, "onItemClick: first: " + mFirstUncovered + ", second: " + mSecondUncovered); }
		}
	};

	private void memoryFinished() {

		
		if (null != mListener) {
			boolean newRound = mListener.onFinished();
			if (newRound) {
				reset();
			}
		} else {
			SharedPreferences sharedpreferences=getContext().getApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedpreferences.edit();
			if(level>3) editor.putInt("Memorylevelnext",4);
			else editor.putInt("Memorylevelnext",level+1);

			editor.commit();
			AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
			dialog.setTitle(R.string.memory_game_finished_title);
			dialog.setMessage(R.string.memory_game_finished_text);
			dialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					reset();
				}
			});
			dialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					Intent ma = new Intent(getContext().getApplicationContext(),FirstMemoryActivity.class);
					getContext().startActivity(ma);

				}
			});
			dialog.show();
		}
	}
	
	public interface OnGameFinishedListener {
		/**
		 * Callback called when the player finished the game.
		 * 
		 * @return true if a new round should be started.
		 */
		public boolean onFinished();
	}
}
//CatLog pour ls crash