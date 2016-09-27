/**
 * 
 */
package com.music;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jitesh.kumar
 *
 */
@XmlRootElement
public class Track {

	private String title;
	private String singer;

	public Track() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Track(String title, String singer) {
		super();
		this.title = title;
		this.singer = singer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		return "Track [title=" + title + ", singer=" + singer + "]";
	}

}
