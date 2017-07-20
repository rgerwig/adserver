package com.rgerwig.adserver.service;

import com.rgerwig.adserver.model.AdCampaignFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import javax.ws.rs.core.Response;

public class AdCampaignServiceTest {

    @Test
    public void testGetActiveCampaign(){

        JsonObject json = Json.createObjectBuilder()
                .add("partner_id", "rgerwig_get")
                .add("duration",330002)
                .add("ad_content", "Content to display for the advertising campaign.")
                .build();

        AdCampaignService service = new AdCampaignService();

        //add new campaign
        service.add(json);

        Response response = service.get("rgerwig_get");
        Assert.assertTrue(response!=null);
        Assert.assertTrue(response.getStatus()==200);
        Assert.assertTrue(response.hasEntity());

        //System.out.println("testGetActiveCampaign");
        //System.out.println(response.toString());
        //System.out.println(response.getEntity().toString());
    }

    @Test
    public void testNoExistingCampaignForPartnerId(){

        JsonObject json = Json.createObjectBuilder()
                .add("partner_id", "rgerwig_none")
                .add("duration",330002)
                .add("ad_content", "Content to display for the advertising campaign.")
                .build();

        AdCampaignService service = new AdCampaignService();

        //add new campaign
        service.add(json);

        Response response = service.get("rgerwig");
        Assert.assertTrue(response!=null);
        Assert.assertTrue(response.getStatus()==200);
        //System.out.println("testGetNoCampaignForPartnerId");
        //System.out.println(response.toString());
    }

    @Test
    public void testNoActiveCampaignForPartnerId(){

        JsonObject json = Json.createObjectBuilder()
                .add("partner_id", "rgerwig_deactivated")
                .add("duration",-330002)
                .add("ad_content", "Content to display for the advertising campaign.")
                .build();

        AdCampaignService service = new AdCampaignService();

        //add new campaign
        service.add(json);

        Response response = service.get("rgerwig_deactivated");
        Assert.assertTrue(response!=null);
        Assert.assertTrue(response.getStatus()==200);
        //System.out.println("testNoActiveCampaignForPartnerId");
        //System.out.println(response.toString());
    }

    @Test
    public void testAddNewCampaignNoConflicts(){

        JsonObject json = Json.createObjectBuilder()
                .add("partner_id", "rgerwig_add")
                .add("duration",330002)
                .add("ad_content", "Content to display for the advertising campaign.")
                .build();

        AdCampaignService service = new AdCampaignService();

        //add new campaign
        Response response = service.add(json);
        Assert.assertTrue(response!=null);
        Assert.assertTrue(response.hasEntity());
        Assert.assertTrue(response.getStatus()==201);
        //System.out.println("testAddNewCampaignNoConflicts");
        //System.out.println(response.toString());
        //System.out.println(response.getEntity().toString());
    }

    @Test
    public void testOnlyOneAdCampaignPerPartner(){
        JsonObject json = Json.createObjectBuilder()
                .add("partner_id", "rgerwig_dup")
                .add("duration",330002)
                .add("ad_content", "Content to display for the advertising campaign.")
                .build();

        AdCampaignService service = new AdCampaignService();

        //add new campaign
        Response response = service.add(json);

        //add the same one twice
        response = service.add(json);
        Assert.assertTrue(response!=null);
        Assert.assertTrue(response.hasEntity());
        Assert.assertTrue(response.getStatus()==409);
        //System.out.println("testOnlyOneAdCampaignPerPartner");
        //System.out.println(response.toString());
        //System.out.println(response.getEntity().toString());
    }

    @Test
    public void testGetAllAdCampaigns(){

        AdCampaignService service = new AdCampaignService();

        //get campaigns
        JsonArray response = service.getAll();

        Assert.assertTrue(response!=null);

        //System.out.println("testGetAllAdCampaigns");
        //System.out.println(response.toString());
    }


}
