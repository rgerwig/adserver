package com.rgerwig.adserver.service;

import com.rgerwig.adserver.dao.AdCampaignCache;
import com.rgerwig.adserver.model.AdCampaign;
import com.rgerwig.adserver.model.AdCampaignFactory;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ad")
@Consumes(MediaType.APPLICATION_JSON)
public class AdCampaignService {

    /**
     * Returns all the advertising campaigns in the cache
     *
     * @return
     */
    @GET
    public JsonObject getAll() {
        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (final AdCampaign campaign : AdCampaignCache.getAll()) {
            arrayBuilder.add(AdCampaignFactory.adCampaignToJsonObject(campaign));
        }

        JsonObject json = Json.createObjectBuilder().add("ads", arrayBuilder).build();
        return json;
    }

    /**
     * This is used to retrieve a specific advertising campaign based on
     * the unique partner id. the path is /ad/{partner_id}
     *
     * @param partner_id The String representing the unique id of the partner
     * @return Response including status code 200 and JSON if the campaign exists. Otherwise it will
     *  return status code 204 and a response indicating what happened.
     */
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") final String partner_id) {
        Response response;
        AdCampaign campaign = AdCampaignCache.get(partner_id);

        if(campaign==null){
          //not found
          response = Response.status(200).entity("An advertising campaign does not exist for partner_id " + partner_id).build();
        } else if(!campaign.isActive()){
          //return no active campaign
          response = Response.status(200).entity("The existing campaign is no longer active for partner_id: " + partner_id).build();
        } else {
          response = Response.status(200).entity(AdCampaignFactory.adCampaignToJsonObject(campaign)).build();
        }
        return response;
    }

    /**
     * Adds a new advertising campaign to the cache. If a campaign already exists for
     * the partner id it will return will return status code 409 along with json containing
     * the current campaign.
     *
     * @param document JsonObject containing the contents of the advertising campaign
     * @return Response including status code 201 and JSON if successful. If campaign already exists for a
     *  partner it will return status code 409 and the JSON of the existing campaign.
     *
     */
    @POST
    public Response add(JsonObject document) {
        Response response = AdCampaignFactory.validateAdCampaignJson(document);
        if(response==null) {
            AdCampaign campaign = AdCampaignFactory.jsonToAdCampaign(document);
            if (!AdCampaignCache.exists(campaign.getPartnerId())) {
                AdCampaignCache.add(campaign);
                response = Response.status(201).entity(AdCampaignFactory.adCampaignToJsonObject(campaign)).build();
            } else {
                //only one active per partner
                response = Response.status(409).entity("Only one active advertising campaign is allowed per partner.").build();
            }
        }
        return response;
    }

}
