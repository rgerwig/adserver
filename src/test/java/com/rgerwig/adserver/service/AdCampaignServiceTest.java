package com.rgerwig.adserver.service;

import com.rgerwig.adserver.model.AdCampaignFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;

import javax.ws.rs.core.Response;

public class AdCampaignServiceTest {

    @Test
    public void testGetActiveCampaign(){

        JsonObject json = Json.createObjectBuilder()
                .add(AdCampaignFactory.PARTNER_ID, "rgerwig_get")
                .add(AdCampaignFactory.DURATION,330002)
                .add(AdCampaignFactory.AD_CONTENT, "Content to display for the advertising campaign.")
                .build();

        AdCampaignService service = new AdCampaignService();

        //add new campaign
        service.add(json);

        Response response = service.get("rgerwig_get");
        Assert.assertTrue(response!=null);
        Assert.assertTrue(response.getStatus()==200);
        Assert.assertTrue(response.hasEntity());
    }

    @Test
    public void testNoExistingCampaignForPartnerId(){

        JsonObject json = Json.createObjectBuilder()
                .add(AdCampaignFactory.PARTNER_ID, "rgerwig_none")
                .add(AdCampaignFactory.DURATION,330002)
                .add(AdCampaignFactory.AD_CONTENT, "Content to display for the advertising campaign.")
                .build();

        AdCampaignService service = new AdCampaignService();

        //add new campaign
        service.add(json);

        Response response = service.get("rgerwig");
        Assert.assertTrue(response!=null);
        Assert.assertTrue(response.getStatus()==404);

    }

    @Test
    public void testNoActiveCampaignForPartnerId(){

        JsonObject json = Json.createObjectBuilder()
                .add(AdCampaignFactory.PARTNER_ID, "rgerwig_deactivated")
                .add(AdCampaignFactory.DURATION,-330002)
                .add(AdCampaignFactory.AD_CONTENT, "Content to display for the advertising campaign.")
                .build();

        AdCampaignService service = new AdCampaignService();

        //add new campaign
        service.add(json);

        Response response = service.get("rgerwig_deactivated");
        Assert.assertTrue(response!=null);
        Assert.assertTrue(response.getStatus()==200);

    }

    @Test
    public void testAddNewCampaignNoConflicts(){

        JsonObject json = Json.createObjectBuilder()
                .add(AdCampaignFactory.PARTNER_ID, "rgerwig_add")
                .add(AdCampaignFactory.DURATION,330002)
                .add(AdCampaignFactory.AD_CONTENT, "Content to display for the advertising campaign.")
                .build();

        AdCampaignService service = new AdCampaignService();

        //add new campaign
        Response response = service.add(json);
        Assert.assertTrue(response!=null);
        Assert.assertTrue(response.hasEntity());
        Assert.assertTrue(response.getStatus()==201);
    }

    @Test
    public void testOnlyOneAdCampaignPerPartner(){
        JsonObject json = Json.createObjectBuilder()
                .add(AdCampaignFactory.PARTNER_ID, "rgerwig_dup")
                .add(AdCampaignFactory.DURATION,330002)
                .add(AdCampaignFactory.AD_CONTENT, "Content to display for the advertising campaign.")
                .build();

        AdCampaignService service = new AdCampaignService();

        //add new campaign
        Response response = service.add(json);

        //add the same one twice
        response = service.add(json);
        Assert.assertTrue(response!=null);
        Assert.assertTrue(response.hasEntity());
        Assert.assertTrue(response.getStatus()==409);
    }

    @Test
    public void testGetAllAdCampaigns(){

        AdCampaignService service = new AdCampaignService();

        //get campaigns
        JsonObject response = service.getAll();

        Assert.assertTrue(response!=null);

    }

    @Test
    public void testUpdateAdCampaignPerPartner(){
        JsonObject json = Json.createObjectBuilder()
                .add(AdCampaignFactory.PARTNER_ID, "rgerwig_dup")
                .add(AdCampaignFactory.DURATION,330002)
                .add(AdCampaignFactory.AD_CONTENT, "Content to display for the advertising campaign.")
                .build();

        AdCampaignService service = new AdCampaignService();

        //add new campaign
        Response response = service.add(json);

        //update the same one
        response = service.update(json);
        Assert.assertTrue(response!=null);
        Assert.assertTrue(response.hasEntity());
        Assert.assertTrue(response.getStatus()==201);
    }

    @Test
    public void testDeleteCampaign(){

        JsonObject json = Json.createObjectBuilder()
                .add(AdCampaignFactory.PARTNER_ID, "rgerwig_delete")
                .add(AdCampaignFactory.DURATION,330002)
                .add(AdCampaignFactory.AD_CONTENT, "Content to display for the advertising campaign.")
                .build();

        AdCampaignService service = new AdCampaignService();

        //add new campaign
        service.add(json);

        Response response = service.delete("rgerwig_delete");
        Assert.assertTrue(response!=null);
        Assert.assertTrue(response.getStatus()==501);
        Assert.assertTrue(response.hasEntity());
    }
}
