package com.rgerwig.adserver.dao;


import com.rgerwig.adserver.model.AdCampaign;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton for caching the partners ad campaigns
 */
public final class AdCampaignCache {

    /** singleton */
    private static final Map<String, AdCampaign> adCampaignCache = new ConcurrentHashMap<>();

    /**
     * Private to prevent multiple instantiation
     */
    private AdCampaignCache() {
    }

    /**
     * Add new campaign to cache
     *
     * @param campaign Instance of AdCampaign
     */
    public static void add(final AdCampaign campaign) {
        adCampaignCache.put(campaign.getPartnerId(), campaign);
    }

    /**
     * Retrieve existing campaign if any from cache
     *
     * @param partner_id String representing unique id of partner
     * @return Instance of AdCampaign if exists otherwise null
     */
    public static AdCampaign get(final String partner_id) {
        return adCampaignCache.get(partner_id);
    }

    /**
     * Returns all AdCampaigns in collection.
     *
     * @return Collection of AdCampaign if any exist otherwise
     *  zero size collection
     */
    public static Collection<AdCampaign> getAll() {
        return adCampaignCache.values();
    }

    /**
     * Used to determine if an AdCampaign exists for a partner id
     *
     * @param partner_id String representing unique id of partner
     * @return true if exists otherwise false
     */
    public static boolean exists(final String partner_id) {
        boolean exists = false;
        if(adCampaignCache.get(partner_id)!=null)
            exists = true;

        return exists;
    }

}
