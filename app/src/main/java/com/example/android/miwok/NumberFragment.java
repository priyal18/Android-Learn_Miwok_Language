package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumberFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChange= new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focus_Change) {

            if(focus_Change == AUDIOFOCUS_LOSS_TRANSIENT || focus_Change == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){

                mediaPlayer.pause();
                mediaPlayer.seekTo(0);

            }
            else if(focus_Change == AUDIOFOCUS_GAIN){

                mediaPlayer.start();

            }
            else if(focus_Change == AUDIOFOCUS_LOSS){

                releaseMediaPlayer();

            }
        }
    };

    final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            releaseMediaPlayer();
        }
    };


    public NumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.drawable.number_one, "lutti", "one", R.raw.number_one));
        words.add(new Word(R.drawable.number_two, "ottiko", "two", R.raw.number_two));
        words.add(new Word(R.drawable.number_three, "tolookosu", "three", R.raw.number_three));
        words.add(new Word(R.drawable.number_four, "oyyisa", "four", R.raw.number_four));
        words.add(new Word(R.drawable.number_five, "massokka", "five", R.raw.number_five));
        words.add(new Word(R.drawable.number_six, "temmokka", "six", R.raw.number_six));
        words.add(new Word(R.drawable.number_seven, "kenekaku", "seven", R.raw.number_seven));
        words.add(new Word(R.drawable.number_eight, "kawinta", "eight", R.raw.number_eight));
        words.add(new Word(R.drawable.number_nine, "wo'e", "nine", R.raw.number_nine));
        words.add(new Word(R.drawable.number_ten, "na'aacha", "ten", R.raw.number_ten));


        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);


        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                releaseMediaPlayer();
                Word word = words.get(position);
                Log.v("NumbersActivity", "Current word: " + word);

                int result = audioManager.requestAudioFocus(mOnAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {

                    mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {

        if (mediaPlayer != null) {

            mediaPlayer.release();
            mediaPlayer=null;
            audioManager.abandonAudioFocus(mOnAudioFocusChange);

        }
    }

}
