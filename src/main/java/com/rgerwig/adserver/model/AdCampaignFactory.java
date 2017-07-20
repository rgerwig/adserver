package com.rgerwig.adserver.model;


import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

/**
 * Factory for validating and creating AdCampaign instances
 */
public class AdCampaignFactory {

    //statics for json name value pairs
    public static String PARTNER_ID = "partner_id";
    public static String DURATION = "duration";
    public static String AD_CONTENT = "ad_content";
    public static String CREATION_TIME = "creation_time";

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
        JsonObject json = Json.createObjectBuilder().add("ad",Json.createObjectBuilder()
                .add(PARTNER_ID, campaign.getPartnerId())
                .add(DURATION, campaign.getDuration())
                .add(AD_CONTENT, campaign.getAdContent())
                .add(CREATION_TIME, campaign.getCreationTime())
                .build()).build();

        return json;
    }

    /**
     * This validates the json document for an incoming AdCampaign
     *
     * @param document JsonObject
     * @return Response containing errors or null if valid
     */
    public static Response validateAdCampaignJson(JsonObject document){
        Response response=null;

        if (document !=null) {
            StringBuilder buffer = new StringBuilder();

            try {
                String partner_id = document.getStringValue(PARTNER_ID);
                if(partner_id.length()==0)
                    buffer.append("partner_id value cannot be empty ");
            } catch(Exception e){
                buffer.append("Invalid partner_id value ");
            }

            try {
                document.getIntValue(DURATION);
            } catch (Exception e){
                if(buffer.length()> 0)
                    buffer.append(", ");

                buffer.append("Invalid duration value ");
            }

            try {
                String ad_content = document.getStringValue(AD_CONTENT);
                if(ad_content.length()==0) {
                    if(buffer.length()> 0)
                        buffer.append(", ");

                    buffer.append("ad_content value cannot be empty ");
                }
            } catch (Exception e){
                if(buffer.length()> 0)
                    buffer.append(", ");

                buffer.append("Invalid ad_content value ");
            }
            if(buffer.length()>0){
                response = Response.status(Response.Status.BAD_REQUEST).entity(buffer.toString()).build();
            }
        } else {
           response = Response.status(Response.Status.BAD_REQUEST).entity("Json representing an Ad is required.").build();
        }
        return response;
    }
}
