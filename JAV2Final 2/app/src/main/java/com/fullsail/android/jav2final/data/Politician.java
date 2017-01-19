// Ryan Backa
// JAV2 - 1612
// Politician

package com.fullsail.android.jav2final.data;

import java.io.Serializable;

public class Politician implements Serializable {

	private static final long serialVersionUID = -4722695584224558275L;
	
	private String mName;
	private String mParty;
	private String mDescription;
	private int mId;
	
	public Politician() {
		mName = mParty = mDescription = "";
		mId = -1;
	}
	
	public Politician(String name, String party, String description, int id) {
		mName = name;
		mParty = party;
		mDescription = description;
		mId = id;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getParty() {
		return mParty;
	}

	public void setParty(String party) {
		mParty = party;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		mDescription = description;
	}

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		mId = id;
	}
	
	@Override
	public boolean equals(Object o) {
        // TODO: Implement this so that two politicians are equal if they have the same ID.
		
		return false;
	}
}