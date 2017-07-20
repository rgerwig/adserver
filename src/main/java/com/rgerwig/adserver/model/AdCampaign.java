package com.rgerwig.adserver.model;



public class AdCampaign {

    private String ad_content;
    private long creationTime;
    private int duration;
    private String partner_id;

    /**
     * Constructor of new AdCampaign. Please note that the creation time
     * is set when you call this constructor.
     *
     * @param partner_id String representing unique id of partner
     * @param duration how long the advertising campaign will last
     * @param ad_content content to display
     */
    public AdCampaign(String partner_id, int duration, String ad_content){
        this.partner_id = partner_id;
        this.duration = duration;
        this.ad_content = ad_content;
        this.creationTime = System.currentTimeMillis();
    }

    /**
     * Content to display for advertising campaign
     *
     * @return String
     */
    public String getAdContent() {
        return this.ad_content;
    }

    /**
     * Creation time for campaign
     *
     * @return long
     */
    public long getCreationTime(){
        return this.creationTime;
    }

    /**
     * Duration of campaign from creation time
     *
     * @return int
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Unique partner id
     *
     * @return String
     */
    public String getPartnerId() {
        return this.partner_id;
    }

    /**
     * Is campaign active
     *
     * @return boolean
     */
    public boolean isActive(){
        return (System.currentTimeMillis() < this.creationTime + this.duration);
    }
}
