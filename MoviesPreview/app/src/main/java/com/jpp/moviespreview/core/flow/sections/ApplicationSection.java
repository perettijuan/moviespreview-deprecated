package com.jpp.moviespreview.core.flow.sections;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.jpp.moviespreview.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a navigational section in the application.
 * <p>
 * Created by jpperetti on 9/12/16.
 */
public class ApplicationSection implements Parcelable {


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SectionType.MOST_POPULAR, SectionType.IN_THEATRE})
    public @interface SectionType {
        int MOST_POPULAR = 1;
        int IN_THEATRE = 2;
    }


    @SectionType
    private final int mType;
    @StringRes
    private final int mName;
    private boolean isSelected;


    /**
     * Creates the new instance of sections to use in the application.
     *
     * @return - the newly created list.
     */
    @NonNull
    public static List<ApplicationSection> newInstance() {
        List<ApplicationSection> sections = new ArrayList<>();
        sections.add(new ApplicationSection(SectionType.MOST_POPULAR, R.string.section_most_popular, true));
        sections.add(new ApplicationSection(SectionType.IN_THEATRE, R.string.section_in_theatre, false));
        return sections;
    }


    /**
     * Private constructor to avoid external initialization.
     */
    private ApplicationSection(@SectionType int sectionType, @StringRes int name, boolean selected) {
        this.mType = sectionType;
        this.mName = name;
        this.isSelected = selected;
    }


    public int getType() {
        return mType;
    }

    public int getName() {
        return mName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void toggleSelected() {
        isSelected = !isSelected;
    }

    //-- parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getType());
        dest.writeInt(this.getName());
        dest.writeByte(isSelected() ? (byte) 1 : (byte) 0);
    }


    private ApplicationSection(Parcel in) {
        this.mType = in.readInt();
        this.mName = in.readInt();
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<ApplicationSection> CREATOR = new Creator<ApplicationSection>() {
        public ApplicationSection createFromParcel(Parcel source) {
            return new ApplicationSection(source);
        }

        public ApplicationSection[] newArray(int size) {
            return new ApplicationSection[size];
        }
    };
}
