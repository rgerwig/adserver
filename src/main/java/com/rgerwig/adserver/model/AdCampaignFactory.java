package com.rgerwig.adserver.model;


import javax.json.Json;
import javax.json.JsonObject;

public class AdCampaignFactory {

    //statics for json name value pairs
    private static String PARTNER_ID = "partner_id";
    private static String DURATION = "duration";
    private static String AD_CONTENT = "ad_content";
    private static String CREATION_TIME = "creation_time";

    /**
     * Creates a new AdCampaign instance
     *
     * @param partner_id String representing the unique id of the partner
     * @param duration int representing the duration time for this ad
     * @param ad_content String representing the content for display
     * @return Instance of AdCampaign
     */
    public static AdCampaign createAdCampaign(String partner_id, int duration, String ad_content){
        return new AdCampaign(partner_id, duration, ad_content);
    }

    /**
     * Used to convert JsonObject to POJO. Includes validation.
     *
     * @param document JsonObject containing ad
     * @return instance of AdCampaign
     */
    public static AdCampaign jsonToAdCampaign(JsonObject document){

        String partner_id = document.getStringValue(PARTNER_ID);
        int duration = document.getIntValue(DURATION);
        String ad_content = document.getStringValue(AD_CONTENT);

        return AdCampaignFactory.createAdCampaign(partner_id,duration,ad_content);

    }

    /**
     * Used to convert POJO back into JsonObject
     *
     * @param campaign Instance of existing AdCampaign
     * @return JsonObject representing the AdCampaign
     */
    public static JsonObject adCampaignToJsonObject(AdCampaign campaign){
        JsonObject json = Json.createObjectBuilder()
                .add(PARTNER_ID, campaign.getPartnerId())
                .add(DURATION, campaign.getDuration())
                .add(AD_CONTENT, campaign.getAdContent())
                .add(CREATION_TIME, campaign.getCreationTime())
                .build();

        return json;
    }
}
