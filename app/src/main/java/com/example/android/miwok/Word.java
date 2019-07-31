package com.example.android.miwok;

public class Word {

    private String mMiwokWordTranslation;
    private String mdefaultWordTranslation;
    public int mimageResourceId;
    public int maudioResourceId;

    public Word(String miwokWordTranslation,String defaultWordTranslation, int audioResourceId)
    {
        mdefaultWordTranslation = defaultWordTranslation;
        mMiwokWordTranslation = miwokWordTranslation;
        maudioResourceId = audioResourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mMiwokWordTranslation='" + mMiwokWordTranslation + '\'' +
                ", mdefaultWordTranslation='" + mdefaultWordTranslation + '\'' +
                ", mimageResourceId=" + mimageResourceId +
                ", maudioResourceId=" + maudioResourceId +
                '}';
    }

    public Word(int imageResouceId, String miwokWordTranslation, String defaultWordTranslation, int audioResourceId)
    {
        mdefaultWordTranslation = defaultWordTranslation;
        mMiwokWordTranslation = miwokWordTranslation;
        mimageResourceId = imageResouceId;
        maudioResourceId = audioResourceId;
    }


    public String getmiwokWordTranslation(){
        return mMiwokWordTranslation;
    }
    public String getdefaultWordTranslation(){
        return mdefaultWordTranslation;
    }
    public int getImageResourceId(){
        return mimageResourceId;
    }
    public int getAudioResourceId() { return maudioResourceId; }

}
