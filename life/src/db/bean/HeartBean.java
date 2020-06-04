package db.bean;

public class HeartBean
{
	private int heart = 0;
	
	private String id;
	
	private int timelineId = 0;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getTimelineId()
	{
		return timelineId;
	}

	public void setTimelineId(int timelineId)
	{
		this.timelineId = timelineId;
	}

	public int getHeart()
	{
		return heart;
	}

	public void setHeart(int heart)
	{
		this.heart = heart;
	}
}
